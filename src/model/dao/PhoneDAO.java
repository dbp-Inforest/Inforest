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
            jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성;
         } catch (Exception e) {
            e.printStackTrace();
         }   
   }
   public List<Phone> getPhoneList() {
      // 기본 쿼리와 합쳐짐 
       String allQuery = query + "FROM PHONE H, PRODUCT P " +
                                 "WHERE H.PRODUCT_ID = P.PRODUCT_ID ";      
      jdbcUtil.setSql(allQuery);      // JDBCUtil 에 query 설정
      
      try { 
         ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행         
         List<Phone> list = new ArrayList<Phone>();      // LaptopDTO 객체들을 담기위한 list 객체
         while (rs.next()) {   
            Phone dto = new Phone();      // 하나의 LaptopDTO 객체 생성 후 정보 설정
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
            
            list.add(dto);      // list 객체에 정보를 설정한 LaptopDTO 객체 저장
         }
         return list;      // 학생정보를 저장한 dto들의 목록을 반환
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
      }      
      return null;   
   }

   public int insertPhone(Phone phone) {
      
      int result = 0;
      
      String insertQuery1 = "INSERT INTO PRODUCT (PRODUCT_ID, NAME, COLOR, PRICE, BRAND, RELEASED_DATE, WEIGHT, P_KIND) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
      
      String insertQuery2 = "INSERT INTO PHONE (P_BATTERY, P_MEMORY, P_DISPLAY, P_RAM, P_SIZE, P_CAMERA, PRODUCT_ID, P_OS) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
      
      // query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성 물음표 다 받아오기 
      Object[] param1 = new Object[] { phone.getProductId(), phone.getName(), phone.getColor(), phone.getPrice(),
				   phone.getBrand(), phone.getReleased_date(), phone.getWeight(), phone.getpKind() };
      Object[] param2 = new Object[] { phone.getpBattery(), phone.getpMemory(), phone.getpDisplay(), phone.getpRAM(),phone.getpSize(), phone.getpCamera(), 
                               		   phone.getProductId(), phone.getpOS() };           
      try {            
         jdbcUtil.setSql(insertQuery1);         // JDBCUtil 에 insert 문 설정
         jdbcUtil.setParameters(param1);         // JDBCUtil 에 매개변수 설정
         result = jdbcUtil.executeUpdate();      // insert 문 실행
         
         jdbcUtil.setSql(insertQuery2);          // JDBCUtil 에 insert 문 설정
         jdbcUtil.setParameters(param2);        // JDBCUtil 에 매개변수 설정
         result = jdbcUtil.executeUpdate();      // insert 문 실행
         System.out.println(phone.getProductId() + " 제품정보가 삽입되었습니다.");
      } catch (SQLException ex) {
         System.out.println("입력오류 발생!!!");
         ex.printStackTrace();
         if (ex.getErrorCode() == 1)
            System.out.println("동일한 제품정보가 이미 존재합니다."); 
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
      }      
      return result;      // insert 에 의해 반영된 레코드 수 반환   
      
   }

   public void updatePhone(Phone phone) {
      
	  int index = 0;
	  
	  System.out.println("updatePhone 들어와따!!!!!");
	  String updateQuery1 = "UPDATE PRODUCT SET ";
      Object[] tempParam1 = new Object[10]; // update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
      
      if (phone.getBrand() != null) {      // 브랜드가 설정되어 있을 경우
         updateQuery1 += "BRAND = ?, ";      // update 문에 브랜드 수정 부분 추가
         tempParam1[index++] = phone.getBrand();      // 매개변수에 수정할 이름 추가
      }
      if (phone.getColor() != null) {      // 색깔이 설정되어 있을 경우
         updateQuery1 += "COLOR = ?, ";      // update 문에 색깔 수정 부분 추가
         tempParam1[index++] = phone.getColor();      // 매개변수에 수정할 색깔 추가
      }
      if (phone.getName() != null) {      // 이름이 설정되어 있을 경우
         updateQuery1 += "NAME = ?, ";      // update 문에 이름 수정 부분 추가
         tempParam1[index++] = phone.getName();      // 매개변수에 수정할 휴대폰 추가
      }
      if (phone.getPrice() != null) {      // price가 설정되어 있을 경우
          updateQuery1 += "PRICE = ?, ";      // update 문에 price 수정 부분 추가
          tempParam1[index++] = phone.getPrice();      // 매개변수에 수정할 price 추가
       }   
      if (phone.getReleased_date() != null) {      // 출시일이 설정되어 있을 경우
          updateQuery1 += "RELEASED_DATE = ?, ";      // update 문에 출시일 수정 부분 추가
          tempParam1[index++] = phone.getReleased_date();      // 매개변수에 수정할 출시 추가
       }
      if (phone.getWeight() > 0) {      // 무게가 설정되어 있을 경우
          updateQuery1 += "WEIGHT = ?, ";      // update 문에 무게 수정 부분 추가
          tempParam1[index++] = phone.getWeight();      // 매개변수에 수정할 무게 추가
       }
      if (phone.getpKind() == 1) {      // pKind가 설정되어 있을 경우
          updateQuery1 += "P_KIND = ?, ";      // update 문에 pKind 수정 부분 추가
          tempParam1[index++] = phone.getpKind();      // 매개변수에 수정할 pKind 추가
       }
      
      updateQuery1 += "WHERE PRODUCT_ID = ? ";      // update 문에 조건 지정
      updateQuery1 = updateQuery1.replace(", WHERE", " WHERE");      // update 문의 where 절 앞에 있을 수 있는 , 제거

      tempParam1[index++] = phone.getProductId();      // 찾을 조건에 해당하는 에 대한 매개변수 추가
      
      Object[] newParam1 = new Object[index];
      for (int i = 0; i < newParam1.length; i++)      // 매개변수의 개수만큼의 크기를 갖는 배열을 생성하고 매개변수 값 복사
         newParam1[i] = tempParam1[i];
    
      index = 0;
      String updateQuery2 = "UPDATE PHONE SET ";
      Object[] tempParam2 = new Object[10];
      
      if (phone.getpBattery() != null) {      // 배터리가 설정되어 있을 경우
         updateQuery2 += "P_BATTERY = ?, ";      // update 문에 배터리수정 부분 추가
         tempParam2[index++] = phone.getpBattery();      // 매개변수에 수정할 배터리 추가
      }
      if (phone.getpCamera() != null) {      // camera가 설정되어 있을 경우
         updateQuery2 += "P_CAMERA = ?, ";      // update 문에 camera 수정 부분 추가
         tempParam2[index++] = phone.getpCamera();      // 매개변수에 수정할 camera 추가
      }
      if (phone.getpDisplay() != null) {      // display가 설정되어 있을 경우
         updateQuery2 += "P_DISPLAY = ?, ";      // update 문에 display 수정 부분 추가
         tempParam2[index++] = phone.getpDisplay();      // 매개변수에 수정할 diaplay 추가
      }
      if (phone.getpMemory() != null) {      // 메모리가 설정되어 있을 경우
         updateQuery2 += "P_MEMORY = ?, ";      // update 문에 메모리 수정 부분 추가
         tempParam2[index++] = phone.getpMemory();      // 매개변수에 수정할 메모리 추가
      }
      if (phone.getpOS() != null) {      // os가 설정되어 있을 경우
         updateQuery2 += "P_OS = ?, ";      // update 문에 os 수정 부분 추가
         tempParam2[index++] = phone.getpOS();      // 매개변수에 수정할 os 추가
      }
      if (phone.getpRAM() != null) {      // ram이 설정되어 있을 경우
         updateQuery2 += "P_RAM = ?, ";      // update 문에 ram 수정 부분 추가
         tempParam2[index++] = phone.getpRAM();      // 매개변수에 수정할 ram 추가
      }   
      if (phone.getpSize() > 0) {      // size가 설정되어 있을 경우
         updateQuery2 += "P_SIZE = ?, ";      // update 문에 size 수정 부분 추가
         tempParam2[index++] = phone.getpSize();      // 매개변수에 수정할 size 추가
      }
      if (phone.getProductId() != null) {      // id가 설정되어 있을 경우
         updateQuery2 += "PRODUCT_ID = ?, ";      // update 문에 id 수정 부분 추가
         tempParam2[index++] = phone.getProductId();      // 매개변수에 수정할 id 추가
      }   

      
      updateQuery2 += "WHERE PRODUCT_ID = ? ";      // update 문에 조건 지정
      updateQuery2 = updateQuery2.replace(", WHERE", " WHERE");      // update 문의 where 절 앞에 있을 수 있는 , 제거
      
      tempParam2[index++] = phone.getProductId();      // 찾을 조건에 해당하는 에 대한 매개변수 추가
      
      Object[] newParam2 = new Object[index];
      for (int i=0; i < newParam2.length; i++)      // 매개변수의 개수만큼의 크기를 갖는 배열을 생성하고 매개변수 값 복사
         newParam2[i] = tempParam2[i];
      

      try {
    	 System.out.println("update try문 들어와따!!!!!");
         jdbcUtil.setSql(updateQuery1);         // JDBCUtil에 update 문 설정
         jdbcUtil.setParameters(newParam1);      // JDBCUtil 에 매개변수 설정
         int result = jdbcUtil.executeUpdate();      // update 문 실행
         
         jdbcUtil.setSql(updateQuery2);         // JDBCUtil에 update 문 설정
         jdbcUtil.setParameters(newParam2);      // JDBCUtil 에 매개변수 설정
         result += jdbcUtil.executeUpdate();
         return;         // update 에 의해 반영된 레코드 수 반환
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
      }      
      return;
   }

   public void deletePhone(String phProductId) {
      String deleteQuery1 = "DELETE FROM PHONE WHERE PRODUCT_ID = ? ";
      String deleteQuery2 = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ? ";
      
      System.out.println("여기는 deletePhone");
      try {
         jdbcUtil.setSql(deleteQuery1);         // JDBCUtil 에 query 문 설정
         Object[] param = new Object[] { phProductId };
         jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
         int result = jdbcUtil.executeUpdate();      // delete 문 실행
         if( result != 0 ) {
             System.out.println("product 삭제 완료"); 
         }
         
         jdbcUtil.setSql(deleteQuery2);         // JDBCUtil 에 query 문 설정
         jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
         result = jdbcUtil.executeUpdate();      // delete 문 실행
         if( result != 0 ) {
             System.out.println("phone 삭제 완료"); 
         }
         
         return; // delete 에 의해 반영된 레코드 수 반환
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
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
               System.out.println("여기까지 성공");
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
             System.out.println("여기까지 성공");
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


