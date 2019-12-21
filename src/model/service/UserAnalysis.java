package model.service;

import java.util.ArrayList;
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

	public UserAnalysis(String productId, String kind) {
		System.out.println("UserAnalysis ���� �Ѿ�� id�� : " + productId + " kind�� : " + kind);
		
		String brand = null;
		
		//kind�� �ش��ϴ�  DAO���� productId�� �귣��� �˾ƿ���
		if (kind.equals("0")) {
			brand = phoneDAO.getBrandById(productId);
			System.out.println("�ش� ��ǰ�� �귣����� ? : " + brand);
		}
		else if (kind.equals("1")) {
			brand = laptopDAO.getBrandById(productId);
			System.out.println("�ش� ��ǰ�� �귣����� ? : " + brand);
		}else if (kind.equals("2")) {
			brand = cameraDAO.getBrandById(productId);
			System.out.println("�ش� ��ǰ�� �귣����� ? : " + brand);
		}else if (kind.equals("3")) {
			brand = tabletDAO.getBrandById(productId);
			System.out.println("�ش� ��ǰ�� �귣����� ? : " + brand);
		}
		

		//�귣��� �˾ƿͼ� �� kind���� �귣�� ��ǰ ������ �� List �����
		List<Product> list = new ArrayList<Product>();
		list.addAll(phoneDAO.getProductByBrand(brand));
		list.addAll(laptopDAO.getProductByBrand(brand));
		list.addAll(cameraDAO.getProductByBrand(brand));
		list.addAll(tabletDAO.getProductByBrand(brand));

		System.out.println("�귣�� ����Ʈ �������� ��.");
		

		//List ���� �� �� �� Random���� 5�� �̾Ƽ�...��õ List ���� �� product-detail.jsp ���� ���� �� �ְ�
		
		
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
