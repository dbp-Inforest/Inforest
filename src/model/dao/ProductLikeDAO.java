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
            List<Ranking> rankList = new ArrayList<Ranking>();      // ��ü list��ü
            
            jdbcUtil.setSql(phone_q);      // JDBCUtil �� query ����
               
            try { 
               ResultSet rs = jdbcUtil.executeQuery();      // query �� ����      
               // HashMap<String, Integer> map = new HashMap<String, Integer>();
               Ranking dto = new Ranking();      
            
               while (rs.next()) {   
                        // �ϳ��� ProductLike ��ü ���� �� ���� ����
                  
                 String pId = rs.getString("PRODUCT_ID");   //Ŀ���� ���� ���� �� pId
                  String name = rs.getString("NAME");
                  
                  int flag = 0;
                  if (rankList.size() != 0){            
                     //����Ʈ �ȿ� �ϳ��̻��� ���� �����Ѵٸ�
                        for(int i = 0; i < rankList.size(); i++) {   //����Ʈ �����ŭ �ݺ�   -> ����Ʈ�ȿ� pId �ִ��� Ȯ��
                                                
                           String getId = rankList.get(i).getProductId().toString();   //����Ʈ ��ȯ ��, ������ pId ��
                          
                           if(pId.equals(getId)) {   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ�
                              int likeCount = rankList.get(i).getLikeCount();
                              
                              likeCount += 1;
                              rankList.get(i).setLikeCount(likeCount);   //like + 1�� setting
                             
                           
                              flag = 1;   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ� flag = 1 ����
                              break;
                           }
                        }
                     //��ü Ž�� �� 
                        int size = rankList.size();
                        if (flag == 0) {   //������ != 0 && ����Ʈ �ȿ� pId�� �������
                           Ranking dto1 = new Ranking();      
                           
                           dto1.setProductId(pId);   //dto�� pId����
                           dto1.setName(name);
                           dto1.setLikeCount(1);
                           rankList.add(dto1);
                         
                           //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                        }   
                        
                   }
                  else   //ó�� ������ ��
                  {
                     Ranking dto2 = new Ranking();      
                     
                     dto2.setProductId(pId);   //dto�� pId����
                     dto2.setName(name);
                     dto2.setLikeCount(1);
                     rankList.add(dto2);
                    
                     //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                  }
                  
               }
               //rankList ����Ʈ ���� ��
               
               //rankList �������� ����
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
               jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
            }      
                  
            return null;   
         }
      
      
      public List<Ranking> getLaptopLikeList() {
         List<Ranking> rankList = new ArrayList<Ranking>();      // ��ü list��ü
         
         jdbcUtil.setSql(laptop_q);      // JDBCUtil �� query ����
            
         try { 
        	 ResultSet rs = jdbcUtil.executeQuery();      // query �� ����      
             // HashMap<String, Integer> map = new HashMap<String, Integer>();
             Ranking dto = new Ranking();      
          
             while (rs.next()) {   
                      // �ϳ��� ProductLike ��ü ���� �� ���� ����
                
               String pId = rs.getString("PRODUCT_ID");   //Ŀ���� ���� ���� �� pId
                String name = rs.getString("NAME");
                
                int flag = 0;
                if (rankList.size() != 0){            
                   //����Ʈ �ȿ� �ϳ��̻��� ���� �����Ѵٸ�
                      for(int i = 0; i < rankList.size(); i++) {   //����Ʈ �����ŭ �ݺ�   -> ����Ʈ�ȿ� pId �ִ��� Ȯ��
                                              
                         String getId = rankList.get(i).getProductId().toString();   //����Ʈ ��ȯ ��, ������ pId ��
                        
                         if(pId.equals(getId)) {   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ�
                            int likeCount = rankList.get(i).getLikeCount();
                            
                            likeCount += 1;
                            rankList.get(i).setLikeCount(likeCount);   //like + 1�� setting
                           
                         
                            flag = 1;   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ� flag = 1 ����
                            break;
                         }
                      }
                   //��ü Ž�� �� 
                      int size = rankList.size();
                      if (flag == 0) {   //������ != 0 && ����Ʈ �ȿ� pId�� �������
                         Ranking dto1 = new Ranking();      
                         
                         dto1.setProductId(pId);   //dto�� pId����
                         dto1.setName(name);
                         dto1.setLikeCount(1);
                         rankList.add(dto1);
                       
                         //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                      }   
                      
                 }
                else   //ó�� ������ ��
                {
                   Ranking dto2 = new Ranking();      
                   
                   dto2.setProductId(pId);   //dto�� pId����
                   dto2.setName(name);
                   dto2.setLikeCount(1);
                   rankList.add(dto2);
                  
                   //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                }
                
             }
             //rankList ����Ʈ ���� ��
             
             //rankList �������� ����
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
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
         }      
               
         return null;   
      }
      
      public List<Ranking> getCameraLikeList() {
         List<Ranking> rankList = new ArrayList<Ranking>();      // ��ü list��ü
         
         jdbcUtil.setSql(camera_q);      // JDBCUtil �� query ����
            
         try { 
        	 ResultSet rs = jdbcUtil.executeQuery();      // query �� ����      
             // HashMap<String, Integer> map = new HashMap<String, Integer>();
             Ranking dto = new Ranking();      
          
             while (rs.next()) {   
                      // �ϳ��� ProductLike ��ü ���� �� ���� ����
                
               String pId = rs.getString("PRODUCT_ID");   //Ŀ���� ���� ���� �� pId
                String name = rs.getString("NAME");
                
                int flag = 0;
                if (rankList.size() != 0){            
                   //����Ʈ �ȿ� �ϳ��̻��� ���� �����Ѵٸ�
                      for(int i = 0; i < rankList.size(); i++) {   //����Ʈ �����ŭ �ݺ�   -> ����Ʈ�ȿ� pId �ִ��� Ȯ��
                                              
                         String getId = rankList.get(i).getProductId().toString();   //����Ʈ ��ȯ ��, ������ pId ��
                        
                         if(pId.equals(getId)) {   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ�
                            int likeCount = rankList.get(i).getLikeCount();
                            
                            likeCount += 1;
                            rankList.get(i).setLikeCount(likeCount);   //like + 1�� setting
                           
                         
                            flag = 1;   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ� flag = 1 ����
                            break;
                         }
                      }
                   //��ü Ž�� �� 
                      int size = rankList.size();
                      if (flag == 0) {   //������ != 0 && ����Ʈ �ȿ� pId�� �������
                         Ranking dto1 = new Ranking();      
                         
                         dto1.setProductId(pId);   //dto�� pId����
                         dto1.setName(name);
                         dto1.setLikeCount(1);
                         rankList.add(dto1);
                       
                         //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                      }   
                      
                 }
                else   //ó�� ������ ��
                {
                   Ranking dto2 = new Ranking();      
                   
                   dto2.setProductId(pId);   //dto�� pId����
                   dto2.setName(name);
                   dto2.setLikeCount(1);
                   rankList.add(dto2);
                  
                   //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                }
                
             }
             //rankList ����Ʈ ���� ��
             
             //rankList �������� ����
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
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
         }      
               
         return null;   
      }
      
      public List<Ranking> getTabletLikeList() {
            List<Ranking> rankList = new ArrayList<Ranking>();      // ��ü list��ü
            
            jdbcUtil.setSql(tablet_q);      // JDBCUtil �� query ����
               
            try { 
            	ResultSet rs = jdbcUtil.executeQuery();      // query �� ����      
                // HashMap<String, Integer> map = new HashMap<String, Integer>();
                Ranking dto = new Ranking();      
             
                while (rs.next()) {   
                         // �ϳ��� ProductLike ��ü ���� �� ���� ����
                   
                  String pId = rs.getString("PRODUCT_ID");   //Ŀ���� ���� ���� �� pId
                   String name = rs.getString("NAME");
                   
                   int flag = 0;
                   if (rankList.size() != 0){            
                      //����Ʈ �ȿ� �ϳ��̻��� ���� �����Ѵٸ�
                         for(int i = 0; i < rankList.size(); i++) {   //����Ʈ �����ŭ �ݺ�   -> ����Ʈ�ȿ� pId �ִ��� Ȯ��
                                                 
                            String getId = rankList.get(i).getProductId().toString();   //����Ʈ ��ȯ ��, ������ pId ��
                           
                            if(pId.equals(getId)) {   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ�
                               int likeCount = rankList.get(i).getLikeCount();
                               
                               likeCount += 1;
                               rankList.get(i).setLikeCount(likeCount);   //like + 1�� setting
                              
                            
                               flag = 1;   //����Ʈ�ȿ� ���� ���� pId�� �̹� �����Ѵٸ� flag = 1 ����
                               break;
                            }
                         }
                      //��ü Ž�� �� 
                         int size = rankList.size();
                         if (flag == 0) {   //������ != 0 && ����Ʈ �ȿ� pId�� �������
                            Ranking dto1 = new Ranking();      
                            
                            dto1.setProductId(pId);   //dto�� pId����
                            dto1.setName(name);
                            dto1.setLikeCount(1);
                            rankList.add(dto1);
                          
                            //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                         }   
                         
                    }
                   else   //ó�� ������ ��
                   {
                      Ranking dto2 = new Ranking();      
                      
                      dto2.setProductId(pId);   //dto�� pId����
                      dto2.setName(name);
                      dto2.setLikeCount(1);
                      rankList.add(dto2);
                     
                      //add���ָ鼭 0���� �ʱ�ȭ �������ϱ� getLikeCount = 0 �̾�� ��!!!
                   }
                   
                }
                //rankList ����Ʈ ���� ��
                
                //rankList �������� ����
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
               jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
            }      
                  
            return null;   
         }
      

   

   public int insertProductLike(ProductLike productLike) {
      int result = 0;
      String insertQuery = "INSERT INTO PRODUCT_LIKE (PRODUCT_ID, USER_ID) " +
                      "VALUES (?, ?) ";
      
      // query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ���� ����ǥ �� �޾ƿ��� 
      Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
      jdbcUtil.setSql(insertQuery);         // JDBCUtil �� insert �� ����
      jdbcUtil.setParameters(param);         // JDBCUtil �� �Ű����� ����
            
      try {            
         result = jdbcUtil.executeUpdate();      // insert �� ����
         System.out.println(productLike.getProductId() + " ��ǰ������ ���ԵǾ����ϴ�.");
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


   

   public int deleteProductLike(ProductLike productLike) { //����
      String deleteQuery = "DELETE FROM PRODUCT_LIKE WHERE PRODUCT_ID = ? AND USER_ID = ? ";
      
      Object[] param = new Object[] {productLike.getProductId(), productLike.getUserId()};
      jdbcUtil.setSql(deleteQuery);         // JDBCUtil �� query �� ����
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
   
   
   public List<String> getPhoneLikeListById(String user) {// ����ڰ� phone���� ���ƿ� ������ ����Ʈ 
      System.out.println("getPhoneLikeListById �޼ҵ忡 ����");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, PHONE P WHERE P.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil �� query �� ����
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in phone");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String phoneId = rs.getString("PRODUCT_ID");
      
            myProdList.add(phoneId);
         }
         System.out.println("������ ��� �޾ƿ��� �ͱ���.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
   public List<String> getLaptopLikeListById(String user) {// ����ڰ� laptop���� ���ƿ� ������ ����Ʈ 
      System.out.println("getLaptopLikeListById �޼ҵ忡 ����");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, LAPTOP L WHERE L.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil �� query �� ����
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in laptop");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String LaptopId = rs.getString("PRODUCT_ID");
      
            myProdList.add(LaptopId);
         }
         System.out.println("������ ��� �޾ƿ��� �ͱ���.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
   public List<String> getCameraLikeListById(String user) {// ����ڰ� camera���� ���ƿ� ������ ����Ʈ 
      System.out.println("getCameraLikeListById �޼ҵ忡 ����");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, CAMERA C WHERE C.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil �� query �� ����
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in camera");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String cameraId = rs.getString("PRODUCT_ID");
      
            myProdList.add(cameraId);
         }
         System.out.println("������ ��� �޾ƿ��� �ͱ���.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
   public List<String> getTabletLikeListById(String user) {// ����ڰ� tablet���� ���ƿ� ������ ����Ʈ 
      System.out.println("getTalbetLikeListById �޼ҵ忡 ����");

      List<String> myProdList = new ArrayList<String>();
      
      String query = "SELECT Z.PRODUCT_ID FROM ( SELECT PRODUCT_ID FROM PRODUCT_LIKE WHERE USER_ID = ? ) Z, TABLET T WHERE T.PRODUCT_ID = Z.PRODUCT_ID ";
      jdbcUtil.setSql(query);         // JDBCUtil �� query �� ����
      Object[] param = new Object[] {user};
      jdbcUtil.setParameters(param);   
      
      System.out.println("setParam in tablet");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery(); 
      
            
         while (rs.next()) { 
            String tabletId = rs.getString("PRODUCT_ID");
      
            myProdList.add(tabletId);
         }
         System.out.println("������ ��� �޾ƿ��� �ͱ���.");
         
         return myProdList;      
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
            jdbcUtil.close();      
       }      

      return myProdList;
   }
}