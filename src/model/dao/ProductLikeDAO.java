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
import model.dto.Ranking;
//
//class LikeList {
//	private String pId;
//	private int likeCount;
//	
//	public void setPid(String pId) {
//        this.pId = pId;
//       
//    }
//	
//	public void setLikeCount(int likeCount) {
//		this.likeCount = likeCount;
//	}
//	
//	public String getPId() {
//		return pId;
//    }
// 
//	public int getLikeCount() {
//		return likeCount;
//	}
//}

public class ProductLikeDAO {
	Ranking lList = new Ranking();
	List<Ranking> list = new ArrayList<Ranking>();		// LikeList 객체들을 담기위한 list 객체
	int like_count;	//좋아요 수
	
	private JDBCUtil jdbcUtil = null;
	
	private static String phone_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, PHONE PH " +
										" WHERE P.PRODUCT_ID = PH.PRODUCT_ID ";
	
	private static String laptop_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, LAPTOP L " +
										" WHERE P.PRODUCT_ID = L.PRODUCT_ID ";
	
	private static String camera_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, CAMERA C " +
										" WHERE P.PRODUCT_ID = C.PRODUCT_ID ";
	
	private static String tablet_q = 	"SELECT P.PRODUCT_ID, P.USER_ID " +
										" FROM PRODUCT_LIKE P, TABLET T " +
										" WHERE P.PRODUCT_ID = T.PRODUCT_ID ";
	
	public ProductLikeDAO() {
		jdbcUtil = new JDBCUtil();
	}
		
	public List<Ranking> getPhoneLikeList() {
		jdbcUtil.setSql(phone_q);		// JDBCUtil 에 query 설정
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// 하나의 ProductLike 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId(rs.getString("USER_ID"));	
				
				String pId = dto.getProductId();
				System.out.println("pId: " + pId);
				if (map.containsKey(pId)) {	//key 값이 이미 존재하면(true)
					like_count = map.get(pId);	//value값 (map.get(pId)) : "like_count"
					like_count ++;	//카운트 += 1;
				}
				else {	//key 값이 존재안하면 (false)
					map.put(pId, 0);	//해당 제품의 좋아요 수는 0으로 초기화
					System.out.println("첫 키 추가");
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value 내림차순 //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< 내림차순 test 결과창 >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("방법1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		    
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
				
		return null;	
	}
	
	public List<Ranking> getLaptopLikeList() {
		jdbcUtil.setSql(laptop_q);		// JDBCUtil 에 query 설정
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// 하나의 ProductLike 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");	
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key 값이 이미 존재하면(true)
					like_count = map.get(pId);	//value값 (map.get(pId)) : "like_count"
					like_count ++;	//카운트 += 1;
				}
				else {	//key 값이 존재안하면 (false)
					map.put(pId, 0);	//해당 제품의 좋아요 수는 0으로 초기화
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value 내림차순 //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< 내림차순 test 결과창 >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("방법1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
				
		return null;	
	}
	
	public List<Ranking> getCameraLikeList() {
		jdbcUtil.setSql(camera_q);		// JDBCUtil 에 query 설정
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// 하나의 ProductLike 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");	
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key 값이 이미 존재하면(true)
					like_count = map.get(pId);	//value값 (map.get(pId)) : "like_count"
					like_count ++;	//카운트 += 1;
				}
				else {	//key 값이 존재안하면 (false)
					map.put(pId, 0);	//해당 제품의 좋아요 수는 0으로 초기화
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value 내림차순 //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< 내림차순 test 결과창 >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("방법1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
				
		return null;	
	}
	
	public List<Ranking> getTabletLikeList() {
		jdbcUtil.setSql(tablet_q);		// JDBCUtil 에 query 설정
			
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행		
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ProductLike dto = new ProductLike();		
			while (rs.next()) {	
						// 하나의 ProductLike 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");	
				
				String pId = dto.getProductId();
				if (map.containsKey(pId)) {	//key 값이 이미 존재하면(true)
					like_count = map.get(pId);	//value값 (map.get(pId)) : "like_count"
					like_count ++;	//카운트 += 1;
				}
				else {	//key 값이 존재안하면 (false)
					map.put(pId, 0);	//해당 제품의 좋아요 수는 0으로 초기화
				}
			}
			
			
			List<String> keySetList = new ArrayList<>(map.keySet());
		    //value 내림차순 //
		    Collections.sort(keySetList, new Comparator<String>() {
		        @Override
		        public int compare(String o1, String o2) {
		           return map.get(o2).compareTo(map.get(o1));
		        }
		    });
		    
		    System.out.println("===========<< 내림차순 test 결과창 >>============");
		    for ( String key : map.keySet() ) {
		        System.out.println("방법1) key : " + key +" / value : " + map.get(key));
		    }
		    System.out.println("=======================");
		    
		    int c = 0;
		    for ( String key : map.keySet() ) {
		    	if(c > 10) {
		    		break;
		    	}
		    	else {
		    		lList.setProductId(key);
		    		lList.setLikeCount(map.get(key));
		    		list.add(lList);
		    		c ++;
		    	}
		    }
		    return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
				
		return null;	
	}
/*
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

*/
	/*
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
*/
	
}
