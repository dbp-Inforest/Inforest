package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.dto.*;

import java.sql.*;

import model.dao.*;

public class TabletDAO{

	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
	
	// tablet�� �⺻ ������ �����ϴ� query��
	private static String query = "SELECT TABLET.T_BATTERY AS TABLET_BATTERY, " +
								         "TABLET.T_MEMORY AS TABLET_MEMORY, " +
								         "TABLET.T_OS AS TABLET_OS, " +
								         "TABLET.T_SIZE AS TABLET_SIZE, " +
								         "TABLET.PRODUCT_ID AS TABLET_ID, " +
								         "PRODUCT.NAME AS TABLET_NAME, " +
								         "PRODUCT.COLOR AS TABLET_COLOR, " +
								         "PRODUCT.PRICE AS TABLET_PRICE, " +
								         "PRODUCT.BRAND AS TABLET_BRAND, " +
								         "PRODUCT.RELEASED_DATE AS TABLET_RELEASED_DATE, " +
								         "PRODUCT.WEIGHT AS TABLET_WEIGHT, " +
								         "PRODUCT.P_KIND AS TABLET_KIND ";		

	// TabletDAOImpl ������
	public TabletDAO() {   
		try {
	        jdbcUtil = new JDBCUtil();   // JDBCUtil ��ü ����;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	
	// ��ü �º��� List���� ��ȯ�ϴ� �޼ҵ�
	public List<Tablet> getTabletList() {
		// PRODUCT ���̺��� ��ӹ޾ұ� ������ ��ü �º� ������ ������
		String allQuery = query + "FROM TABLET, PRODUCT "
							+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID";		
		jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<Tablet> list = new ArrayList<Tablet>();		// TabletDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				Tablet dto = new Tablet();		// �ϳ��� TabletDTO ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("TABLET_ID"));
				dto.settBattery(rs.getString("TABLET_BATTERY"));
				dto.settMemory(rs.getString("TABLET_MEMORY"));
				dto.settOS(rs.getString("TABLET_OS"));
				dto.settSize(rs.getDouble("TABLET_SIZE"));
				dto.setName(rs.getString("TABLET_NAME"));
				dto.setColor(rs.getString("TABLET_COLOR"));
				dto.setpKind(rs.getInt("TABLET_KIND"));
				dto.setPrice(rs.getString("TABLET_PRICE"));
				dto.setBrand(rs.getString("TABLET_BRAND"));
				dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
				list.add(dto);		// list ��ü�� ������ ������ TabletDTO ��ü ����
			}
			return list;		// �º� ������ ������ dto ���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}
	
	// �º��� �̸����� ������ �˻��Ͽ� �ش� �º��� ������ ���� �ִ� TabletDTO ��ü�� ��ȯ�ϴ� �޼ҵ�
	public List<Tablet> getTabletByName(String tname) {
		// �⺻ ������ ������  tname�� �����ϴ� name�� ���� tablet ������ �������� ���̺�
		String searchQuery = query + "FROM TABLET, PRODUCT "
				+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.NAME LIKE ? ";	 
		jdbcUtil.setSql(searchQuery);	// JDBCUtil �� query �� ����
		System.out.println("tname: " + tname);
		Object[] param = new Object[] { "%" + tname + "%" };		// �º��� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query���� �Ű����� ������ ����� �Ű����� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
			List<Tablet> list = new ArrayList<Tablet>();		// TabletDTO ��ü���� ������� list ��ü
			while (rs.next()) {						// ã�� ī�޶��� ������ TabletDTO ��ü�� ����
				Tablet dto = new Tablet();
				dto.setProductId(rs.getString("TABLET_ID"));
				System.out.println("dto : " + dto.getProductId());
				dto.settBattery(rs.getString("TABLET_BATTERY"));
				dto.settMemory(rs.getString("TABLET_MEMORY"));
				dto.settOS(rs.getString("TABLET_OS"));
				dto.settSize(rs.getDouble("TABLET_SIZE"));
				dto.setName(rs.getString("TABLET_NAME"));
				dto.setColor(rs.getString("TABLET_COLOR"));
				dto.setpKind(rs.getInt("TABLET_KIND"));
				dto.setPrice(rs.getString("TABLET_PRICE"));
				dto.setBrand(rs.getString("TABLET_BRAND"));
				dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
				list.add(dto);
			}
			return list;				// ã�� �л��� ������ ��� �ִ� TabletDTO ��ü ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}
		return null;
	}

	
	// �𵨸����� ������ �˻��Ͽ� �ش� �º��� ������ ���� �ִ� TabletDTO ��ü�� ��ȯ�ϴ� �޼ҵ�
	public Tablet getTabletById(String tId) {
		// �⺻ ������ ������  tId�� �����ϴ� name�� ���� tablet ������ �������� ���̺�
		String searchQuery = query + "FROM TABLET, PRODUCT "
				+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = ?"; 	 
		jdbcUtil.setSql(searchQuery);	// JDBCUtil �� query �� ����
		System.out.println("tId: " + tId);
		Object[] param = new Object[] { tId };		// �º��� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query���� �Ű����� ������ ����� �Ű����� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
			Tablet dto = new Tablet();		// TabletDTO ��ü���� ������� list ��ü
			if (rs.next()) {						// ã�� �л��� ������ TabletDTO ��ü�� ����
				dto.setProductId(rs.getString("TABLET_ID"));
				dto.settBattery(rs.getString("TABLET_BATTERY"));
				dto.settMemory(rs.getString("TABLET_MEMORY"));
				dto.settOS(rs.getString("TABLET_OS"));
				dto.settSize(rs.getDouble("TABLET_SIZE"));
				dto.setName(rs.getString("TABLET_NAME"));
				dto.setColor(rs.getString("TABLET_COLOR"));
				dto.setpKind(rs.getInt("TABLET_KIND"));
				dto.setPrice(rs.getString("TABLET_PRICE"));
				dto.setBrand(rs.getString("TABLET_BRAND"));
				dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
			}
			return dto;				// ã�� �л��� ������ ��� �ִ� TabletDTO ��ü ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}
		return null;
	}
	
	// TabletDTO ��ü�� ��� �ִ� �º��� ������ ������� �º� ������ tablet ���̺� �����ϴ� �޼ҵ�

	public int insertTablet(Tablet tablet) {
		int result = 0;
		
		String insertQuery1 = "INSERT INTO PRODUCT(PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		String insertQuery2 = "INSERT INTO TABLET (PRODUCT_ID, T_BATTERY, T_MEMORY, T_OS, T_SIZE) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) " ;
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param1 = new Object[] { tablet.getProductId(), tablet.getName(), tablet.getColor(), tablet.getPrice(), tablet.getBrand(), tablet.getReleased_date(), tablet.getWeight(), 3 };	
		Object[] param2 = new Object[] { tablet.getProductId(), tablet.gettBattery(), tablet.gettMemory(), tablet.gettOS(), tablet.gettSize(), };	
				
		try {
			jdbcUtil.setSql(insertQuery1);			// JDBCUtil �� insert �� ����
			jdbcUtil.setParameters(param1);			// JDBCUtil �� �Ű����� ����
			result = jdbcUtil.executeUpdate();		// insert �� ����
			
			jdbcUtil.setSql(insertQuery2);			// JDBCUtil �� insert �� ����
			jdbcUtil.setParameters(param2);			// JDBCUtil �� �Ű����� ����
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(tablet.getProductId() + " ��ǰ���� ������ ���ԵǾ����ϴ�.");
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
	
	// TabletDTO ��ü�� �����Ǿ� �ִ� ������ ���� ���̺��� ������ �����ϴ� �޼ҵ�

	public int updateTablet(Tablet tablet) {
		
		System.out.println("updatePhone ���͵�!!!!!");
		int index = 0;
		String updateQuery1 = "UPDATE PRODUCT SET ";
	    Object[] tempParam1 = new Object[10]; // update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
	   
		if (tablet.getProductId() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery1 += "PRODUCT_ID = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam1[index++] = tablet.getProductId();		// �Ű������� ������ �̸� �߰�
		}
		if (tablet.getBrand() != null) {		// �귣�尡 �����Ǿ� ���� ���
			updateQuery1 += "BRAND = ?, ";		// update ���� �귣�� ���� �κ� �߰�
			tempParam1[index++] = tablet.getBrand();		// �Ű������� ������ �귣�� �߰�
		}
		if (tablet.getColor() != null) {		// ������ �����Ǿ� ���� ���
			updateQuery1 += "COLOR = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam1[index++] = tablet.getColor();		// �Ű������� ������ ���� �߰�
		}
		if (tablet.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery1 += "NAME = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam1[index++] = tablet.getName();		// �Ű������� ������ �̸� �߰�
		}
		if (tablet.getpKind() == 3) {		// ������ �����Ǿ� ���� ���
			updateQuery1 += "P_KIND = ?, ";		// update���� ���� ���� �κ� �߰�
			tempParam1[index++] = tablet.getpKind();		// �Ű������� ������ ���� �߰�
		}
		if (tablet.getReleased_date() != null) {		// ������� �����Ǿ� ���� ���
			updateQuery1 += "RELEASED_DATE = ?, ";		// update���� ����� ���� �κ� �߰�
			tempParam1[index++] = tablet.getReleased_date();		// �Ű������� ������ ����� �߰�
		}
		
	    updateQuery1 += "WHERE PRODUCT_ID = ? ";      // update ���� ���� ����
	    updateQuery1 = updateQuery1.replace(", WHERE", " WHERE");      // update ���� where �� �տ� ���� �� �ִ� , ����
	    tempParam1[index++] = tablet.getProductId();      // ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
	      
	    Object[] newParam1 = new Object[index];
	    for (int i = 0; i < newParam1.length; i++)      // �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
	         newParam1[i] = tempParam1[i];
	    
	    index = 0;  
		String updateQuery2= "UPDATE TABLET SET ";
	    Object[] tempParam2 = new Object[10]; // update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		   
		if (tablet.gettBattery() != null) {		// ���͸��� �����Ǿ� ���� ���
			updateQuery2 += "T_BATTERY = ?, ";		// update ���� ���͸� ���� �κ� �߰�
			tempParam2[index++] = tablet.gettBattery();		// �Ű������� ������ ���͸� �߰�
		}
		if (tablet.gettMemory() != null) {		// �޸𸮰� �����Ǿ� ���� ���
			updateQuery2 += "T_MEMORY = ?, ";		// update ���� �޸� ���� �κ� �߰�
			tempParam2[index++] = tablet.gettMemory();		// �Ű������� ������ �޸� �߰�
		}
		if (tablet.gettOS() != null) {		// �ü���� �����Ǿ� ���� ���
			updateQuery2 += "T_OS = ?, ";		// update ���� �ü�� ���� �κ� �߰�
			tempParam2[index++] = tablet.gettOS();		// �Ű������� ������ �ü�� �߰�
		}
		if (tablet.gettSize() >= 0) {		// ũ�Ⱑ �����Ǿ� ���� ���
			updateQuery2 += "T_SIZE = ?, ";		// update ���� ũ�� ���� �κ� �߰�
			tempParam2[index++] = tablet.gettSize();		// �Ű������� ������ ũ�� �߰�
		}
		if (tablet.getWeight() >= 0) {		// ���԰� �����Ǿ� ���� ���
			updateQuery2 += "WEIGHT = ? ";		// update ���� ���� ���� �κ� �߰�
			tempParam2[index++] = tablet.getWeight();		// �Ű������� ������ ���� �߰�
		}
		
		updateQuery2 += " WHERE PRODUCT_ID = ? ";		// update ���� ���� ����
		updateQuery2 = updateQuery2.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam2[index++] = tablet.getProductId();		// ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)		// �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
			newParam[i] = tempParam2[i];
		
		jdbcUtil.setSql(updateQuery2);			// JDBCUtil�� update �� ����
		jdbcUtil.setParameters(newParam);		// JDBCUtil �� �Ű����� ����
		
		try {
			int result = jdbcUtil.executeUpdate();		// update �� ����
			return result;			// update �� ���� �ݿ��� ���ڵ� �� ��ȯ
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return 0;
	}

	// product_id�� ���޹޾� �ش� �º��� ������ �����ϴ� �޼ҵ�
	public void deleteTablet(String tId) {
	      String deleteQuery1 = "DELETE FROM TABLET WHERE PRODUCT_ID = ? ";
	      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
	      
	      try {
	         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil �� query �� ����
	         Object[] param = new Object[] { tId };
	         jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
	         int result = jdbcUtil.executeUpdate();      // delete �� ����
	         if( result != 0 ) {
	             System.out.println("product ���� �Ϸ�"); 
	         }
	         
	         jdbcUtil.setSql(deleteQuery2);         // JDBCUtil �� query �� ����
	         jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
	         result = jdbcUtil.executeUpdate();      // delete �� ����
	         if( result != 0 ) {
	             System.out.println("phone ���� �Ϸ�"); 
	         }
	         return; // delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
	      } catch (Exception ex) {
	         jdbcUtil.rollback();
	         ex.printStackTrace();      
	      } finally {
	         jdbcUtil.commit();
	         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
	      }
	      return;
	}
	
	
	
	public String getBrandById(String tId) {
	       // TODO Auto-generated method stub
	       String searchQuery = query + "FROM TABLET, PRODUCT "
					+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = ?";   
	       
	       jdbcUtil.setSql(searchQuery);
		     
	       Object[] param = new Object[] {tId};
	       jdbcUtil.setParameters(param);
	    
	       try {
	          ResultSet rs = jdbcUtil.executeQuery();
	          String brand = null;
	          System.out.println("TABLETDAO");
	          if (rs.next()) { 
	             brand = rs.getString("TABLET_BRAND");
	             System.out.println("������� ����");
	          }else {
	          	 System.out.println("No ResultSet"); 
	          }
	               
	          return brand;   
	       } catch (Exception ex) {
	          ex.printStackTrace();
	       } finally {
	          jdbcUtil.close();
	       }
	       return null;
	   }
	
	
	
	public List<Product> getProductByBrand(String brand) {	   
		   String searchQuery = query + "FROM TABLET, PRODUCT "
					+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.BRAND = ?";
				   
	       
	       jdbcUtil.setSql(searchQuery);
	       Object[] param = new Object[] {brand};
	       jdbcUtil.setParameters(param);
	    
	       try {
	           ResultSet rs = jdbcUtil.executeQuery();
	           List<Product> productList = new ArrayList<Product>();
	           while (rs.next()) {
	        	  Product dto = new Product();
	              dto.setProductId(rs.getString("TABLET_ID"));
	              dto.setName(rs.getString("TABLET_NAME"));
	              dto.setColor(rs.getString("TABLET_COLOR"));
	              dto.setPrice(rs.getString("TABLET_PRICE"));
	              dto.setBrand(rs.getString("TABLET_BRAND"));
	              dto.setReleased_date(rs.getDate("TABLET_RELEASED_DATE"));
	              dto.setWeight(rs.getDouble("TABLET_WEIGHT"));
	              dto.setpKind(rs.getInt("TABLET_KIND"));
	              
	              productList.add(dto);
	           }
	           return productList;
	        } catch (Exception ex) {
	           ex.printStackTrace();
	        } finally {
	           jdbcUtil.close();
	        }
	        return null;
	   }
}
