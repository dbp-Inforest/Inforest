package model.dto;

public class Ranking {

   private String productId;
   private String name;
   private int likeCount;
   
   public String getProductId() {
      return productId;
   }
   public void setProductId(String productId) {
      this.productId = productId;
   }
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   
   public int getLikeCount() {
      return likeCount;
   }
   public void setLikeCount(int likeCount) {
      this.likeCount = likeCount;
   }
}