package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.PCommentDAO;
import model.dto.PComment;

public class PCommentDAO {

	private JDBCUtil jdbcUtil = null;
	
	private static String query = "SELECT COMMENT_ID AS COMMENT_ID, " +
			  "       RECOMMENDATION AS COMMENT_RECOMMENDATION, " +
			  "       CONTENT AS COMMENT_CONTENT, " +
			  "       USER_ID AS COMMENT_USER, " +
			  "		  POST_ID AS COMMENT_POST,  "+
			  "		  REGIST_DATE AS COMMENT_REGISTDATE ";
			  
	 
	public List<PComment> getPCommentList() {
		// 기본 쿼리와 합쳐짐 
		String allQuery = query  + "FROM P_COMMENT ";
		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
			List<PComment> list = new ArrayList<PComment>();		// LaptopDTO 객체들을 담기위한 list 객체
			while (rs.next()) {	
				PComment dto = new PComment();		// 하나의 LaptopDTO 객체 생성 후 정보 설정
				dto.setCommentId(rs.getString("COMMENT_ID"));
				dto.setRecommandation(rs.getDouble("COMMENT_RECOMMENDATION"));
				dto.setContent(rs.getString("COMMENT_CONTENT"));
				dto.setUserId(rs.getString("COMMENT_USER"));
				dto.setPostId(rs.getString("COMMENT_POST"));
				dto.setRegistDate(rs.getDate("COMMENT_REGISTDATE"));
				
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

	public int insertPComment(PComment pComment) {
		
		int result = 0;
		
		String insertQuery = "INSERT INTO PHONE (COMMENT_ID, RECOMMENDATION, CONTENT, USER_ID, POST_ID, REGIST_DATE) " +
							 "VALUES (?, ?, ?, ?, ?, ?) ";
		insertQuery += "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
				 "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
	
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성 물음표 다 받아오기 
		Object[] param = new Object[] {pComment.getCommentId(), pComment.getRecommandation(), pComment.getContent(), pComment.getUserId(), 
							pComment.getPostId(), pComment.getRegistDate(), pComment.getProductId(), pComment.getName(), pComment.getColor(), pComment.getPrice(),
		 					pComment.getBrand(), pComment.getReleased_date(), pComment.getWeight(), pComment.getpKind()};
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(pComment.getCommentId() + " 제품정보가 삽입되었습니다.");
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

	public int updatePComment(PComment pComment) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deletePComment(int pcProductId) {
		String deleteQuery = "DELETE FROM P_COMMENT WHERE pcProductId = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {pcProductId};
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

	public PComment getPCommentByName(String pcName) {
		// TODO Auto-generated method stub
		String searchQuery = query + " FROM P_COMMENT " + " WHERE P_COMMENT.pcName = ?";
		Object[] param = new Object[] {pcName};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			PComment dto = new PComment();
			while (rs.next()) {
				dto.setCommentId(rs.getString("COMMENT_ID"));
				dto.setRecommandation(rs.getDouble("COMMENT_RECOMMENDATION"));
				dto.setContent(rs.getString("COMMENT_CONTENT"));
				dto.setUserId(rs.getString("COMMENT_USER"));
				dto.setPostId(rs.getString("COMMENT_POST"));
				dto.setRegistDate(rs.getDate("COMMENT_REGISTDATE"));
			}
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<PComment> getPCommentById(String pcId) {
		// TODO Auto-generated method stub
		String searchQuery = query + " FROM P_COMMENT " + " WHERE P_COMMENT.pcId = ?";
		Object[] param = new Object[] {pcId};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<PComment> list = new ArrayList<PComment>();
			while (rs.next()) {
				PComment dto = new PComment();
				dto.setCommentId(rs.getString("COMMENT_ID"));
				dto.setRecommandation(rs.getDouble("COMMENT_RECOMMENDATION"));
				dto.setContent(rs.getString("COMMENT_CONTENT"));
				dto.setUserId(rs.getString("COMMENT_USER"));
				dto.setPostId(rs.getString("COMMENT_POST"));
				dto.setRegistDate(rs.getDate("COMMENT_REGISTDATE"));
				
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

	
}
