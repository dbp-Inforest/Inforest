package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.CameraDAO;
import model.dto.Camera;
import model.dto.Product;

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
				dto.setcPixel(rs.getInt("CAMERA_PIXEL"));
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

	       String insertQuery1 = "INSERT INTO Camera (PRODUCT_ID, C_DISPLAY, C_PIXEL, C_BATTERY, C_VIBRATION, C_BURSTSHOT, C_LENS) "
	               + " VALUES (?, ?, ?, ?, ?, ?, ?) ";
	         
	       String insertQuery2 = "INSERT INTO PRODUCT(PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND)"
	               + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	      
	         
	      // query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
	      Object[] param1 = new Object[] { Camera.getProductId(), Camera.getcDisplay(), Camera.getcPixel(), Camera.getcBattery(), Camera.getcVibration(), Camera.getcBurstshot(), Camera.getcLens() };
	       Object[] param2 = new Object[] { Camera.getProductId(), Camera.getName(), Camera.getColor(), Camera.getPrice(), Camera.getBrand(), Camera.getReleased_date(), Camera.getWeight(), 2 };     
	       
	       try {    
	            jdbcUtil.setSql(insertQuery2);          // JDBCUtil �� insert �� ����
	            jdbcUtil.setParameters(param2);        // JDBCUtil �� �Ű����� ����
	            result = jdbcUtil.executeUpdate();      // insert �� ����
	            
	            jdbcUtil.setSql(insertQuery1);         // JDBCUtil �� insert �� ����
	            jdbcUtil.setParameters(param1);         // JDBCUtil �� �Ű����� ����
	            result = jdbcUtil.executeUpdate();      // insert �� ����
	            System.out.println(Camera.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
	       }catch (SQLException ex) {
	            System.out.println("�Է¿��� �߻�!!!");
	            ex.printStackTrace();
	            if (ex.getErrorCode() == 1)
	               System.out.println("������ ��ǰ������ �̹� �����մϴ�.");      
	       } catch (Exception ex) {
	            jdbcUtil.rollback();
	            ex.printStackTrace();
	       } finally {      
	            jdbcUtil.commit();
	            jdbcUtil.close(); // ResultSet, PreparedStatement, Connection ��ȯ
	       }      
	       
	       return result;      // insert �� ���� �ݿ��� ���ڵ� �� ��ȯ   
	               
	   }
	   
	   // Camera ��ü�� �����Ǿ� �ִ� ������ ���� ���̺��� ������ �����ϴ� �޼ҵ�
	   
	   public void updateCamera(Camera camera) {
	      
	      int index = 0;
	      
	      System.out.println("updatePhone ���͵�!!!!!");
	      String updateQuery1 = "UPDATE PRODUCT SET ";
	       Object[] tempParam1 = new Object[10]; // update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
	      
	      if (camera.getProductId() != null) {      // �̸��� �����Ǿ� ���� ���
	         updateQuery1 += "PRODUCT_ID = ?, ";      // update ���� �̸� ���� �κ� �߰�
	         tempParam1[index++] = camera.getProductId();      // �Ű������� ������ �̸� �߰�
	      }
	      if (camera.getBrand() != null) {      // �귣�尡 �����Ǿ� ���� ���
	         updateQuery1 += "BRAND = ?, ";      // update ���� �귣�� ���� �κ� �߰�
	         tempParam1[index++] = camera.getBrand();      // �Ű������� ������ �귣�� �߰�
	      }
	      if (camera.getColor() != null) {      // ������ �����Ǿ� ���� ���
	         updateQuery1 += "COLOR = ?, ";      // update ���� ���� ���� �κ� �߰�
	         tempParam1[index++] = camera.getColor();      // �Ű������� ������ ���� �߰�
	      }
	      if (camera.getName() != null) {      // �̸��� �����Ǿ� ���� ���
	         updateQuery1 += "NAME = ?, ";      // update ���� �̸� ���� �κ� �߰�
	         tempParam1[index++] = camera.getName();      // �Ű������� ������ �̸� �߰�
	      }
	      if (camera.getpKind() == 3) {      // ������ �����Ǿ� ���� ���
	         updateQuery1 += "P_KIND = ?, ";      // update���� ���� ���� �κ� �߰�
	         tempParam1[index++] = camera.getpKind();      // �Ű������� ������ ���� �߰�
	      }
	      if (camera.getReleased_date() != null) {      // ������� �����Ǿ� ���� ���
	         updateQuery1 += "RELEASED_DATE = ?, ";      // update���� ����� ���� �κ� �߰�
	         tempParam1[index++] = camera.getReleased_date();      // �Ű������� ������ ����� �߰�
	      }
	      
	       updateQuery1 += "WHERE PRODUCT_ID = ? ";      // update ���� ���� ����
	       updateQuery1 = updateQuery1.replace(", WHERE", " WHERE");      // update ���� where �� �տ� ���� �� �ִ� , ����

	       tempParam1[index++] = camera.getProductId();      // ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
	         
	       Object[] newParam1 = new Object[index];
	       for (int i = 0; i < newParam1.length; i++)      // �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
	          newParam1[i] = tempParam1[i];
	       
	       index = 0;
	       String updateQuery2 = "UPDATE CAMERA SET ";
	       Object[] tempParam2 = new Object[10];
	               
	      if (camera.getcDisplay() >= 0) {      // ���÷��̰� �����Ǿ� ���� ���
	         updateQuery2 += "C_DISPLAY = ?, ";      // update ���� ���÷��� ���� �κ� �߰�
	         tempParam2[index++] = camera.getcDisplay();      // �Ű������� ������ ���÷��� �߰�
	      }
	      if (camera.getcPixel() >= 0) {      // �ȼ��� �����Ǿ� ���� ���
	         updateQuery2 += "C_PIXEL = ?, ";      // update ���� �ȼ� ���� �κ� �߰�
	         tempParam2[index++] = camera.getcPixel();      // �Ű������� ������ �ȼ� �߰�
	      }
	      if (camera.getcBattery() != null) {      // ���͸��� �����Ǿ� ���� ���
	         updateQuery2 += "C_BATTERY = ?, ";      // update ���� ���͸� ���� �κ� �߰�
	         tempParam2[index++] = camera.getcBattery();      // �Ű������� ������ ���͸� �߰�
	      }
	      if (camera.getcVibration() != null) {      // ���̺극�̼��� �����Ǿ� ���� ���
	         updateQuery2 += "C_VIBRATION = ?, ";      // update ���� ���̺극�̼� ���� �κ� �߰�
	         tempParam2[index++] = camera.getcVibration();      // �Ű������� ������ ���̺극�̼� �߰�
	      }
	      if (camera.getcBurstshot() >= 0) {      // ����Ʈ���� �����Ǿ� ���� ���
	         updateQuery2 += "C_BURSTSHOT = ?, ";      // update ���� ����Ʈ�� ���� �κ� �߰�
	         tempParam2[index++] = camera.getcBurstshot();      // �Ű������� ������ ����Ʈ�� �߰�
	      }
	      if (camera.getcLens() != null) {      // ��� �����Ǿ� ���� ���
	         updateQuery2 += "C_LENS = ? ";      // update ���� ���� ���� �κ� �߰�
	         tempParam2[index++] = camera.getcLens();      // �Ű������� ������ ���� �߰�
	      }
	      
	      updateQuery2 += "WHERE PRODUCT_ID = ? ";      // update ���� ���� ����
	      updateQuery2 = updateQuery2.replace(", WHERE", " WHERE");      // update ���� where �� �տ� ���� �� �ִ� , ����
	      
	      tempParam2[index++] = camera.getProductId();      // ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
	      
	      Object[] newParam2 = new Object[index];
	      for (int i=0; i < newParam2.length; i++)      // �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
	         newParam2[i] = tempParam2[i];
	      
	       try {
	            System.out.println("update try�� ���͵�!!!!!");
	             jdbcUtil.setSql(updateQuery1);         // JDBCUtil�� update �� ����
	             jdbcUtil.setParameters(newParam1);      // JDBCUtil �� �Ű����� ����
	             int result = jdbcUtil.executeUpdate();      // update �� ����
	             
	             jdbcUtil.setSql(updateQuery2);         // JDBCUtil�� update �� ����
	             jdbcUtil.setParameters(newParam2);      // JDBCUtil �� �Ű����� ����
	             result += jdbcUtil.executeUpdate();
	             return;         // update �� ���� �ݿ��� ���ڵ� �� ��ȯ
	        } catch (Exception ex) {
	             jdbcUtil.rollback();
	             ex.printStackTrace();
	        }
	          finally {
	             jdbcUtil.commit();
	             jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
	        }      
	        return;
	   }

	
	// product_id�� ���޹޾� �ش� ī�޶��� ������ �����ϴ� �޼ҵ�
	public void deleteCamera(String cId) {
	      String deleteQuery1 = "DELETE FROM CAMERA WHERE PRODUCT_ID = ? ";
	      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
	      
	      try {
	         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil �� query �� ����
	         Object[] param = new Object[] { cId };
	         jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
	         int result = jdbcUtil.executeUpdate();      // delete �� ����
	         if( result != 0 ) {
	             System.out.println("product ���� �Ϸ�"); 
	         }
	         
	         jdbcUtil.setSql(deleteQuery2);         // JDBCUtil �� query �� ����
	         jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
	         result = jdbcUtil.executeUpdate();      // delete �� ����
	         if( result != 0 ) {
	             System.out.println("Camera ���� �Ϸ�"); 
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
				dto.setcPixel(rs.getInt("CAMERA_PIXEL"));
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
		
		Object[] param = new Object[] {cId};		// �º��� ã�� ���� �������� �̸��� ����
		jdbcUtil.setSql(searchQuery);	// JDBCUtil �� query �� ����
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
				dto.setcPixel(rs.getInt("CAMERA_PIXEL"));
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
	
	
	public String getBrandById(String cId) {
	       // TODO Auto-generated method stub
	       String searchQuery = query + "FROM CAMERA, PRODUCT "
					+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = ? ";   
	     
	       
	       
	       jdbcUtil.setSql(searchQuery);
	       Object[] param = new Object[] {cId};
	       jdbcUtil.setParameters(param);
	    
	       try {
	          ResultSet rs = jdbcUtil.executeQuery();
	          String brand = null;
	          System.out.println("CAMERADAO");
	          if (rs.next()) { 
	             brand = rs.getString("CAMERA_BRAND");
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
		   String searchQuery = query + "FROM CAMERA, PRODUCT "
					+ "WHERE CAMERA.PRODUCT_ID = PRODUCT.PRODUCT_ID AND PRODUCT.BRAND = ? ";
				   
		   
		   
	       
	       jdbcUtil.setSql(searchQuery);
	       Object[] param = new Object[] {brand};
	       jdbcUtil.setParameters(param);
	    
	       try {
	           ResultSet rs = jdbcUtil.executeQuery();
	           List<Product> productList = new ArrayList<Product>();
	           while (rs.next()) {
	        	  Product dto = new Product();
	              dto.setProductId(rs.getString("CAMERA_ID"));
	              dto.setName(rs.getString("CAMERA_NAME"));
	              dto.setColor(rs.getString("CAMERA_COLOR"));
	              dto.setPrice(rs.getString("CAMERA_PRICE"));
	              dto.setBrand(rs.getString("CAMERA_BRAND"));
	              dto.setReleased_date(rs.getDate("CAMERA_RELEASED_DATE"));
	              dto.setWeight(rs.getDouble("CAMERA_WEIGHT"));
	              dto.setpKind(rs.getInt("CAMERA_KIND"));
	              
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
