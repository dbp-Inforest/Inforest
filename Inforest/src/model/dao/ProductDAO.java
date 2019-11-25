package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.model.dto.*;
public class ProductDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ProductDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	
	/*  ViewProductController���� �˻� ��ɿ� ����� ���� 
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� Product ������ Ŭ������ 
	 * �����Ͽ� ��ȯ
	public Product findProduct(String ProductId) throws SQLException {
        String sql = "SELECT password, name, email, phone, commId, cName "
        			+ "FROM ProductINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        			+ "WHERE Productid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ProductId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				Product Product = new Product(		// Product ��ü�� �����Ͽ� �л� ������ ����
					ProductId,
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getInt("commId"),					
					rs.getString("cName"));
				return Product;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
   */
   /*
	//��ü product������ �˻��Ͽ� list�� ���� �� ��ȯ 
	public List<ProductDTO> findProductList() throws SQLException {
        String sql = "SELECT ProductId, name, email, NVL(commId,0) AS commId, cName " 
        		   + "FROM ProductINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        		   + "ORDER BY ProductId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Product> ProductList = new ArrayList<Product>();	// Product���� ����Ʈ ����
			while (rs.next()) {
				Product Product = new Product(			// Product ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("ProductId"),
					null,
					rs.getString("name"),
					rs.getString("email"),
					null,
					rs.getInt("commId"),
					rs.getString("cName"));
				ProductList.add(Product);				// List�� Product ��ü ����
			}		
			return ProductList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<Product> findProductList(int currentPage, int countPerPage) throws SQLException {
		/*
		String sql = "SELECT ProductId, name, email, NVL(commId, 0), cName " 
					+ "FROM ProductINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
					+ "ORDER BY ProductId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Product> ProductList = new ArrayList<Product>();	// Product���� ����Ʈ ����
				do {
					Product Product = new Product(			// Product ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("ProductId"),
						null,
						rs.getString("name"),
						rs.getString("email"),
						null,
						rs.getInt("commId"),
						rs.getString("cName"));
					ProductList.add(Product);							// ����Ʈ�� Product ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return ProductList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * Ư�� Ŀ�´�Ƽ�� ���� ����ڵ��� �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Product> findProductsInCommunity(int communityId) throws SQLException {
        String sql = "SELECT ProductId, name FROM ProductInfo "
     				+ "WHERE commId = ?";                         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			List<Product> memList = new ArrayList<Product>();	// member���� ����Ʈ ����
			while (rs.next()) {
				Product member = new Product(			// Product ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("ProductId"),
					rs.getString("name"));
				memList.add(member);			// List�� Community ��ü ����
			}		
			return memList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * Ư�� Ŀ�´�Ƽ�� ���� ����ڵ��� ���� count�Ͽ� ��ȯ
	 */
	public int getNumberOfProductsInCommunity(int communityId) {
		String sql = "SELECT COUNT(ProductId) FROM ProductInfo "
     				+ "WHERE commId = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingProduct(String ProductId) throws SQLException {
		String sql = "SELECT count(*) FROM ProductINFO WHERE Productid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ProductId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}




}
