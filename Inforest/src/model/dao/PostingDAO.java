package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.dao.PostingDAO;
import model.dto.Posting;

public class PostingDAO{
	
	private JDBCUtil jdbcUtil = null;

	private static String query = "SELECT POSTING.POST_ID, " +
								  "       POSTING.TITLE, " +
								  "       POSTING.REGIST_DATE, " +
								  "       POSTING.P_VIEW, " +
								  "       POSTING.PRODUCT_ID, " +
								  "       POSTING.USER_ID " +
								  "FROM POSTING ";
	
	
	public List<Posting> getPostingList() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public int deletePosting(int poId) {
		String deleteQuery = "DELETE FROM POSTING WHERE POST_ID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { poId };
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

	
	public Posting getPostingByName(String poName) {
		// TODO Auto-generated method stub
		String searchQuery = query + "WHERE POSTING.TITLE = ?";
		Object[] param = new Object[] { poName };

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Posting dto = new Posting();
			while (rs.next()) {
				dto.setProductId(rs.getString("POST_ID"));
				dto.setPostId(rs.getString("TITLE"));
				dto.setTitle(rs.getString("REGIST_DATE"));
				dto.setRegistDate(rs.getDate("P_VIEW"));
				dto.setpView(rs.getInt("Posting_pView"));
				dto.setUserId(rs.getString("Posting_UserId"));
				//POST_ID, TITLE, REGIST_DATE, P_VIEW, PRODUCT_ID, USER_ID
			}
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;

	}
}
