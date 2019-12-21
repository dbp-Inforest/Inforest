package model.dto;

public class ProductLike extends Product {

   private String productId = null;
   private String userId = null;
   
   public ProductLike() {}; // 奄沙 持失切
   
   public ProductLike(String productId, String userId) { // 持失切
      this.productId = productId;
      this.userId = userId;
   };
   
   public String getProductId() {
      return productId;
   }
   public void setProductId(String productId) {
      this.productId = productId;
   }
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
}