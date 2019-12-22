package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.ProductLikeDAO;
import model.dto.Camera;
import model.dto.ProductLike;
import model.dto.Ranking;
import model.dao.PhoneDAO;
import model.dao.LaptopDAO;
import model.dao.CameraDAO;
import model.dao.TabletDAO;
import model.dto.Phone;
import model.dto.Laptop;
import model.dto.Camera;
import model.dto.Tablet;

public class ProductLikeDAO {
    private JDBCUtil jdbcUtil = null;
      
      private static String phone_q =    "SELECT PL.PRODUCT_ID, P.NAME " +
                                 " FROM PRODUCT_LIKE PL, PHONE PH, PRODUCT P " +
                                 " WHERE PL.PRODUCT_ID = PH.PRODUCT_ID AND PH.PRODUCT_ID = P.PRODUCT_ID ";
      
      private static String laptop_q =    "SELECT PL.PRODUCT_ID, P.NAME " +
                                 " FROM PRODUCT_LIKE PL, LAPTOP L, PRODUCT P " +
                                 " WHERE PL.PRODUCT_ID = L.PRODUCT_ID AND L.PRODUCT_ID = P.PRODUCT_ID ";
      
      private static String camera_q =    "SELECT PL.PRODUCT_ID, P.NAME " +
                                 " FROM PRODUCT_LIKE PL, CAMERA C, PRODUCT P " +
                                 " WHERE PL.PRODUCT_ID = C.PRODUCT_ID AND C.PRODUCT_ID = P.PRODUCT_ID ";
      
      private static String tablet_q =    "SELECT PL.PRODUCT_ID, P.NAME " +
                                 " FROM PRODUCT_LIKE PL, TABLET T, PRODUCT P " +
                                 " WHERE PL.PRODUCT_ID = T.PRODUCT_ID AND T.PRODUCT_ID = P.PRODUCT_ID ";
      
      public ProductLikeDAO() {
         jdbcUtil = new JDBCUtil();
      }
         
      
      public List<Ranking> getPhoneLikeList() {
            List<Ranking> rankList = new ArrayList<Ranking>();      // 전체 list객체
            
            jdbcUtil.setSql(phone_q);      // JDBCUtil 에 query 설정
               
            try { 
               ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행      
               // HashMap<String, Integer> map = new HashMap<String, Integer>();
               Ranking dto = new Ranking();      
            
               while (rs.next()) {   
                        // 하나의 ProductLike 객체 생성 후 정보 설정
                  
                 String pId = rs.getString("PRODUCT_ID");   //커서로 지금 들어온 값 pId
                  String name = rs.getString("NAME");
                  
                  int flag = 0;
                  if (rankList.size() != 0){            
                     //리스트 안에 하나이상의 값이 존재한다면
                        for(int i = 0; i < rankList.size(); i++) {   //리스트 사이즈만큼 반복   -> 리스트안에 pId 있는지 확인
                                                
                           String getId = rankList.get(i).getProductId().toString();   //리스트 순환 중, 현재의 pId 값
                          
                           if(pId.equals(getId)) {   //리스트안에 지금 들어온 pId가 이미 존재한다면
                              int likeCount = rankList.get(i).getLikeCount();
                              
                              likeCount += 1;
                              rankList.get(i).setLikeCount(likeCount);   //like + 1을 setting
                             
                           
                              flag = 1;   //리스트안에 지금 들어온 pId가 이미 존재한다면 flag = 1 변경
                              break;
                           }
                        }
                     //전체 탐색 후 
                        int size = rankList.size();
                        if (flag == 0) {   //사이즈 != 0 && 리스트 안에 pId가 없을경우
                           Ranking dto1 = new Ranking();      
                           
                           dto1.setProductId(pId);   //dto에 pId저장
                           dto1.setName(name);
                           dto1.setLikeCount(1);
                           rankList.add(dto1);
                         
                           //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                        }   
                        
                   }
                  else   //처음 들어오는 값
                  {
                     Ranking dto2 = new Ranking();      
                     
                     dto2.setProductId(pId);   //dto에 pId저장
                     dto2.setName(name);
                     dto2.setLikeCount(1);
                     rankList.add(dto2);
                    
                     //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                  }
                  
               }
               //rankList 리스트 삽입 끝
               
               //rankList 내림차순 정렬
                  rankList.sort(new Comparator<Ranking>() {
                             @Override
                             public int compare(Ranking arg0, Ranking arg1) {
                                    // TODO Auto-generated method stub
                                    int age0 = arg0.getLikeCount();
                                    int age1 = arg1.getLikeCount();
                                    if (age0 == age1)
                                          return 0;
                                    else if (age1 > age0)
                                          return 1;
                                    else
                                          return -1;
                             }
                      });
                   
                return rankList;
                
            } catch (Exception ex) {
               ex.printStackTrace();
            } finally {
               jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
            }      
                  
            return null;   
         }
      
      
      public List<Ranking> getLaptopLikeList() {
         List<Ranking> rankList = new ArrayList<Ranking>();      // 전체 list객체
         
         jdbcUtil.setSql(laptop_q);      // JDBCUtil 에 query 설정
            
         try { 
        	 ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행      
             // HashMap<String, Integer> map = new HashMap<String, Integer>();
             Ranking dto = new Ranking();      
          
             while (rs.next()) {   
                      // 하나의 ProductLike 객체 생성 후 정보 설정
                
               String pId = rs.getString("PRODUCT_ID");   //커서로 지금 들어온 값 pId
                String name = rs.getString("NAME");
                
                int flag = 0;
                if (rankList.size() != 0){            
                   //리스트 안에 하나이상의 값이 존재한다면
                      for(int i = 0; i < rankList.size(); i++) {   //리스트 사이즈만큼 반복   -> 리스트안에 pId 있는지 확인
                                              
                         String getId = rankList.get(i).getProductId().toString();   //리스트 순환 중, 현재의 pId 값
                        
                         if(pId.equals(getId)) {   //리스트안에 지금 들어온 pId가 이미 존재한다면
                            int likeCount = rankList.get(i).getLikeCount();
                            
                            likeCount += 1;
                            rankList.get(i).setLikeCount(likeCount);   //like + 1을 setting
                           
                         
                            flag = 1;   //리스트안에 지금 들어온 pId가 이미 존재한다면 flag = 1 변경
                            break;
                         }
                      }
                   //전체 탐색 후 
                      int size = rankList.size();
                      if (flag == 0) {   //사이즈 != 0 && 리스트 안에 pId가 없을경우
                         Ranking dto1 = new Ranking();      
                         
                         dto1.setProductId(pId);   //dto에 pId저장
                         dto1.setName(name);
                         dto1.setLikeCount(1);
                         rankList.add(dto1);
                       
                         //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                      }   
                      
                 }
                else   //처음 들어오는 값
                {
                   Ranking dto2 = new Ranking();      
                   
                   dto2.setProductId(pId);   //dto에 pId저장
                   dto2.setName(name);
                   dto2.setLikeCount(1);
                   rankList.add(dto2);
                  
                   //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                }
                
             }
             //rankList 리스트 삽입 끝
             
             //rankList 내림차순 정렬
                rankList.sort(new Comparator<Ranking>() {
                           @Override
                           public int compare(Ranking arg0, Ranking arg1) {
                                  // TODO Auto-generated method stub
                                  int age0 = arg0.getLikeCount();
                                  int age1 = arg1.getLikeCount();
                                  if (age0 == age1)
                                        return 0;
                                  else if (age1 > age0)
                                        return 1;
                                  else
                                        return -1;
                           }
                    });
                 
              return rankList;
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
         }      
               
         return null;   
      }
      
      public List<Ranking> getCameraLikeList() {
         List<Ranking> rankList = new ArrayList<Ranking>();      // 전체 list객체
         
         jdbcUtil.setSql(camera_q);      // JDBCUtil 에 query 설정
            
         try { 
        	 ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행      
             // HashMap<String, Integer> map = new HashMap<String, Integer>();
             Ranking dto = new Ranking();      
          
             while (rs.next()) {   
                      // 하나의 ProductLike 객체 생성 후 정보 설정
                
               String pId = rs.getString("PRODUCT_ID");   //커서로 지금 들어온 값 pId
                String name = rs.getString("NAME");
                
                int flag = 0;
                if (rankList.size() != 0){            
                   //리스트 안에 하나이상의 값이 존재한다면
                      for(int i = 0; i < rankList.size(); i++) {   //리스트 사이즈만큼 반복   -> 리스트안에 pId 있는지 확인
                                              
                         String getId = rankList.get(i).getProductId().toString();   //리스트 순환 중, 현재의 pId 값
                        
                         if(pId.equals(getId)) {   //리스트안에 지금 들어온 pId가 이미 존재한다면
                            int likeCount = rankList.get(i).getLikeCount();
                            
                            likeCount += 1;
                            rankList.get(i).setLikeCount(likeCount);   //like + 1을 setting
                           
                         
                            flag = 1;   //리스트안에 지금 들어온 pId가 이미 존재한다면 flag = 1 변경
                            break;
                         }
                      }
                   //전체 탐색 후 
                      int size = rankList.size();
                      if (flag == 0) {   //사이즈 != 0 && 리스트 안에 pId가 없을경우
                         Ranking dto1 = new Ranking();      
                         
                         dto1.setProductId(pId);   //dto에 pId저장
                         dto1.setName(name);
                         dto1.setLikeCount(1);
                         rankList.add(dto1);
                       
                         //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                      }   
                      
                 }
                else   //처음 들어오는 값
                {
                   Ranking dto2 = new Ranking();      
                   
                   dto2.setProductId(pId);   //dto에 pId저장
                   dto2.setName(name);
                   dto2.setLikeCount(1);
                   rankList.add(dto2);
                  
                   //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                }
                
             }
             //rankList 리스트 삽입 끝
             
             //rankList 내림차순 정렬
                rankList.sort(new Comparator<Ranking>() {
                           @Override
                           public int compare(Ranking arg0, Ranking arg1) {
                                  // TODO Auto-generated method stub
                                  int age0 = arg0.getLikeCount();
                                  int age1 = arg1.getLikeCount();
                                  if (age0 == age1)
                                        return 0;
                                  else if (age1 > age0)
                                        return 1;
                                  else
                                        return -1;
                           }
                    });
                 
              return rankList;
             
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
         }      
               
         return null;   
      }
      
      public List<Ranking> getTabletLikeList() {
            List<Ranking> rankList = new ArrayList<Ranking>();      // 전체 list객체
            
            jdbcUtil.setSql(tablet_q);      // JDBCUtil 에 query 설정
               
            try { 
            	ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행      
                // HashMap<String, Integer> map = new HashMap<String, Integer>();
                Ranking dto = new Ranking();      
             
                while (rs.next()) {   
                         // 하나의 ProductLike 객체 생성 후 정보 설정
                   
                  String pId = rs.getString("PRODUCT_ID");   //커서로 지금 들어온 값 pId
                   String name = rs.getString("NAME");
                   
                   int flag = 0;
                   if (rankList.size() != 0){            
                      //리스트 안에 하나이상의 값이 존재한다면
                         for(int i = 0; i < rankList.size(); i++) {   //리스트 사이즈만큼 반복   -> 리스트안에 pId 있는지 확인
                                                 
                            String getId = rankList.get(i).getProductId().toString();   //리스트 순환 중, 현재의 pId 값
                           
                            if(pId.equals(getId)) {   //리스트안에 지금 들어온 pId가 이미 존재한다면
                               int likeCount = rankList.get(i).getLikeCount();
                               
                               likeCount += 1;
                               rankList.get(i).setLikeCount(likeCount);   //like + 1을 setting
                              
                            
                               flag = 1;   //리스트안에 지금 들어온 pId가 이미 존재한다면 flag = 1 변경
                               break;
                            }
                         }
                      //전체 탐색 후 
                         int size = rankList.size();
                         if (flag == 0) {   //사이즈 != 0 && 리스트 안에 pId가 없을경우
                            Ranking dto1 = new Ranking();      
                            
                            dto1.setProductId(pId);   //dto에 pId저장
                            dto1.setName(name);
                            dto1.setLikeCount(1);
                            rankList.add(dto1);
                          
                            //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                         }   
                         
                    }
                   else   //처음 들어오는 값
                   {
                      Ranking dto2 = new Ranking();      
                      
                      dto2.setProductId(pId);   //dto에 pId저장
                      dto2.setName(name);
                      dto2.setLikeCount(1);
                      rankList.add(dto2);
                     
                      //add해주면서 0으로 초기화 해줬으니까 getLikeCount = 0 이어야 함!!!
                   }
                   
                }
                //rankList 리스트 삽입 끝
                
                //rankList 내림차순 정렬
                   rankList.sort(new Comparator<Ranking>() {
                              @Override
                              public int compare(Ranking arg0, Ranking arg1) {
                                     // TODO Auto-generated method stub
                                     int age0 = arg0.getLikeCount();
                                     int age1 = arg1.getLikeCount();
                                     if (age0 == age1)
                                           return 0;
                                     else if (age1 > age0)
                                           return 1;
                                     else
                                           return -1;
                              }
                       });
                    
                 return rankList;
                
            } catch (Exception ex) {
               ex.printStackTrace();
            } finally {
               jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
            }      
                  
            return null;   
         }
      

   

   public int insertProductLike(ProductLike productLike) {
      int result = 0;
      String insertQuery = "INSERT INTO PRODUCT_LIKE (PRODUCT_ID, USER_ID) " +
                      "VALUES (?, ?) ";
      
      // query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성 물음표 다 받아오기 
      Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
      jdbcUtil.setSql(insertQuery);         // JDBCUtil 에 insert 문 설정
      jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
            
      try {            
         result = jdbcUtil.executeUpdate();      // insert 문 실행
         System.out.println(productLike.getProductId() + " 제품정보가 삽입되었습니다.");
      } catch (SQLException ex) {
         System.out.println("입력오류 발생!!!");
         if (ex.getErrorCode() == 1)
            System.out.println("동일한 제품정보가 이미 존재합니다."); 
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
      }      
      return result;      // insert 에 의해 반영된 레코드 수 반환   
   }


   

   public int deleteProductLike(ProductLike productLike) { //질문
      String deleteQuery = "DELETE FROM PRODUCT_LIKE WHERE PRODUCT_ID = ? AND USER_ID = ? ";
      
      Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
      jdbcUtil.setSql(deleteQuery);         // JDBCUtil 에 query 문 설정
      jdbcUtil.setParameters(param);         // JDBCUtil 에 매개변수 설정
      
      try {
         int result = jdbcUtil.executeUpdate();      // delete 문 실행
         return result;                  // delete 에 의해 반영된 레코드 수 반환
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
      }
      return 0;
   }
   
   
   public List<String> getPhoneLikeListById(String user) {// 사용자가 phone에서 좋아요 누른거 리스트 
      System.out.println("getPhoneLikeListById 메소드에 들어옴");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, PHONE P WHERE P.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil 에 query 문 설정
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in phone");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String phoneId = rs.getString("PRODUCT_ID");
      
            myProdList.add(phoneId);
         }
         System.out.println("쿼리문 결과 받아오는 것까지.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
   public List<String> getLaptopLikeListById(String user) {// 사용자가 laptop에서 좋아요 누른거 리스트 
      System.out.println("getLaptopLikeListById 메소드에 들어옴");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, LAPTOP L WHERE L.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil 에 query 문 설정
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in laptop");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String LaptopId = rs.getString("PRODUCT_ID");
      
            myProdList.add(LaptopId);
         }
         System.out.println("쿼리문 결과 받아오는 것까지.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
   public List<String> getCameraLikeListById(String user) {// 사용자가 camera에서 좋아요 누른거 리스트 
      System.out.println("getCameraLikeListById 메소드에 들어옴");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, CAMERA C WHERE C.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil 에 query 문 설정
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in camera");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String cameraId = rs.getString("PRODUCT_ID");
      
            myProdList.add(cameraId);
         }
         System.out.println("쿼리문 결과 받아오는 것까지.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
   public List<String> getTabletLikeListById(String user) {// 사용자가 tablet에서 좋아요 누른거 리스트 
      System.out.println("getTalbetLikeListById 메소드에 들어옴");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, TABLET T WHERE T.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil 에 query 문 설정
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in tablet");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String tabletId = rs.getString("PRODUCT_ID");
      
            myProdList.add(tabletId);
         }
         System.out.println("쿼리문 결과 받아오는 것까지.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
}