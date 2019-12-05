package model.dto;

import java.sql.Date;

public class PComment extends Product{

   private String commentId = null;
   private double recommandation = 0;
   private String review = null;
   private String userId = null;
   private Date registDate = null;
   private String productId = null;

   public PComment() {
      super();
      // TODO Auto-generated constructor stub
   }
   public PComment(String review, String userId, String productId) {
      super();
      this.review = review;
      this.userId = userId;
      this.productId = productId;
   }

   public String getProductId() {
      return productId;
   }
   public void setProductId(String productId) {
      this.productId = productId;
   }
   public String getCommentId() {
      return commentId;
   }
   public void setCommentId(String commentId) {
      this.commentId = commentId;
   }
   public double getRecommandation() {
      return recommandation;
   }
   public void setRecommandation(double recommandation) {
      this.recommandation = recommandation;
   }

   public String getReview() {
      return review;
   }

   public void setReview(String review) {
      this.review = review;
   }

   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }

   public Date getRegistDate() {
      return registDate;
   }
   public void setRegistDate(Date registDate) {
      this.registDate = registDate;
   }
   
   
}