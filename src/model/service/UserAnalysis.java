package model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.dao.*;
import model.dto.*;
import java.util.Random;
/**
 * UserAnalysis -> user ���� ����Ͻ� ���� ������ Ŭ����
 * ���߿� business logic �κ� ������ �� ������ ���� 
 * �������ּ���
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
      System.out.println("UserAnalysis ���� �Ѿ�� id�� : " + productId + " kind�� : " + kind);
      
      String brand = null;
      
      //kind�� �ش��ϴ�  DAO���� productId�� �귣��� �˾ƿ���
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
      

      //�귣��� �˾ƿͼ� �� kind���� �귣�� ��ǰ ������ �� List �����
      List<Product> list = new ArrayList<Product>();
      list.addAll(phoneDAO.getProductByBrand(brand));
      list.addAll(laptopDAO.getProductByBrand(brand));
      list.addAll(cameraDAO.getProductByBrand(brand));
      list.addAll(tabletDAO.getProductByBrand(brand));

      //List Random���� ����
      Collections.shuffle(list);
      
      int size = list.size();
      
      System.out.println("�귣�� �� ����:" + size);
      
      if (size >= 5) {
         //Random���� ���� List���� �� 5���� ��������
         list = list.subList(0, 5); 
      }
      else {
         //���� �귣���� �� ��ǰ���� 5���� �� �Ѵ´ٸ�? kind�� �ش��ϴ� Ranking���� ������ ������ �κ� ä���
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
      

      System.out.println("�ش� �귣��  : " + brand + "/ �� ��ǰ�� : " + list.size());
      System.out.println("<��õ ����Ʈ>");
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