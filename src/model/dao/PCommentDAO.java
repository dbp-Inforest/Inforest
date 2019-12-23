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
      // 기본 쿼리와 합쳐짐 
      String allQuery = query  + "FROM P_COMMENT ";
      jdbcUtil.setSqlAndParameters(allQuery ,null);       // JDBCUtil 에 query 설정
      try { 
         ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행         
         List<PComment> list = new ArrayList<PComment>();      // LaptopDTO 객체들을 담기위한 list 객체
         if(rs == null)
            System.out.println("RS가 NULL입니다!!!!");
         
         while (rs.next()) {   
            PComment dto = new PComment();      // 하나의 LaptopDTO 객체 생성 후 정보 설정
            dto.setCommentId(rs.getDouble("COMMENT_ID"));
            dto.setRecommandation(rs.getDouble("RECOMMENDATION"));
            dto.setReview(rs.getString("REVIEW"));
            dto.setUserId(rs.getString("USER_ID"));
            dto.setRegistDate(rs.getDate("REGIST_DATE"));
            dto.setProductId(rs.getString("PRODUCT_ID"));
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

   public int insertPComment(PComment pComment) {
      int result = 0;
      System.out.println("insert문 들어옴");
      String insertQuery = "INSERT INTO P_COMMENT (COMMENT_ID, REVIEW, USER_ID, REGIST_DATE, PRODUCT_ID)  " +
             " VALUES (SEQ_ID.NEXTVAL, ?, ?, SYSDATE, ?) ";
      Object[] param = new Object[] {pComment.getReview(), pComment.getUserId(), pComment.getProductId()};
      
      jdbcUtil.setSql(insertQuery);
      jdbcUtil.setParameters(param);
      try {      
         result = jdbcUtil.executeUpdate();      // insert 문 실행
         System.out.println(pComment.getUserId() + " 댓글이 삽입되었습니다.");}
      catch (SQLException ex) {
         System.out.println("SQL 오류 발생");
         ex.printStackTrace();
      }
      catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
      }      
      
      return result;      // insert 에 의해 반영된 레코드 수 반환   
   }

   public int updatePComment(PComment pComment) {
         int result = 0;
         System.out.println("update문 들어옴");
         String updateQuery = "UPDATE P_COMMENT " + 
               "SET REVIEW = ? AND REGIST_DATE = ?" + 
               "WHERE USER_ID = ?";
         Object[] param = new Object[] {pComment.getReview(), pComment.getRegistDate(), pComment.getUserId()};
         
         jdbcUtil.setSqlAndParameters(updateQuery, param);
        
         try {      
            result = jdbcUtil.executeUpdate();      // insert 문 실행
            System.out.println(pComment.getUserId() + " 댓글이 수정되었습니다.");}
         catch (SQLException ex) {
            System.out.println("SQL 오류 발생");
            ex.printStackTrace();
         }
         catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
         } finally {      
            jdbcUtil.commit();
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
         }      
         
         return result;      // update에 의해 반영된 레코드 수 반환   
   }

   public int deletePComment(String cId) {
      String deleteQuery = "DELETE FROM P_COMMENT WHERE COMMENT_ID = ?";
      int result=0;
      Object[] param = new Object[] {cId};
      jdbcUtil.setSqlAndParameters( deleteQuery , param); 
      try {
         result = jdbcUtil.executeUpdate();      // delete 문 실행
         return result;                  // delete 에 의해 반영된 레코드 수 반환
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
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
   public List<PComment> getPCommentById(String pcId) { //product-detail에 따른 각각의 제품마다 댓글리스트를 보여줘야함 
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
             PComment dto = new PComment();      // 하나의 LaptopDTO 객체 생성 후 정보 설정
             dto.setCommentId(rs.getDouble("COMMENT_ID"));
             dto.setRecommandation(rs.getDouble("RECOMMENDATION"));
             dto.setReview(rs.getString("REVIEW"));
             dto.setUserId(rs.getString("USER_ID"));
             dto.setRegistDate(rs.getDate("REGIST_DATE"));
             dto.setProductId(rs.getString("PRODUCT_ID"));
             list.add(dto);      // list 객체에 정보를 설정한 LaptopDTO 객체 저장
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