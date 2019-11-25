package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.CameraDAO;
import model.dto.Camera;

public class CameraDAO {

	private JDBCUtil jdbcUtil = null;

	private static String query = "SELECT CAMERA.C_DISPLAY AS CAMERA_DISPLAY, " +
	         "CAMERA.C_PIXEL AS CAMERA_PIXEL, " +
	         "CAMERA.C_BATTERY AS CAMERA_BATTERY, " +
	         "CAMERA.C_VIBRATION AS CAMERA_VIBRATION, " +
	         "CAMERA.C_BURSTSHOT AS CAMERA_BURSTSHOT, " +
	         "CAMERA.C_LENS AS CAMERA_LENS, " +
	         "CAMERA.PRODUCT_ID AS CAMERA_ID, " +
	         "PRODUCT.NAME AS CAMERA_NAME, " +
	         "PRODUCT.COLOR AS CAMERA_COLOR, " +
	         "PRODUCT.PRICE AS CAMERA_PRICE, " +
	         "PRODUCT.BRAND AS CAMERA_BRAND, " +
	         "PRODUCT.RELEASED_DATE AS CAMERA_RELEASED_DATE, " +
	         "PRODUCT.WEIGHT AS CAMERA_WEIGHT, " +
	         "PRODUCT.P_KIND AS CAMERA_KIND ";		
	
	public CameraDAO() {   
		try {
	        jdbcUtil = new JDBCUtil();   // JDBCUtil ��ü ����;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	
	public List<Camera> getCameraList() {
		// PRODUCT ���̺��� ��ӹ޾ұ� ������ ��ü �º� ������ ������
		String allQuery = query + "FROM CAMERA, PRODUCT "
							+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID";	
		
		jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<Camera> list = new ArrayList<Camera>();		// Camera ��ü���� ������� list ��ü
	
			while (rs.next()) {	
				Camera dto = new Camera();		// �ϳ��� Camera ��ü ���� �� ���� ����
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setName(rs.getString("CAMERA_NAME"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
				
				list.add(dto);		// list ��ü�� ������ ������ Camera ��ü ����
			}
			return list;		// ī�޶� ������ ������ dto ���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}

	// Camera ��ü�� ��� �ִ� �º��� ������ ������� �º� ������ Camera ���̺� �����ϴ� �޼ҵ�
	public int insertCamera(Camera Camera) {
		int result = 0;
		// Ʈ������? ����ؾ��ϳ�
		String insertQuery = "INSERT INTO Camera (PRODUCT_ID, C_DISPLAY, C_PIXEL, C_BATTERY, C_VIBRATION, C_BURSTSHOT, C_LENS) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) "  
				+ "INSERT INTO PRODUCT(PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param = new Object[] { Camera.getProductId(), Camera.getcDisplay(), Camera.getcPixel(), Camera.getcBattery(), Camera.getcVibration(), Camera.getcBurstshot(), Camera.getcLens(),
				Camera.getProductId(), Camera.getName(), Camera.getColor(), Camera.getPrice(), Camera.getBrand(), Camera.getReleased_date(), Camera.getWeight(), 2 };	
		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(Camera.getProductId() + " ��ǰ���� ������ ���ԵǾ����ϴ�.");
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
	
	// Camera ��ü�� �����Ǿ� �ִ� ������ ���� ���̺��� ������ �����ϴ� �޼ҵ�
	
	public int updateCamera(Camera camera) {
		
		String updateQuery = "UPDATE Camera SET ";
		int index = 0;
		Object[] tempParam = new Object[15];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		
		if (camera.getProductId() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "PRODUCT_ID = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam[index++] = camera.getProductId();		// �Ű������� ������ �̸� �߰�
		}
		if (camera.getBrand() != null) {		// �귣�尡 �����Ǿ� ���� ���
			updateQuery += "BRAND = ?, ";		// update ���� �귣�� ���� �κ� �߰�
			tempParam[index++] = camera.getBrand();		// �Ű������� ������ �귣�� �߰�
		}
		if (camera.getColor() != null) {		// ������ �����Ǿ� ���� ���
			updateQuery += "COLOR = ?, ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = camera.getColor();		// �Ű������� ������ ���� �߰�
		}
		if (camera.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "NAME = ?, ";		// update ���� �̸� ���� �κ� �߰�
			tempParam[index++] = camera.getName();		// �Ű������� ������ �̸� �߰�
		}
		if (camera.getpKind() == 3) {		// ������ �����Ǿ� ���� ���
			updateQuery += "P_KIND = ?, ";		// update���� ���� ���� �κ� �߰�
			tempParam[index++] = camera.getpKind();		// �Ű������� ������ ���� �߰�
		}
		if (camera.getReleased_date() != null) {		// ������� �����Ǿ� ���� ���
			updateQuery += "RELEASED_DATE = ?, ";		// update���� ����� ���� �κ� �߰�
			tempParam[index++] = camera.getReleased_date();		// �Ű������� ������ ����� �߰�
		}
		if (camera.getcDisplay() >= 0) {		// ���÷��̰� �����Ǿ� ���� ���
			updateQuery += "C_DISPLAY = ?, ";		// update ���� ���÷��� ���� �κ� �߰�
			tempParam[index++] = camera.getcDisplay();		// �Ű������� ������ ���÷��� �߰�
		}
		if (camera.getcPixel() >= 0) {		// �ȼ��� �����Ǿ� ���� ���
			updateQuery += "C_PIXEL = ?, ";		// update ���� �ȼ� ���� �κ� �߰�
			tempParam[index++] = camera.getcPixel();		// �Ű������� ������ �ȼ� �߰�
		}
		if (camera.getcBattery() != null) {		// ���͸��� �����Ǿ� ���� ���
			updateQuery += "C_BATTERY = ?, ";		// update ���� ���͸� ���� �κ� �߰�
			tempParam[index++] = camera.getcBattery();		// �Ű������� ������ ���͸� �߰�
		}
		if (camera.getcVibration() != null) {		// ���̺극�̼��� �����Ǿ� ���� ���
			updateQuery += "C_VIBRATION = ?, ";		// update ���� ���̺극�̼� ���� �κ� �߰�
			tempParam[index++] = camera.getcVibration();		// �Ű������� ������ ���̺극�̼� �߰�
		}
		if (camera.getcBurstshot() >= 0) {		// ����Ʈ���� �����Ǿ� ���� ���
			updateQuery += "C_BURSTSHOT = ?, ";		// update ���� ����Ʈ�� ���� �κ� �߰�
			tempParam[index++] = camera.getcBurstshot();		// �Ű������� ������ ����Ʈ�� �߰�
		}
		if (camera.getcLens() != null) {		// ��� �����Ǿ� ���� ���
			updateQuery += "C_LENS = ? ";		// update ���� ���� ���� �κ� �߰�
			tempParam[index++] = camera.getcLens();		// �Ű������� ������ ���� �߰�
		}
		
		updateQuery += "WHERE PRODUCT_ID = ? ";		// update ���� ���� ����
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam[index++] = camera.getProductId();		// ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
		
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

	
	// product_id�� ���޹޾� �ش� ī�޶��� ������ �����ϴ� �޼ҵ�
	public int deleteCamera(String cId) {
		String deleteQuery = "DELETE FROM CAMERA WHERE PRODUCT_ID = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] { cId };
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


	// ī�޶��� �̸����� ������ �˻��Ͽ� �ش� ī�޶��� ������ ���� �ִ� Camera ��ü�� ��ȯ�ϴ� �޼ҵ�
	public List<Camera> getCameraByName(String cName) {
		// �⺻ ������ ������  cName�� �����ϴ� name�� ���� Camera ������ �������� ���̺�
		String searchQuery = query + "FROM CAMERA, PRODUCT "
				+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.NAME LIKE ?";
		
		Object[] param = new Object[] { "%" + cName + "%"};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Camera> list = new ArrayList<Camera>();
			while (rs.next()) { 
				// ã�� ī�޶��� ������ Camera ��ü�� ����
				Camera dto = new Camera();
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setName(rs.getString("CAMERA_NAME"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
				list.add(dto);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}


	// �𵨸����� ������ �˻��Ͽ� �ش� �º��� ������ ���� �ִ� Camera ��ü�� ��ȯ�ϴ� �޼ҵ�
	public Camera getCameraById(String cId) {
		// �⺻ ������ ������  tId�� �����ϴ� name�� ���� CAMERA ������ �������� ���̺�
		String searchQuery = query + "FROM CAMERA, PRODUCT "
				+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = ? ";
		
		jdbcUtil.setSql(searchQuery);	// JDBCUtil �� query �� ����
		Object[] param = new Object[] { ("%" + cId + "%") };		// �º��� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query���� �Ű����� ������ ����� �Ű����� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
			Camera dto = new Camera();		// Camera ��ü���� ������� list ��ü
			while (rs.next()) {						// ã�� �л��� ������ Camera ��ü�� ����
				dto.setProductId(rs.getString("CAMERA_ID"));
				dto.setcBattery(rs.getString("CAMERA_BATTERY"));
				dto.setcBurstshot(rs.getDouble("CAMERA_BURSTSHOT"));
				dto.setcDisplay(rs.getDouble("CAMERA_DISPLAY"));
				dto.setcLens(rs.getString("CAMERA_LENS"));
				dto.setcPixel(rs.getDouble("CAMERA_PIXEL"));
				dto.setcVibration(rs.getString("CAMERA_VIBRATION"));
				dto.setName(rs.getString("CAMERA_NAME"));
				dto.setColor(rs.getString("CAMERA_COLOR"));
				dto.setpKind(rs.getInt("CAMERA_KIND"));
				dto.setPrice(rs.getString("CAMERA_PRICE"));
				dto.setBrand(rs.getString("CAMERA_BRAND"));
				dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
				dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
			}
			return dto;				// ã�� �л��� ������ ��� �ִ� Camera ��ü ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}
		return null;
	}
	
	
}
