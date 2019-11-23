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
	
	public List<Laptop> getLaptopList() {
		// �⺻ ������ ������ 
		String allQuery = query + ", " + "FROM LAPTOP l, PRODUCT P " +
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
		String insertQuery = "INSERT INTO LAPTOP (L_PURPOSE, L_DISPLAY, L_CPU, L_RAM_MEMORY, L_OS, PRODUCT_ID, L_SSD) " +
							 "VALUES (?, ?, ?, ?, ?, ?, ?) ";
		insertQuery += "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
				 "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
		Object[] param = new Object[] {laptop.getlPurpose(), laptop.getlDisplay(), laptop.getlCPU(), laptop.getlRAMMemory(),
				laptop.getlOS(), laptop.getProductId(), laptop.getlSSD(), laptop.getProductId(), laptop.getName(),
				laptop.getColor(), laptop.getPrice(), laptop.getBrand(), laptop.getReleased_date(), laptop.getWeight(), 1};		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
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
		
		String updateQuery = "UPDATE LAPTOP SET ";
		int index = 0;
		Object[] tempParam = new Object[10];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		
		if (laptop.getBrand() != null) {		// �귣�尡 �����Ǿ� ���� ���
			updateQuery += "LAPTOP_BRAND = ?, ";		// update ���� �귣�� ���� �κ� �߰�
			tempParam[index++] = laptop.getBrand();		// �Ű������� ������ �̸� �߰�
		}
		if (laptop.getColor() != null) {		// ������ �����Ǿ� ���� ���
			updateQuery += "LAPTOP_COLOR = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = laptop.getColor();		// �Ű������� ������ ���� �߰�
		}
		if (laptop.getlCPU() != null) {		// cpu�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_CPU = ?, ";		// update ���� cpu ���� �κ� �߰�
			tempParam[index++] = laptop.getlCPU();		// �Ű������� ������ �޴��� �߰�
		}
		if (laptop.getlDisplay() != null) {		// display�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_DISPLAY = ?, ";		// update ���� display ���� �κ� �߰�
			tempParam[index++] = laptop.getlDisplay();		// �Ű������� ������ display �߰�
		}
		if (laptop.getlOS() != null) {		// os�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_OS = ?, ";		// update ���� os ���� �κ� �߰�
			tempParam[index++] = laptop.getlOS();		// �Ű������� ������ os �߰�
		}
		if (laptop.getlPurpose() != null) {		// purpose�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_PURPOSE = ?, ";		// update ���� purpose ���� �κ� �߰�
			tempParam[index++] = laptop.getlPurpose();		// �Ű������� ������ purpose �߰�
		}
		if (laptop.getlRAMMemory()!= null) {		// �޸𸮰� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_RAM_MEMORY = ?, ";		// update ���� �޸� ���� �κ� �߰�
			tempParam[index++] = laptop.getlRAMMemory();		// �Ű������� ������ �޸� �߰�
		}
		if (laptop.getlSSD() != null) {		// ssd�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_SSD = ?, ";		// update ���� ssd ���� �κ� �߰�
			tempParam[index++] = laptop.getlSSD();		// �Ű������� ������ ssd �߰�
		}
		if (laptop.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_NAME = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam[index++] = laptop.getName();		// �Ű������� ������ �̸� �߰�
		}
		if (laptop.getpKind() == 1) {		// pKind�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_KIND = ?, ";		// update ���� pKind ���� �κ� �߰�
			tempParam[index++] = laptop.getpKind();		// �Ű������� ������ pKind �߰�
		}
		if (laptop.getPrice() != null) {		// price�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_PRICE = ?, ";		// update ���� price ���� �κ� �߰�
			tempParam[index++] = laptop.getPrice();		// �Ű������� ������ price �߰�
		}
		if (laptop.getProductId() != null) {		// id�� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_ID = ?, ";		// update ���� id ���� �κ� �߰�
			tempParam[index++] = laptop.getProductId();		// �Ű������� ������ id �߰�
		}
		if (laptop.getReleased_date() != null) {		// ������� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_NAME = ?, ";		// update ���� ����� ���� �κ� �߰�
			tempParam[index++] = laptop.getReleased_date();		// �Ű������� ������ ��� �߰�
		}
		
		if (laptop.getWeight() > 0) {		// ���԰� �����Ǿ� ���� ���
			updateQuery += "LAPTOP_WEIGHT = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = laptop.getWeight();		// �Ű������� ������ ���� �߰�
		}
		
		updateQuery += "WHERE LAPTOP_ID = ? ";		// update ���� ���� ����
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam[index++] = laptop.getProductId();		// ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)		// �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSql(updateQuery);			// JDBCUtil�� update �� ����
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


	public int deleteLaptop(int lProductId) {
		String deleteQuery = "DELETE FROM LAPTOP WHERE LAPTOP_ID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {lProductId};
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


	public List<Laptop> getLaptopByName(String lName) {
		// TODO Auto-generated method stub
		String searchQuery = query + ", " + "FROM LAPTOP l, PRODUCT P " +
				"WHERE l.PRODUCT_ID = P.PRODUCT_ID " + "AND LAPTOP.lName = ?";
		Object[] param = new Object[] {lName};

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

	public List<Laptop> getLaptopById(String lId) {
		// TODO Auto-generated method stub
		String searchQuery = query + ", " + "FROM LAPTOP l, PRODUCT P " +
				"WHERE l.PRODUCT_ID = P.PRODUCT_ID " + "AND LAPTOP.lId = ?";
		Object[] param = new Object[] {lId};

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


}
