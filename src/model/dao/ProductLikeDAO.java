package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.ProductLikeDAO;
import model.dto.Camera;
import model.dto.ProductLike;

class LikeList {
	private String pId;
	private int likeCount;
	
	public void setPid(String pId) {
        this.pId = pId;
       
    }
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public String getPId() {
		return pId;
    }
 
	public int getLikeCount() {
		return likeCount;
	}
}

public class ProductLikeDAO {

	private JDBCUtil jdbcUtil = null;
	
	private static String query = 	"SELECT PRODUCT_ID, USER_ID " +
	  								" FROM PRODUCT_LIKE ";
	public ProductLikeDAO() {
		jdbcUtil = new JDBCUtil();
	}
		
	/*
	public ProductLike getProductLikeByName(String plname) {
		String searchQuery = query + "WHERE PRODUCT_LIKE.plname = ?";
		Object[] param = new Object[] {plname};
		
		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			ProductLike dto = new ProductLike();
			while (rs.next()) {
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId(rs.getString("USER_ID"));
			}
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	//
	public List<Camera> getCameraRanking(){
		
		String allQuery = query + ", " + "FROM CAMERA"
							+ "WHERE PRODUCT.NAME = ? ";	
		
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행	
			
			List<Camera> list = new ArrayList<Camera>();		// Camera 객체들을 담기위한 list 객체
			while (rs.next()) {	
				Camera dto = new Camera();		// 하나의 Camera 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
				
				
				list.add(dto);		// list 객체에 정보를 설정한 Camera 객체 저장
			}
			return list;		// 카메라 정보를 저장한 dto 들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;	
		
	}
	*/
	
	
	// 좋아요 수를 많이 받은 상품을 1위부터 10위까지 보여줌 
	// 제품 이름과 사진을 출력 
	public List<LikeList> getProductLikeList() {
		// 기본 쿼리와 합쳐짐 	
//		String newquery = "select p.name " +
//				"from (select p.name from product_like l, product p group by p.name " 
//				+ "order by count(l.PRODUCT_ID) )"
//				+	" where rownum <= 10"; 
//		
		jdbcUtil.setSql(query);		// JDBCUtil 에 query 설정
		
		String[] productList = new String[10];	//1-10위 까지 상품명 저장할 배열
		int like_count;	//좋아요 수
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
//			List<LikeList> list = new ArrayList<LikeList>();		// LikeList 객체들을 담기위한 list 객체
			
			while (rs.next()) {	
				ProductLike dto = new ProductLike();		// 하나의 ProductLike 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("PRODUCT_ID"));
	//***		//	dto.setUserId("USER_ID");	에러나면 주석 풀어보기
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key 값이 이미 존재하면(true)
					like_count = map.get(pId);	//value값 (map.get(pId)) : "like_count"
					like_count ++;	//카운트 += 1;
				}
				else {	//key 값이 존재안하면 (false)
					map.put(pId, 0);	//해당 제품의 좋아요 수는 0으로 초기화
				}
//				list.add();		// list 객체에 정보를 설정한 ProductLike 객체 저장
			}	//max 정해짐---while 끝남
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    
			//value 내림차순 //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    for(String key : keySetList) {
	            System.out.println(String.format("Key : %s, Value : %s", key, map.get(key)));
	        }	//내림차순 정렬 확인 출력
	         
	          
		        
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		
		return null;	
	}

	//map 에서 value로 키값 찾기
	public static String getKeyFromValue(Map map, int max) {	
		  for (Object o : map.keySet()) {
		   if (map.get(o).equals(max)) {
		    return o.toString();	//key 반환
		   }
		  }
		  return null;
		 }
	
	
	public int insertProductLike(ProductLike productLike) {
		int result = 0;
		String insertQuery = "INSERT INTO PRODUCT_LIKE (PRODUCT_ID, USER_ID) " +
							 "VALUES (?, ?) ";
		
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성 물음표 다 받아오기 
		Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(productLike.getProductId() + " 제품정보가 삽입되었습니다.");
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 제품정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return result;		// insert 에 의해 반영된 레코드 수 반환	
	}


	
	public int deleteProductLike(int uid, int pid) { //질문
		String deleteQuery = "DELETE FROM PRODUCT_LIKE WHERE USER_ID = ? AND PRODUCT_ID = ? ";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {uid, pid};
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}

	
	public List<ProductLike> getProductLikeById(String plId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "WHERE PRODUCT_LIKE.plname = ?";
		Object[] param = new Object[] {plId};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ProductLike> list = new ArrayList<ProductLike>();
			while (rs.next()) {
				ProductLike dto = new ProductLike();
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");
				list.add(dto);		// list 객체에 정보를 설정한 LaptopDTO 객체 저장
			}
			return list;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public int updateProductLike(ProductLike productLike) {
		// TODO Auto-generated method stub
		return 0;
	}

}
