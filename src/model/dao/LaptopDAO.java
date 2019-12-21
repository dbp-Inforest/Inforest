package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.LaptopDAO;
import model.dto.Laptop;

public class LaptopDAO{

	private JDBCUtil jdbcUtil = null;
	
	private static String query = "SELECT L.L_PURPOSE AS LAPTOP_PURPOSE, " +
			  "        L.L_DISPLAY AS LAPTOP_DISPLAY,L.L_CPU AS LAPTOP_CPU, " +
			  "        L.L_RAM_MEMORY AS LAPTOP_RAM_MEMORY, L.L_OS AS LAPTOP_OS, " +
			  "        L.PRODUCT_ID AS LAPTOP_ID, L.L_SSD AS LAPTOP_SSD, " +
			  "		   P.NAME AS LAPTOP_NAME, P.COLOR AS LAPTOP_COLOR, " +
			  "		   P.PRICE AS LAPTOP_PRICE,  P.BRAND  AS LAPTOP_BRAND, " +
			  " 	   P.RELEASED_DATE  AS LAPTOP_RELEASED_DATE, P.WEIGHT  AS LAPTOP_WEIGHT, " +
			  "		   P.P_KIND  AS LAPTOP_KIND ";
	
	public LaptopDAO() {   
		try {
	        jdbcUtil = new JDBCUtil();   // JDBCUtil ��ü ����;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	   
	public List<Laptop> getLaptopList() {
		// �⺻ ������ ������ 
		String allQuery = query + "FROM LAPTOP l, PRODUCT P " +
											"WHERE l.PRODUCT_ID = P.PRODUCT_ID ";		
		jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<Laptop> list = new ArrayList<Laptop>();		// Laptop ��ü���� ������� list ��ü
			while (rs.next()) {	
				Laptop dto = new Laptop();		// �ϳ��� Laptop ��ü ���� �� ���� ����
				dto.setlPurpose(rs.getString("LAPTOP_PURPOSE"));
				dto.setlDisplay(rs.getString("LAPTOP_DISPLAY"));
				dto.setlCPU(rs.getString("LAPTOP_CPU"));
				dto.setlRAMMemory(rs.getString("LAPTOP_RAM_MEMORY"));
				dto.setlOS(rs.getString("LAPTOP_OS"));
				dto.setProductId(rs.getString("LAPTOP_ID"));
				dto.setlSSD(rs.getString("LAPTOP_SSD"));
				//--
				dto.setName(rs.getString("LAPTOP_NAME"));
				dto.setColor(rs.getString("LAPTOP_COLOR"));
				dto.setPrice(rs.getString("LAPTOP_PRICE"));
				dto.setBrand(rs.getString("LAPTOP_BRAND"));
				dto.setReleased_date(rs.getDate("LAPTOP_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("LAPTOP_WEIGHT"));
				dto.setpKind(rs.getInt("LAPTOP_KIND"));
				
				list.add(dto);		// list ��ü�� ������ ������ Laptop ��ü ����
			}
			return list;		// �л������� ������ dto���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;		
	}

	public int insertLaptop(Laptop laptop) {
		int result = 0;
		String insertQuery1 = "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
	 			 "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		String insertQuery2 = "INSERT INTO LAPTOP (L_PURPOSE, L_DISPLAY, L_CPU, L_RAM_MEMORY, L_OS, PRODUCT_ID, L_SSD) " +
							 "VALUES (?, ?, ?, ?, ?, ?, ?) ";
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
		Object[] param1 = new Object[] {laptop.getProductId(), laptop.getName(), laptop.getColor(), laptop.getPrice(), laptop.getBrand(), laptop.getReleased_date(), laptop.getWeight(), 1};		

		Object[] param2 = new Object[] {laptop.getlPurpose(), laptop.getlDisplay(), laptop.getlCPU(), laptop.getlRAMMemory(),
				laptop.getlOS(), laptop.getProductId(), laptop.getlSSD()};		
			
		try {				
			jdbcUtil.setSql(insertQuery1);			// JDBCUtil �� insert �� ����
			jdbcUtil.setParameters(param1);			// JDBCUtil �� �Ű����� ����
			result = jdbcUtil.executeUpdate();		// insert �� ����
			
			jdbcUtil.setSql(insertQuery2);			// JDBCUtil �� insert �� ����
			jdbcUtil.setParameters(param2);			// JDBCUtil �� �Ű����� ����
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(laptop.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
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

	public int updateLaptop(Laptop laptop) {
		
		String updateQuery1 = "UPDATE PRODUCT SET ";
		String updateQuery2 = "UPDATE LAPTOP SET ";
		int index = 0;
		Object[] tempParam = new Object[10];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		
		if (laptop.getBrand() != null) {		// �귣�尡 �����Ǿ� ���� ���
			updateQuery1 += "BRAND = ?, ";		// update ���� �귣�� ���� �κ� �߰�
			tempParam[index++] = laptop.getBrand();		// �Ű������� ������ �̸� �߰�
		}
		if (laptop.getColor() != null) {		// ������ �����Ǿ� ���� ���
			updateQuery1 += "COLOR = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = laptop.getColor();		// �Ű������� ������ ���� �߰�
		}
		if (laptop.getlCPU() != null) {		// cpu�� �����Ǿ� ���� ���
			updateQuery2 += "L_CPU = ?, ";		// update ���� cpu ���� �κ� �߰�
			tempParam[index++] = laptop.getlCPU();		// �Ű������� ������ �޴��� �߰�
		}
		if (laptop.getlDisplay() != null) {		// display�� �����Ǿ� ���� ���
			updateQuery2 += "L_DISPLAY = ?, ";		// update ���� display ���� �κ� �߰�
			tempParam[index++] = laptop.getlDisplay();		// �Ű������� ������ display �߰�
		}
		if (laptop.getlOS() != null) {		// os�� �����Ǿ� ���� ���
			updateQuery2 += "L_OS = ?, ";		// update ���� os ���� �κ� �߰�
			tempParam[index++] = laptop.getlOS();		// �Ű������� ������ os �߰�
		}
		if (laptop.getlPurpose() != null) {		// purpose�� �����Ǿ� ���� ���
			updateQuery2 += "L_PURPOSE = ?, ";		// update ���� purpose ���� �κ� �߰�
			tempParam[index++] = laptop.getlPurpose();		// �Ű������� ������ purpose �߰�
		}
		if (laptop.getlRAMMemory()!= null) {		// �޸𸮰� �����Ǿ� ���� ���
			updateQuery2 += "L_RAM_MEMORY = ?, ";		// update ���� �޸� ���� �κ� �߰�
			tempParam[index++] = laptop.getlRAMMemory();		// �Ű������� ������ �޸� �߰�
		}
		if (laptop.getlSSD() != null) {		// ssd�� �����Ǿ� ���� ���
			updateQuery2 += "L_SSD = ?, ";		// update ���� ssd ���� �κ� �߰�
			tempParam[index++] = laptop.getlSSD();		// �Ű������� ������ ssd �߰�
		}
		if (laptop.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery1 += "NAME = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam[index++] = laptop.getName();		// �Ű������� ������ �̸� �߰�
		}
		if (laptop.getpKind() == 1) {		// pKind�� �����Ǿ� ���� ���
			updateQuery1 += "P_KIND = ?, ";		// update ���� pKind ���� �κ� �߰�
			tempParam[index++] = laptop.getpKind();		// �Ű������� ������ pKind �߰�
		}
		if (laptop.getPrice() != null) {		// price�� �����Ǿ� ���� ���
			updateQuery1 += "PRICE = ?, ";		// update ���� price ���� �κ� �߰�
			tempParam[index++] = laptop.getPrice();		// �Ű������� ������ price �߰�
		}
		if (laptop.getProductId() != null) {		// id�� �����Ǿ� ���� ���
			updateQuery2 += "PRODUCT_ID = ?, ";		// update ���� id ���� �κ� �߰�
			tempParam[index++] = laptop.getProductId();		// �Ű������� ������ id �߰�
		}
		if (laptop.getReleased_date() != null) {		// ������� �����Ǿ� ���� ���
			updateQuery1 += "NAME = ?, ";		// update ���� ����� ���� �κ� �߰�
			tempParam[index++] = laptop.getReleased_date();		// �Ű������� ������ ��� �߰�
		}
		if (laptop.getWeight() > 0) {		// ���԰� �����Ǿ� ���� ���
			updateQuery1 += "WEIGHT = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = laptop.getWeight();		// �Ű������� ������ ���� �߰�
		}
		
		updateQuery1 += "WHERE PRODUCT_ID = ? ";		// update ���� ���� ����
		updateQuery1 = updateQuery1.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		updateQuery2 += "WHERE PRODUCT_ID = ? ";		// update ���� ���� ����
		updateQuery2 = updateQuery2.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam[index++] = laptop.getProductId();		// ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)		// �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSql(updateQuery1);			// JDBCUtil�� update �� ����
		jdbcUtil.setSql(updateQuery2);	
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


	public void deleteLaptop(String lProductId) {
	      String deleteQuery1 = "DELETE FROM LAPTOP WHERE PRODUCT_ID = ? ";
	      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
	      
	      try {
	         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil �� query �� ����
	         Object[] param = new Object[] { lProductId };
	         jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
	         int result = jdbcUtil.executeUpdate();      // delete �� ����
	         if( result != 0 ) {
	             System.out.println("product ���� �Ϸ�"); 
	         }
	         
	         jdbcUtil.setSql(deleteQuery2);         // JDBCUtil �� query �� ����
	         jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
	         result = jdbcUtil.executeUpdate();      // delete �� ����
	         if( result != 0 ) {
	             System.out.println("Laptop ���� �Ϸ�"); 
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


	public List<Laptop> getLaptopByName(String lName) {
		// TODO Auto-generated method stub
		String searchQuery = query  + "FROM LAPTOP l, PRODUCT P " +
				"WHERE l.PRODUCT_ID = P.PRODUCT_ID " + "AND P.Name LIKE ?";
		Object[] param = new Object[] { "%" + lName + "%" };

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param); 
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Laptop> list = new ArrayList<Laptop>();
			while (rs.next()) {
				Laptop dto = new Laptop();
				dto.setlPurpose(rs.getString("LAPTOP_PURPOSE"));
				dto.setlDisplay(rs.getString("LAPTOP_DISPLAY"));
				dto.setlCPU(rs.getString("LAPTOP_CPU"));
				dto.setlRAMMemory(rs.getString("LAPTOP_RAM_MEMORY"));
				dto.setlOS(rs.getString("LAPTOP_OS"));
				dto.setProductId(rs.getString("LAPTOP_ID"));
				dto.setlSSD(rs.getString("LAPTOP_SSD"));
				//--
				dto.setName(rs.getString("LAPTOP_NAME"));
				dto.setColor(rs.getString("LAPTOP_COLOR"));
				dto.setPrice(rs.getString("LAPTOP_PRICE"));
				dto.setBrand(rs.getString("LAPTOP_BRAND"));
				dto.setReleased_date(rs.getDate("LAPTOP_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("LAPTOP_WEIGHT"));
				dto.setpKind(rs.getInt("LAPTOP_KIND"));
				
				list.add(dto);		// list ��ü�� ������ ������ Laptop ��ü ����
			}
			return list;	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public Laptop getLaptopById(String lId) {
		// TODO Auto-generated method stub
		String searchQuery = query + "FROM LAPTOP l, PRODUCT P " +
				"WHERE l.PRODUCT_ID = P.PRODUCT_ID AND P.PRODUCT_ID = ?";
		Object[] param = new Object[] {lId};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Laptop dto = new Laptop();
			while (rs.next()) {
				dto.setlPurpose(rs.getString("LAPTOP_PURPOSE"));
				dto.setlDisplay(rs.getString("LAPTOP_DISPLAY"));
				dto.setlCPU(rs.getString("LAPTOP_CPU"));
				dto.setlRAMMemory(rs.getString("LAPTOP_RAM_MEMORY"));
				dto.setlOS(rs.getString("LAPTOP_OS"));
				dto.setProductId(rs.getString("LAPTOP_ID"));
				dto.setlSSD(rs.getString("LAPTOP_SSD"));
				//--
				dto.setName(rs.getString("LAPTOP_NAME"));
				dto.setColor(rs.getString("LAPTOP_COLOR"));
				dto.setPrice(rs.getString("LAPTOP_PRICE"));
				dto.setBrand(rs.getString("LAPTOP_BRAND"));
				dto.setReleased_date(rs.getDate("LAPTOP_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("LAPTOP_WEIGHT"));
				dto.setpKind(rs.getInt("LAPTOP_KIND"));
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
