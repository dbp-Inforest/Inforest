package model.service;

import java.util.ArrayList;
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

	public UserAnalysis(String productId, String kind) {
		System.out.println("UserAnalysis 까지 넘어옴 id는 : " + productId + " kind는 : " + kind);
		
		String brand = null;
		
		//kind에 해당하는  DAO에서 productId로 브랜드명 알아오기
		if (kind.equals("0")) {
			brand = phoneDAO.getBrandById(productId);
			System.out.println("해당 제품의 브랜드명은 ? : " + brand);
		}
		else if (kind.equals("1")) {
			brand = laptopDAO.getBrandById(productId);
			System.out.println("해당 제품의 브랜드명은 ? : " + brand);
		}else if (kind.equals("2")) {
			brand = cameraDAO.getBrandById(productId);
			System.out.println("해당 제품의 브랜드명은 ? : " + brand);
		}else if (kind.equals("3")) {
			brand = tabletDAO.getBrandById(productId);
			System.out.println("해당 제품의 브랜드명은 ? : " + brand);
		}
		

		//브랜드명 알아와서 각 kind별로 브랜드 제품 가져온 뒤 List 만들기
		List<Product> list = new ArrayList<Product>();
		list.addAll(phoneDAO.getProductByBrand(brand));
		list.addAll(laptopDAO.getProductByBrand(brand));
		list.addAll(cameraDAO.getProductByBrand(brand));
		list.addAll(tabletDAO.getProductByBrand(brand));

		System.out.println("브랜드 리스트 가져오기 됨.");
		

		//List 만든 뒤 그 중 Random으로 5개 뽑아서...추천 List 만든 후 product-detail.jsp 에서 보일 수 있게
		
		
	}
//	
//	public UserAnalysis(UserDAOImpl dao) {
//		super();
//		this.dao = dao;
//	}
//
//	// an example business method
//	public List<User> recommendFriends(String userId) throws Exception {
//		User thisuser = dao.findUser(userId);
//		if (thisuser == null) {
//			throw new Exception("invalid user ID!");
//		}
//		int m1 = userId.indexOf('@');
//		if (m1 == -1) return null;
//		String mserver1 = thisuser.getEmail().substring(m1);
//		
//		List<User> friends = new ArrayList<User>();
//		
//		List<User> userList = dao.findUserList(1, 10000);
//		Iterator<User> userIter = userList.iterator();		
//		while (userIter.hasNext()) {
//			User user = (User)userIter.next();
//			
//			if (user.getUserId().equals(userId)) continue;
//			int m2 = user.getEmail().indexOf('@');
//			if (m2 == -1) continue;
//			String mserver2 = user.getEmail().substring(m2);
//
//			if (mserver1.equals(mserver2)) 
//				friends.add(user);		
//		}
//		return friends;
//	}

}
