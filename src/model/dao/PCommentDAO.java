package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.PCommentDAO;
import model.dto.PComment;

public class PCommentDAO {

   private static JDBCUtil jdbcUtil = null;
   
   private static String query = "SELECT COMMENT_ID, " +
           "       RECOMMENDATION, " +
           "       REVIEW, " +
           "       USER_ID, " +
           "        REGIST_DATE, " +
           "        PRODUCT_ID  ";
    
   public PCommentDAO() {
      try {
         jdbcUtil = new JDBCUtil();
      }catch (Exception e){
         e.printStackTrace();
      }
   }
   
   public List<PComment> getPCommentList() {
      // �⺻ ������ ������ 
      String allQuery = query  + "FROM P_COMMENT ";
      jdbcUtil.setSqlAndParameters(allQuery ,null);       // JDBCUtil �� query ����
      try { 
         ResultSet rs = jdbcUtil.executeQuery();      // query �� ����         
         List<PComment> list = new ArrayList<PComment>();      // LaptopDTO ��ü���� ������� list ��ü
         if(rs == null)
            System.out.println("RS�� NULL�Դϴ�!!!!");
         
         while (rs.next()) {   
            PComment dto = new PComment();      // �ϳ��� LaptopDTO ��ü ���� �� ���� ����
            dto.setCommentId(rs.getDouble("COMMENT_ID"));
            dto.setRecommandation(rs.getDouble("RECOMMENDATION"));
            dto.setReview(rs.getString("REVIEW"));
            dto.setUserId(rs.getString("USER_ID"));
            dto.setRegistDate(rs.getDate("REGIST_DATE"));
            dto.setProductId(rs.getString("PRODUCT_ID"));
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

   public int insertPComment(PComment pComment) {
      int result = 0;
      System.out.println("insert�� ����");
      String insertQuery = "INSERT INTO P_COMMENT (COMMENT_ID, REVIEW, USER_ID, REGIST_DATE, PRODUCT_ID)  " +
             " VALUES (SEQ_ID.NEXTVAL, ?, ?, SYSDATE, ?) ";
      Object[] param = new Object[] {pComment.getReview(), pComment.getUserId(), pComment.getProductId()};
      
      jdbcUtil.setSql(insertQuery);
      jdbcUtil.setParameters(param);
      try {      
         result = jdbcUtil.executeUpdate();      // insert �� ����
         System.out.println(pComment.getUserId() + " ����� ���ԵǾ����ϴ�.");}
      catch (SQLException ex) {
         System.out.println("SQL ���� �߻�");
         ex.printStackTrace();
      }
      catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
      }      
      
      return result;      // insert �� ���� �ݿ��� ���ڵ� �� ��ȯ   
   }

   public int updatePComment(PComment pComment) {
         int result = 0;
         System.out.println("update�� ����");
         String updateQuery = "UPDATE P_COMMENT " + 
               "SET REVIEW = ? AND REGIST_DATE = ?" + 
               "WHERE USER_ID = ?";
         Object[] param = new Object[] {pComment.getReview(), pComment.getRegistDate(), pComment.getUserId()};
         
         jdbcUtil.setSqlAndParameters(updateQuery, param);
        
         try {      
            result = jdbcUtil.executeUpdate();      // insert �� ����
            System.out.println(pComment.getUserId() + " ����� �����Ǿ����ϴ�.");}
         catch (SQLException ex) {
            System.out.println("SQL ���� �߻�");
            ex.printStackTrace();
         }
         catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
         } finally {      
            jdbcUtil.commit();
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
         }      
         
         return result;      // update�� ���� �ݿ��� ���ڵ� �� ��ȯ   
   }

   public int deletePComment(String cId) {
      String deleteQuery = "DELETE FROM P_COMMENT WHERE COMMENT_ID = ?";
      int result=0;
      Object[] param = new Object[] {cId};
      jdbcUtil.setSqlAndParameters( deleteQuery , param); 
      try {
         result = jdbcUtil.executeUpdate();      // delete �� ����
         return result;                  // delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection ��ȯ
      }
      return result;
   }

/*   public PComment getPCommentByName(String pcName) {
      // TODO Auto-generated method stub
      String searchQuery = query + " FROM P_COMMENT " + " WHERE P_COMMENT.pcName = ?";
      Object[] param = new Object[] {pcName};

      jdbcUtil.setSql(searchQuery);
      jdbcUtil.setParameters(param);
   
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         PComment dto = new PComment();
         while (rs.next()) {
               dto.setCommentId(rs.getString("COMMENT_ID"));
            dto.setRecommandation(rs.getDouble("COMMENT_RECOMMENDATION"));
            dto.setReview(rs.getString("COMMENT_REVIEW"));
            dto.setUserId(rs.getString("COMMENT_USER"));
            dto.setRegistDate(rs.getDate("COMMENT_REGISTDATE"));
            dto.setProductId("COMMENT_PRODUCT");
         }
         return dto;
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      return null;
   }
*/
   public List<PComment> getPCommentById(String pcId) { //product-detail�� ���� ������ ��ǰ���� ��۸���Ʈ�� ��������� 
      // TODO Auto-generated method stub
      String searchQuery = "FROM P_COMMENT " + " WHERE P_COMMENT.PRODUCT_ID = PRODUCT.PRODUCT_ID "
                        +"AND P_COMMENT.PRODUCT_ID = ? ";
      Object[] param = new Object[] {pcId}; 
      jdbcUtil.setSql(searchQuery);
      jdbcUtil.setParameters(param);
   
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         List<PComment> list = new ArrayList<PComment>();
         while (rs.next()) {
             PComment dto = new PComment();      // �ϳ��� LaptopDTO ��ü ���� �� ���� ����
             dto.setCommentId(rs.getDouble("COMMENT_ID"));
             dto.setRecommandation(rs.getDouble("RECOMMENDATION"));
             dto.setReview(rs.getString("REVIEW"));
             dto.setUserId(rs.getString("USER_ID"));
             dto.setRegistDate(rs.getDate("REGIST_DATE"));
             dto.setProductId(rs.getString("PRODUCT_ID"));
             list.add(dto);      // list ��ü�� ������ ������ LaptopDTO ��ü ����
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