package model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.dao.*;
import model.dto.*;
import java.util.Random;
/**
 * UserAnalysis -> user 관련 비즈니스 로직 구현한 클래스
 * 나중에 business logic 부분 구현할 때 참고할 예정 
 * 무시해주세요
 */
// an example business class
public class UserAnalysis {
   Random random = new Random();
   
   private PhoneDAO phoneDAO = new PhoneDAO(); 
   private LaptopDAO laptopDAO = new LaptopDAO();
   private CameraDAO cameraDAO = new CameraDAO();
   private TabletDAO tabletDAO = new TabletDAO();
   private ProductLikeDAO productLikeDAO = new ProductLikeDAO();

   public UserAnalysis(String productId, String kind) {
      System.out.println("UserAnalysis 까지 넘어옴 id는 : " + productId + " kind는 : " + kind);
      
      String brand = null;
      
      //kind에 해당하는  DAO에서 productId로 브랜드명 알아오기
      if (kind.equals("0")) {
         brand = phoneDAO.getBrandById(productId);
      }
      else if (kind.equals("1")) {
         brand = laptopDAO.getBrandById(productId);
      }else if (kind.equals("2")) {
         brand = cameraDAO.getBrandById(productId);
      }else if (kind.equals("3")) {
         brand = tabletDAO.getBrandById(productId);
      }
      

      //브랜드명 알아와서 각 kind별로 브랜드 제품 가져온 뒤 List 만들기
      List<Product> list = new ArrayList<Product>();
      list.addAll(phoneDAO.getProductByBrand(brand));
      list.addAll(laptopDAO.getProductByBrand(brand));
      list.addAll(cameraDAO.getProductByBrand(brand));
      list.addAll(tabletDAO.getProductByBrand(brand));

      //List Random으로 섞기
      Collections.shuffle(list);
      
      int size = list.size();
      
      System.out.println("브랜드 총 개수:" + size);
      
      if (size >= 5) {
         //Random으로 섞은 List에서 앞 5개만 가져오기
         list = list.subList(0, 5); 
      }
      else {
         //만약 브랜드의 총 제품수가 5개를 못 넘는다면? kind에 해당하는 Ranking에서 가져와 나머지 부분 채우기
         int empty = 5 - size;
         
         List<Ranking> ranking = new ArrayList<Ranking>();
         String id;
         Product p = new Product();
         
         switch(kind) {
         case "0" : 
            ranking = productLikeDAO.getPhoneLikeList();
            ranking = ranking.subList(0, empty);
            for(int i = 0; i < empty; i++) {
               id = ranking.get(i).getProductId();
               p = phoneDAO.getPhoneById(id);
               list.add(p);
            }
            break;
         case "1" :
            ranking = productLikeDAO.getLaptopLikeList();
            ranking = productLikeDAO.getLaptopLikeList();
            ranking = ranking.subList(0, empty);
            for(int i = 0; i < empty; i++) {
               id = ranking.get(i).getProductId();
               p = laptopDAO.getLaptopById(id);
               list.add(p);
            }
            break;
         case "2" :
            ranking = productLikeDAO.getCameraLikeList();
            ranking = productLikeDAO.getCameraLikeList();
            ranking = ranking.subList(0, empty);
            for(int i = 0; i < empty; i++) {
               id = ranking.get(i).getProductId();
               p = cameraDAO.getCameraById(id);
               list.add(p);
            }
            break;
         case "3" :
            ranking = productLikeDAO.getTabletLikeList();
            ranking = productLikeDAO.getTabletLikeList();
            ranking = ranking.subList(0, empty);
            for(int i = 0; i < empty; i++) {
               id = ranking.get(i).getProductId();
               p = tabletDAO.getTabletById(id);
               list.add(p);
            }
            break;
         }
      }
      

      System.out.println("해당 브랜드  : " + brand + "/ 총 제품수 : " + list.size());
      System.out.println("<추천 리스트>");
      System.out.println(list.get(0).getName());
      System.out.println(list.get(1).getName());
      System.out.println(list.get(2).getName());
      System.out.println(list.get(3).getName());
      System.out.println(list.get(4).getName());
   }
//   
//   public UserAnalysis(UserDAOImpl dao) {
//      super();
//      this.dao = dao;
//   }
//
//   // an example business method
//   public List<User> recommendFriends(String userId) throws Exception {
//      User thisuser = dao.findUser(userId);
//      if (thisuser == null) {
//         throw new Exception("invalid user ID!");
//      }
//      int m1 = userId.indexOf('@');
//      if (m1 == -1) return null;
//      String mserver1 = thisuser.getEmail().substring(m1);
//      
//      List<User> friends = new ArrayList<User>();
//      
//      List<User> userList = dao.findUserList(1, 10000);
//      Iterator<User> userIter = userList.iterator();      
//      while (userIter.hasNext()) {
//         User user = (User)userIter.next();
//         
//         if (user.getUserId().equals(userId)) continue;
//         int m2 = user.getEmail().indexOf('@');
//         if (m2 == -1) continue;
//         String mserver2 = user.getEmail().substring(m2);
//
//         if (mserver1.equals(mserver2)) 
//            friends.add(user);      
//      }
//      return friends;
//   }

}