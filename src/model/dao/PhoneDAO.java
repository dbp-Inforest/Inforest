package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.PhoneDAO;
import model.dto.Phone;

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
      
      String insertQuery = "INSERT INTO PHONE (P_BATTERY, P_MEMORY, P_DISPLAY, P_RAM, P_SIZE, P_CAMERA, PRODUCT_ID, P_OS) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
      insertQuery += "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
      
      // query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
      Object[] param = new Object[] {phone.getpBattery(), phone.getpMemory(), phone.getpDisplay(), phone.getpRAM(),phone.getpSize(), phone.getpCamera(), 
                               phone.getProductId(), phone.getpOS(), phone.getProductId(), phone.getName(), phone.getColor(), phone.getPrice(),
                               phone.getBrand(), phone.getReleased_date(), phone.getWeight(), phone.getpKind()};
      jdbcUtil.setSql(insertQuery);         // JDBCUtil �� insert �� ����
      jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
            
      try {            
         result = jdbcUtil.executeUpdate();      // insert �� ����
         System.out.println(phone.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
      } catch (SQLException ex) {
         System.out.println("�Է¿��� �߻�!!!");
         if (ex.getErrorCode() == 1)
            System.out.println("������ ��ǰ������ �̹� �����մϴ�."); 
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
      }      
      return result;      // insert �� ���� �ݿ��� ���ڵ� �� ��ȯ   
      
   }

   public int updatePhone(Phone phone) {
      
      String updateQuery = "UPDATE PHONE SET ";
      int index = 0;
      Object[] tempParam = new Object[10];      // update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
      
      if (phone.getBrand() != null) {      // �귣�尡 �����Ǿ� ���� ���
         updateQuery += "BRAND = ?, ";      // update ���� �귣�� ���� �κ� �߰�
         tempParam[index++] = phone.getBrand();      // �Ű������� ������ �̸� �߰�
      }
      if (phone.getColor() != null) {      // ������ �����Ǿ� ���� ���
         updateQuery += "COLOR = ?, ";      // update ���� ���� ���� �κ� �߰�
         tempParam[index++] = phone.getColor();      // �Ű������� ������ ���� �߰�
      }
      if (phone.getName() != null) {      // �̸��� �����Ǿ� ���� ���
         updateQuery += "NAME = ?, ";      // update ���� �̸� ���� �κ� �߰�
         tempParam[index++] = phone.getName();      // �Ű������� ������ �޴��� �߰�
      }
      if (phone.getpBattery() != null) {      // ���͸��� �����Ǿ� ���� ���
         updateQuery += "P_BATTERY = ?, ";      // update ���� ���͸����� �κ� �߰�
         tempParam[index++] = phone.getpBattery();      // �Ű������� ������ ���͸� �߰�
      }
      if (phone.getpCamera() != null) {      // camera�� �����Ǿ� ���� ���
         updateQuery += "P_CAMERA = ?, ";      // update ���� camera ���� �κ� �߰�
         tempParam[index++] = phone.getpCamera();      // �Ű������� ������ camera �߰�
      }
      if (phone.getpDisplay() != null) {      // display�� �����Ǿ� ���� ���
         updateQuery += "P_DISPLAY = ?, ";      // update ���� display ���� �κ� �߰�
         tempParam[index++] = phone.getpDisplay();      // �Ű������� ������ diaplay �߰�
      }
      if (phone.getpKind() == 1) {      // pKind�� �����Ǿ� ���� ���
         updateQuery += "P_KIND = ?, ";      // update ���� pKind ���� �κ� �߰�
         tempParam[index++] = phone.getpKind();      // �Ű������� ������ pKind �߰�
      }
      if (phone.getpMemory() != null) {      // �޸𸮰� �����Ǿ� ���� ���
         updateQuery += "P_MEMORY = ?, ";      // update ���� �޸� ���� �κ� �߰�
         tempParam[index++] = phone.getpMemory();      // �Ű������� ������ �޸� �߰�
      }
      if (phone.getpOS() != null) {      // os�� �����Ǿ� ���� ���
         updateQuery += "P_OS = ?, ";      // update ���� os ���� �κ� �߰�
         tempParam[index++] = phone.getpOS();      // �Ű������� ������ os �߰�
      }
      if (phone.getpRAM() != null) {      // ram�� �����Ǿ� ���� ���
         updateQuery += "P_RAM = ?, ";      // update ���� ram ���� �κ� �߰�
         tempParam[index++] = phone.getpRAM();      // �Ű������� ������ ram �߰�
      }   
      if (phone.getPrice() != null) {      // price�� �����Ǿ� ���� ���
         updateQuery += "PRICE = ?, ";      // update ���� price ���� �κ� �߰�
         tempParam[index++] = phone.getPrice();      // �Ű������� ������ price �߰�
      }   
      if (phone.getpSize() > 0) {      // size�� �����Ǿ� ���� ���
         updateQuery += "P_SIZE = ?, ";      // update ���� size ���� �κ� �߰�
         tempParam[index++] = phone.getpSize();      // �Ű������� ������ size �߰�
      }
      if (phone.getProductId() != null) {      // id�� �����Ǿ� ���� ���
         updateQuery += "PRODUCT_ID = ?, ";      // update ���� id ���� �κ� �߰�
         tempParam[index++] = phone.getProductId();      // �Ű������� ������ id �߰�
      }   
      if (phone.getReleased_date() != null) {      // ������� �����Ǿ� ���� ���
         updateQuery += "RELEASED_DATE = ?, ";      // update ���� ����� ���� �κ� �߰�
         tempParam[index++] = phone.getReleased_date();      // �Ű������� ������ ��� �߰�
      }
      if (phone.getWeight() > 0) {      // ���԰� �����Ǿ� ���� ���
         updateQuery += "WEIGHT = ?, ";      // update ���� ���� ���� �κ� �߰�
         tempParam[index++] = phone.getWeight();      // �Ű������� ������ ���� �߰�
      }
      
      updateQuery += "WHERE PRODUCT_ID = ? ";      // update ���� ���� ����
      updateQuery = updateQuery.replace(", WHERE", " WHERE");      // update ���� where �� �տ� ���� �� �ִ� , ����
      
      tempParam[index++] = phone.getProductId();      // ã�� ���ǿ� �ش��ϴ� �� ���� �Ű����� �߰�
      
      Object[] newParam = new Object[index];
      for (int i=0; i < newParam.length; i++)      // �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
         newParam[i] = tempParam[i];
      
      jdbcUtil.setSql(updateQuery);         // JDBCUtil�� update �� ����
      jdbcUtil.setParameters(newParam);      // JDBCUtil �� �Ű����� ����
      
      try {
         int result = jdbcUtil.executeUpdate();      // update �� ����
         return result;         // update �� ���� �ݿ��� ���ڵ� �� ��ȯ
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
      }      
      return 0;
   }

   public int deletePhone(int phProductId) {
      String deleteQuery = "DELETE FROM PHONE WHERE PRODUCT_ID = ?";
      
      jdbcUtil.setSql(deleteQuery);         // JDBCUtil �� query �� ����
      Object[] param = new Object[] {phProductId};
      jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
      
      try {
         int result = jdbcUtil.executeUpdate();      // delete �� ����
         return result;                  // delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
      }
      return 0;
   }

   public List<Phone> getPhoneByName(String phName) {
      // TODO Auto-generated method stub
      String searchQuery = query + "FROM PHONE H, PRODUCT P " +
              "WHERE H.PRODUCT_ID = P.PRODUCT_ID  AND P.PRODUCT_ID LIKE ?";  
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
            	System.out.println("������� ����");
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
     }


