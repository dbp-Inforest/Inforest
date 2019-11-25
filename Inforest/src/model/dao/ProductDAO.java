package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.model.dto.*;
public class ProductDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ProductDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	
	/*  ViewProductController에서 검색 기능에 써먹을 예정 
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 Product 도메인 클래스에 
	 * 저장하여 반환
	public Product findProduct(String ProductId) throws SQLException {
        String sql = "SELECT password, name, email, phone, commId, cName "
        			+ "FROM ProductINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        			+ "WHERE Productid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ProductId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Product Product = new Product(		// Product 객체를 생성하여 학생 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
   */
   /*
	//전체 product정보를 검색하여 list에 저장 및 반환 
	public List<ProductDTO> findProductList() throws SQLException {
        String sql = "SELECT ProductId, name, email, NVL(commId,0) AS commId, cName " 
        		   + "FROM ProductINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        		   + "ORDER BY ProductId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Product> ProductList = new ArrayList<Product>();	// Product들의 리스트 생성
			while (rs.next()) {
				Product Product = new Product(			// Product 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("ProductId"),
					null,
					rs.getString("name"),
					rs.getString("email"),
					null,
					rs.getInt("commId"),
					rs.getString("cName"));
				ProductList.add(Product);				// List에 Product 객체 저장
			}		
			return ProductList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<Product> findProductList(int currentPage, int countPerPage) throws SQLException {
		/*
		String sql = "SELECT ProductId, name, email, NVL(commId, 0), cName " 
					+ "FROM ProductINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
					+ "ORDER BY ProductId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Product> ProductList = new ArrayList<Product>();	// Product들의 리스트 생성
				do {
					Product Product = new Product(			// Product 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("ProductId"),
						null,
						rs.getString("name"),
						rs.getString("email"),
						null,
						rs.getInt("commId"),
						rs.getString("cName"));
					ProductList.add(Product);							// 리스트에 Product 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return ProductList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 특정 커뮤니티에 속한 사용자들을 검색하여 List에 저장 및 반환
	 */
	public List<Product> findProductsInCommunity(int communityId) throws SQLException {
        String sql = "SELECT ProductId, name FROM ProductInfo "
     				+ "WHERE commId = ?";                         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<Product> memList = new ArrayList<Product>();	// member들의 리스트 생성
			while (rs.next()) {
				Product member = new Product(			// Product 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("ProductId"),
					rs.getString("name"));
				memList.add(member);			// List에 Community 객체 저장
			}		
			return memList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 특정 커뮤니티에 속한 사용자들의 수를 count하여 반환
	 */
	public int getNumberOfProductsInCommunity(int communityId) {
		String sql = "SELECT COUNT(ProductId) FROM ProductInfo "
     				+ "WHERE commId = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return 0;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingProduct(String ProductId) throws SQLException {
		String sql = "SELECT count(*) FROM ProductINFO WHERE Productid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ProductId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}




}
