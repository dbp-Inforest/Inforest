package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ProductLikeDAO;
import model.dto.ProductLike;


public class ProductLikeDAO {

	private JDBCUtil jdbcUtil = null;
	
	private static String query = 	"SELECT PRODUCT_ID, USER_ID " +
	  								" FROM PRODUCT_LIKE ";
	public ProductLikeDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	
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

	
	public List<ProductLike> getProductLikeList() {
		// 기본 쿼리와 합쳐짐 	
		jdbcUtil.setSql(query);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
			List<ProductLike> list = new ArrayList<ProductLike>();		// ProductLike 객체들을 담기위한 list 객체
			while (rs.next()) {	
				ProductLike dto = new ProductLike();		// 하나의 ProductLike 객체 생성 후 정보 설정
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");
				list.add(dto);		// list 객체에 정보를 설정한 LaptopDTO 객체 저장
			}
			return list;		// 학생정보를 저장한 dto들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;	
	}

	
	public int insertProductLike(ProductLike productLike) {
		int result = 0;
		String insertQuery = "INSERT INTO PHONE (PRODUCT_ID, USER_ID) " +
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
