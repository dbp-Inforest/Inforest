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
		// �⺻ ������ ������ 
		String allQuery = query  + "FROM P_COMMENT ";
		jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<PComment> list = new ArrayList<PComment>();		// LaptopDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				PComment dto = new PComment();		// �ϳ��� LaptopDTO ��ü ���� �� ���� ����
				dto.setCommentId(rs.getString("COMMENT_ID"));
				dto.setRecommandation(rs.getDouble("COMMENT_RECOMMENDATION"));
				dto.setContent(rs.getString("COMMENT_CONTENT"));
				dto.setUserId(rs.getString("COMMENT_USER"));
				dto.setPostId(rs.getString("COMMENT_POST"));
				dto.setRegistDate(rs.getDate("COMMENT_REGISTDATE"));
				
				list.add(dto);		// list ��ü�� ������ ������ LaptopDTO ��ü ����
			}
			return list;		// �л������� ������ dto���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}

	public int insertPComment(PComment pComment) {
		
		int result = 0;
		
		String insertQuery = "INSERT INTO PHONE (COMMENT_ID, RECOMMENDATION, CONTENT, USER_ID, POST_ID, REGIST_DATE) " +
							 "VALUES (?, ?, ?, ?, ?, ?) ";
		insertQuery += "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
				 "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
	
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
		Object[] param = new Object[] {pComment.getCommentId(), pComment.getRecommandation(), pComment.getContent(), pComment.getUserId(), 
							pComment.getPostId(), pComment.getRegistDate(), pComment.getProductId(), pComment.getName(), pComment.getColor(), pComment.getPrice(),
		 					pComment.getBrand(), pComment.getReleased_date(), pComment.getWeight(), pComment.getpKind()};
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(pComment.getCommentId() + " ��ǰ������ ���ԵǾ����ϴ�.");
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ ��ǰ������ �̹� �����մϴ�."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
	}

	public int updatePComment(PComment pComment) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deletePComment(int pcProductId) {
		String deleteQuery = "DELETE FROM P_COMMENT WHERE pcProductId = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {pcProductId};
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete �� ����
			return result;						// delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
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
				
				list.add(dto);		// list ��ü�� ������ ������ LaptopDTO ��ü ����
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
