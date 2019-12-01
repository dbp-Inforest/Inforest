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
		// �⺻ ������ ������ 	
		jdbcUtil.setSql(query);		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<ProductLike> list = new ArrayList<ProductLike>();		// ProductLike ��ü���� ������� list ��ü
			while (rs.next()) {	
				ProductLike dto = new ProductLike();		// �ϳ��� ProductLike ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("PRODUCT_ID"));
				dto.setUserId("USER_ID");
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

	
	public int insertProductLike(ProductLike productLike) {
		int result = 0;
		String insertQuery = "INSERT INTO PHONE (PRODUCT_ID, USER_ID) " +
							 "VALUES (?, ?) ";
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
		Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(productLike.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
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


	
	public int deleteProductLike(int uid, int pid) { //����
		String deleteQuery = "DELETE FROM PRODUCT_LIKE WHERE USER_ID = ? AND PRODUCT_ID = ? ";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {uid, pid};
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

	public int updateProductLike(ProductLike productLike) {
		// TODO Auto-generated method stub
		return 0;
	}

}
