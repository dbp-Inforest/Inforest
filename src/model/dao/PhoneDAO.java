package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.PhoneDAO;
import model.dto.Phone;
import model.dto.Product;

public class PhoneDAO{

   private JDBCUtil jdbcUtil = null;
   
   private static String query = "SELECT H.P_BATTERY AS PHONE_BATTERY, " +
            "        H.P_DISPLAY AS PHONE_DISPLAY, H.P_RAM AS PHONE_RAM," +
              "        H.P_MEMORY AS PHONE_MEMORY, H.P_SIZE AS PHONE_SIZE, " +
           "        H.P_CAMERA AS PHONE_CAMERA, H.PRODUCT_ID AS PHONE_ID, H.P_OS AS PHONE_OS, " +
           "         P.NAME AS PHONE_NAME, P.COLOR AS PHONE_COLOR, " +
           "         P.PRICE AS PHONE_PRICE,  P.BRAND  AS PHONE_BRAND, " +
           "       P.RELEASED_DATE  AS PHONE_RELEASED_DATE, P.WEIGHT  AS PHONE_WEIGHT, " +
           "         P.P_KIND  AS PHONE_KIND ";
   
   public PhoneDAO() {   
         try {
            jdbcUtil = new JDBCUtil();   // JDBCUtil ��ü ����;
         } catch (Exception e) {
            e.printStackTrace();
         }   
   }
   public List<Phone> getPhoneList() {
      // �⺻ ������ ������ 
       String allQuery = query + "FROM PHONE H, PRODUCT P " +
                                 "WHERE H.PRODUCT_ID = P.PRODUCT_ID ";      
      jdbcUtil.setSql(allQuery);      // JDBCUtil �� query ����
      
      try { 
         ResultSet rs = jdbcUtil.executeQuery();      // query �� ����         
         List<Phone> list = new ArrayList<Phone>();      // LaptopDTO ��ü���� ������� list ��ü
         while (rs.next()) {   
            Phone dto = new Phone();      // �ϳ��� LaptopDTO ��ü ���� �� ���� ����
            dto.setpBattery(rs.getString("PHONE_BATTERY"));
            dto.setpMemory(rs.getString("PHONE_MEMORY"));
            dto.setpDisplay(rs.getString("PHONE_DISPLAY"));
            dto.setpRAM(rs.getString("PHONE_RAM"));
            dto.setpSize(rs.getDouble("PHONE_SIZE"));
            dto.setpCamera(rs.getString("PHONE_CAMERA"));
            dto.setProductId(rs.getString("PHONE_ID"));
            dto.setpOS(rs.getString("PHONE_OS"));
            //--
            dto.setName(rs.getString("PHONE_NAME"));
            dto.setColor(rs.getString("PHONE_COLOR"));
            dto.setPrice(rs.getString("PHONE_PRICE"));
            dto.setBrand(rs.getString("PHONE_BRAND"));
            dto.setReleased_date(rs.getDate("PHONE_RELEASED_DATE"));
            dto.setWeight(rs.getDouble("PHONE_WEIGHT"));
            dto.setpKind(rs.getInt("PHONE_KIND"));
            
            list.add(dto);      // list ��ü�� ������ ������ LaptopDTO ��ü ����
         }
         return list;      // �л������� ������ dto���� ����� ��ȯ
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
      }      
      return null;   
   }

   public int insertPhone(Phone phone) {
      
      int result = 0;
      
      String insertQuery1 = "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
      
      String insertQuery2 = "INSERT INTO PHONE (P_BATTERY, P_MEMORY, P_DISPLAY, P_RAM, P_SIZE, P_CAMERA, PRODUCT_ID, P_OS) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
      
      // query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
      Object[] param1 = new Object[] { phone.getProductId(), phone.getName(), phone.getColor(), phone.getPrice(),
				   phone.getBrand(), phone.getReleased_date(), phone.getWeight(), phone.getpKind() };
      Object[] param2 = new Object[] { phone.getpBattery(), phone.getpMemory(), phone.getpDisplay(), phone.getpRAM(),phone.getpSize(), phone.getpCamera(), 
                               		   phone.getProductId(), phone.getpOS() };           
      try {            
         jdbcUtil.setSql(insertQuery1);         // JDBCUtil �� insert �� ����
         jdbcUtil.setParameters(param1);         // JDBCUtil �� �Ű����� ����
         result = jdbcUtil.executeUpdate();      // insert �� ����
         
         jdbcUtil.setSql(insertQuery2);          // JDBCUtil �� insert �� ����
         jdbcUtil.setParameters(param2);        // JDBCUtil �� �Ű����� ����
         result = jdbcUtil.executeUpdate();      // insert �� ����
         System.out.println(phone.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
      } catch (SQLException ex) {
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

   public void updatePhone(Phone phone) {
      
	  int index = 0;
	  
	  System.out.println("updatePhone ���͵�!!!!!");
	  String updateQuery1 = "UPDATE PRODUCT SET ";
      Object[] tempParam1 = new Object[10]; // update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
      
      if (phone.getBrand() != null) {      // �귣�尡 �����Ǿ� ���� ���
         updateQuery1 += "BRAND = ?, ";      // update ���� �귣�� ���� �κ� �߰�
         tempParam1[index++] = phone.getBrand();      // �Ű������� ������ �̸� �߰�
      }
      if (phone.getColor() != null) {      // ������ �����Ǿ� ���� ���
         updateQuery1 += "COLOR = ?, ";      // update ���� ���� ���� �κ� �߰�
         tempParam1[index++] = phone.getColor();      // �Ű������� ������ ���� �߰�
      }
      if (phone.getName() != null) {      // �̸��� �����Ǿ� ���� ���
         updateQuery1 += "NAME = ?, ";      // update ���� �̸� ���� �κ� �߰�
         tempParam1[index++] = phone.getName();      // �Ű������� ������ �޴��� �߰�
      }
      if (phone.getPrice() != null) {      // price�� �����Ǿ� ���� ���
          updateQuery1 += "PRICE = ?, ";      // update ���� price ���� �κ� �߰�
          tempParam1[index++] = phone.getPrice();      // �Ű������� ������ price �߰�
       }   
      if (phone.getReleased_date() != null) {      // ������� �����Ǿ� ���� ���
          updateQuery1 += "RELEASED_DATE = ?, ";      // update ���� ����� ���� �κ� �߰�
          tempParam1[index++] = phone.getReleased_date();      // �Ű������� ������ ��� �߰�
       }
      if (phone.getWeight() > 0) {      // ���԰� �����Ǿ� ���� ���
          updateQuery1 += "WEIGHT = ?, ";      // update ���� ���� ���� �κ� �߰�
          tempParam1[index++] = phone.getWeight();      // �Ű������� ������ ���� �߰�
       }
      if (phone.getpKind() == 1) {      // pKind�� �����Ǿ� ���� ���
          updateQuery1 += "P_KIND = ?, ";      // update ���� pKind ���� �κ� �߰�
          tempParam1[index++] = phone.getpKind();      // �Ű������� ������ pKind �߰�
       }
      
      updateQuery1 += "WHERE PRODUCT_ID = ? ";      // update ���� ���� ����
      updateQuery1 = updateQuery1.replace(", WHERE", " WHERE");      // update ���� where �� �տ� ���� �� �ִ� , ����

      tempParam1[index++] = phone.getProductId();      // ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
      
      Object[] newParam1 = new Object[index];
      for (int i = 0; i < newParam1.length; i++)      // �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
         newParam1[i] = tempParam1[i];
    
      index = 0;
      String updateQuery2 = "UPDATE PHONE SET ";
      Object[] tempParam2 = new Object[10];
      
      if (phone.getpBattery() != null) {      // ���͸��� �����Ǿ� ���� ���
         updateQuery2 += "P_BATTERY = ?, ";      // update ���� ���͸����� �κ� �߰�
         tempParam2[index++] = phone.getpBattery();      // �Ű������� ������ ���͸� �߰�
      }
      if (phone.getpCamera() != null) {      // camera�� �����Ǿ� ���� ���
         updateQuery2 += "P_CAMERA = ?, ";      // update ���� camera ���� �κ� �߰�
         tempParam2[index++] = phone.getpCamera();      // �Ű������� ������ camera �߰�
      }
      if (phone.getpDisplay() != null) {      // display�� �����Ǿ� ���� ���
         updateQuery2 += "P_DISPLAY = ?, ";      // update ���� display ���� �κ� �߰�
         tempParam2[index++] = phone.getpDisplay();      // �Ű������� ������ diaplay �߰�
      }
      if (phone.getpMemory() != null) {      // �޸𸮰� �����Ǿ� ���� ���
         updateQuery2 += "P_MEMORY = ?, ";      // update ���� �޸� ���� �κ� �߰�
         tempParam2[index++] = phone.getpMemory();      // �Ű������� ������ �޸� �߰�
      }
      if (phone.getpOS() != null) {      // os�� �����Ǿ� ���� ���
         updateQuery2 += "P_OS = ?, ";      // update ���� os ���� �κ� �߰�
         tempParam2[index++] = phone.getpOS();      // �Ű������� ������ os �߰�
      }
      if (phone.getpRAM() != null) {      // ram�� �����Ǿ� ���� ���
         updateQuery2 += "P_RAM = ?, ";      // update ���� ram ���� �κ� �߰�
         tempParam2[index++] = phone.getpRAM();      // �Ű������� ������ ram �߰�
      }   
      if (phone.getpSize() > 0) {      // size�� �����Ǿ� ���� ���
         updateQuery2 += "P_SIZE = ?, ";      // update ���� size ���� �κ� �߰�
         tempParam2[index++] = phone.getpSize();      // �Ű������� ������ size �߰�
      }
      if (phone.getProductId() != null) {      // id�� �����Ǿ� ���� ���
         updateQuery2 += "PRODUCT_ID = ?, ";      // update ���� id ���� �κ� �߰�
         tempParam2[index++] = phone.getProductId();      // �Ű������� ������ id �߰�
      }   

      
      updateQuery2 += "WHERE PRODUCT_ID = ? ";      // update ���� ���� ����
      updateQuery2 = updateQuery2.replace(", WHERE", " WHERE");      // update ���� where �� �տ� ���� �� �ִ� , ����
      
      tempParam2[index++] = phone.getProductId();      // ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
      
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

   public void deletePhone(String phProductId) {
      String deleteQuery1 = "DELETE FROM PHONE WHERE PRODUCT_ID = ? ";
      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
      
      System.out.println("����� deletePhone");
      try {
         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil �� query �� ����
         Object[] param = new Object[] { phProductId };
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

   public List<Phone> getPhoneByName(String phName) {
      // TODO Auto-generated method stub
      String searchQuery = query + "FROM PHONE H, PRODUCT P " +
              "WHERE H.PRODUCT_ID = P.PRODUCT_ID  AND P.Name LIKE ?";  
      Object[] param = new Object[] { "%" + phName + "%"};

      jdbcUtil.setSql(searchQuery);
      jdbcUtil.setParameters(param);
   
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         List<Phone> list = new ArrayList<Phone>();
         while (rs.next()) {
        	Phone dto = new Phone();
            dto.setpBattery(rs.getString("PHONE_BATTERY"));
            dto.setpMemory(rs.getString("PHONE_MEMORY"));
            dto.setpDisplay(rs.getString("PHONE_DISPLAY"));
            dto.setpRAM(rs.getString("PHONE_RAM"));
            dto.setpSize(rs.getDouble("PHONE_SIZE"));
            dto.setpCamera(rs.getString("PHONE_CAMERA"));
            dto.setProductId(rs.getString("PHONE_ID"));
            dto.setpOS(rs.getString("PHONE_OS"));
            //--
            dto.setName(rs.getString("PHONE_NAME"));
            dto.setColor(rs.getString("PHONE_COLOR"));
            dto.setPrice(rs.getString("PHONE_PRICE"));
            dto.setBrand(rs.getString("PHONE_BRAND"));
            dto.setReleased_date(rs.getDate("PHONE_RELEASED_DATE"));
            dto.setWeight(rs.getDouble("PHONE_WEIGHT"));
            dto.setpKind(rs.getInt("PHONE_KIND"));
            
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
   
   public Phone getPhoneById(String phId) {
         // TODO Auto-generated method stub
         String searchQuery = query + "FROM PHONE H, PRODUCT P " +
                 "WHERE H.PRODUCT_ID = P.PRODUCT_ID  AND H.PRODUCT_ID = ?";   
       
         Object[] param = new Object[] {phId};
         System.out.println(phId);
         jdbcUtil.setSql(searchQuery);
         jdbcUtil.setParameters(param);
      
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            Phone dto = new Phone();
            System.out.println("PHONEDAO");
            if (rs.next()) { 
               dto.setpBattery(rs.getString("PHONE_BATTERY"));
               dto.setpMemory(rs.getString("PHONE_MEMORY"));
               dto.setpDisplay(rs.getString("PHONE_DISPLAY"));
               dto.setpRAM(rs.getString("PHONE_RAM"));
               dto.setpSize(rs.getDouble("PHONE_SIZE"));
               dto.setpCamera(rs.getString("PHONE_CAMERA"));
               dto.setProductId(rs.getString("PHONE_ID"));
               dto.setpOS(rs.getString("PHONE_OS"));
               //--
               dto.setName(rs.getString("PHONE_NAME"));
               dto.setColor(rs.getString("PHONE_COLOR"));
               dto.setPrice(rs.getString("PHONE_PRICE"));
               dto.setBrand(rs.getString("PHONE_BRAND"));
               dto.setReleased_date(rs.getDate("PHONE_RELEASED_DATE"));
               dto.setWeight(rs.getDouble("PHONE_WEIGHT"));
               dto.setpKind(rs.getInt("PHONE_KIND"));
               System.out.println("������� ����");
            }else {
            	 System.out.println("No ResultSet"); 
            }
                 
            return dto;   
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();
         }
         return null;
     }
   
   public String getBrandById(String pId) {
       // TODO Auto-generated method stub
       String searchQuery = query + "FROM PHONE H, PRODUCT P " +
               "WHERE H.PRODUCT_ID = P.PRODUCT_ID  AND H.PRODUCT_ID = ?";   
     
       
       
       jdbcUtil.setSql(searchQuery);
       Object[] param = new Object[] {pId};
       jdbcUtil.setParameters(param);
    
       try {
          ResultSet rs = jdbcUtil.executeQuery();
          String brand = null;
          System.out.println("PHONEDAO");
          if (rs.next()) { 
             brand = rs.getString("PHONE_BRAND");
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
	   String searchQuery = query + "FROM PHONE H, PRODUCT P " +
               "WHERE H.PRODUCT_ID = P.PRODUCT_ID  AND P.BRAND = ?";
			   
       
       jdbcUtil.setSql(searchQuery);
       Object[] param = new Object[] {brand};
       jdbcUtil.setParameters(param);
    
       try {
           ResultSet rs = jdbcUtil.executeQuery();
           List<Product> productList = new ArrayList<Product>();
           while (rs.next()) {
        	  Product dto = new Product();
              dto.setProductId(rs.getString("PHONE_ID"));
              dto.setName(rs.getString("PHONE_NAME"));
              dto.setColor(rs.getString("PHONE_COLOR"));
              dto.setPrice(rs.getString("PHONE_PRICE"));
              dto.setBrand(rs.getString("PHONE_BRAND"));
              dto.setReleased_date(rs.getDate("PHONE_RELEASED_DATE"));
              dto.setWeight(rs.getDouble("PHONE_WEIGHT"));
              dto.setpKind(rs.getInt("PHONE_KIND"));
              
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


