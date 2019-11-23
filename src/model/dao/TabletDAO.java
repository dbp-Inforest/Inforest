package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.DAOFactory;
import model.dao.TabletDAO;
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
		jdbcUtil = new JDBCUtil();		// TabletDAOImpl ��ü ���� �� JDBCUtil ��ü ����
	}
	
	// ��ü �º��� List���� ��ȯ�ϴ� �޼ҵ�
	public List<Tablet> getTabletList() {
		// PRODUCT ���̺��� ��ӹ޾ұ� ������ ��ü �º� ������ ������
		String allQuery = query + ", " + "FROM TABLET, PRODUCT"
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
		String searchQuery = query + ", " + "FROM TABLET, PRODUCT"
				+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID "
				+ "AND PRODUCT.NAME = ? ";	 
		jdbcUtil.setSql(searchQuery);	// JDBCUtil �� query �� ����
		Object[] param = new Object[] { ("%" + tname + "%") };		// �º��� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query���� �Ű����� ������ ����� �Ű����� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
			List<Tablet> list = new ArrayList<Tablet>();		// TabletDTO ��ü���� ������� list ��ü
			if (rs.next()) {						// ã�� ī�޶��� ������ TabletDTO ��ü�� ����
				Tablet dto = new Tablet();
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
	public List<Tablet> getTabletById(String tId) {
		// �⺻ ������ ������  tId�� �����ϴ� name�� ���� tablet ������ �������� ���̺�
		String searchQuery = query + ", " + "FROM TABLET, PRODUCT"
				+ "WHERE TABLET.PRODUCT_ID = PRODUCT.PRODUCT_ID "
				+ "AND PRODUCT.PRODUCT_ID = ? ";	 
		jdbcUtil.setSql(searchQuery);	// JDBCUtil �� query �� ����
		Object[] param = new Object[] { ("%" + tId + "%") };		// �º��� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query���� �Ű����� ������ ����� �Ű����� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
			List<Tablet> list = new ArrayList<Tablet>();		// TabletDTO ��ü���� ������� list ��ü
			if (rs.next()) {						// ã�� �л��� ������ TabletDTO ��ü�� ����
				Tablet dto = new Tablet();
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
	
	// TabletDTO ��ü�� ��� �ִ� �º��� ������ ������� �º� ������ tablet ���̺� �����ϴ� �޼ҵ�

	public int insertTablet(Tablet tablet) {
		int result = 0;
		String insertQuery = "INSERT INTO TABLET (PRODUCT_ID, T_BATTERY, T_MEMORY, T_OS, T_SIZE) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) " 
				+ "INSERT INTO PRODUCT(PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param = new Object[] { tablet.getProductId(), tablet.gettBattery(), tablet.gettMemory(), tablet.gettOS(), tablet.gettSize(),
				tablet.getProductId(), tablet.getName(), tablet.getColor(), tablet.getPrice(), tablet.getBrand(), tablet.getReleased_date(), tablet.getWeight(), 3 };	
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
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
		
		String updateQuery = "UPDATE TABLET SET ";
		int index = 0;
		Object[] tempParam = new Object[15];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		
		if (tablet.getProductId() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "PRODUCT_ID = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam[index++] = tablet.getProductId();		// �Ű������� ������ �̸� �߰�
		}
		if (tablet.getBrand() != null) {		// �귣�尡 �����Ǿ� ���� ���
			updateQuery += "BRAND = ?, ";		// update ���� �귣�� ���� �κ� �߰�
			tempParam[index++] = tablet.getBrand();		// �Ű������� ������ �귣�� �߰�
		}
		if (tablet.getColor() != null) {		// ������ �����Ǿ� ���� ���
			updateQuery += "COLOR = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = tablet.getColor();		// �Ű������� ������ ���� �߰�
		}
		if (tablet.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "NAME = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam[index++] = tablet.getName();		// �Ű������� ������ �̸� �߰�
		}
		if (tablet.getpKind() == 3) {		// ������ �����Ǿ� ���� ���
			updateQuery += "P_KIND = ?, ";		// update���� ���� ���� �κ� �߰�
			tempParam[index++] = tablet.getpKind();		// �Ű������� ������ ���� �߰�
		}
		if (tablet.getReleased_date() != null) {		// ������� �����Ǿ� ���� ���
			updateQuery += "RELEASED_DATE = ?, ";		// update���� ����� ���� �κ� �߰�
			tempParam[index++] = tablet.getReleased_date();		// �Ű������� ������ ����� �߰�
		}
		if (tablet.gettBattery() != null) {		// ���͸��� �����Ǿ� ���� ���
			updateQuery += "T_BATTERY = ?, ";		// update ���� ���͸� ���� �κ� �߰�
			tempParam[index++] = tablet.gettBattery();		// �Ű������� ������ ���͸� �߰�
		}
		if (tablet.gettMemory() != null) {		// �޸𸮰� �����Ǿ� ���� ���
			updateQuery += "T_MEMORY = ?, ";		// update ���� �޸� ���� �κ� �߰�
			tempParam[index++] = tablet.gettMemory();		// �Ű������� ������ �޸� �߰�
		}
		if (tablet.gettOS() != null) {		// �ü���� �����Ǿ� ���� ���
			updateQuery += "T_OS = ?, ";		// update ���� �ü�� ���� �κ� �߰�
			tempParam[index++] = tablet.gettOS();		// �Ű������� ������ �ü�� �߰�
		}
		if (tablet.gettSize() >= 0) {		// ũ�Ⱑ �����Ǿ� ���� ���
			updateQuery += "T_SIZE = ?, ";		// update ���� ũ�� ���� �κ� �߰�
			tempParam[index++] = tablet.gettSize();		// �Ű������� ������ ũ�� �߰�
		}
		if (tablet.getWeight() >= 0) {		// ���԰� �����Ǿ� ���� ���
			updateQuery += "WEIGHT = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = tablet.getWeight();		// �Ű������� ������ ���� �߰�
		}
		
		updateQuery += "WHERE PRODUCT_ID = ? ";		// update ���� ���� ����
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam[index++] = tablet.getProductId();		// ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
		
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

	// product_id�� ���޹޾� �ش� �º��� ������ �����ϴ� �޼ҵ�
	public int deleteTablet(String tId) {
		String deleteQuery = "DELETE FROM TABLET WHERE PRODUCT_ID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����

		Object[] param = new Object[] { tId };
 
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

}
