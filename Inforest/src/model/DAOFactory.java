package model;

import model.dao.CameraDAO;
import model.dao.InforestUserDAO;
import model.dao.LaptopDAO;
import model.dao.PCommentDAO;
import model.dao.PhoneDAO;
import model.dao.PostingDAO;
import model.dao.ProductLikeDAO;
import model.dao.TabletDAO;

// DAO �� ������ Implementation ��ü�� �����ϴ� Ŭ����
public class DAOFactory {
	
	public ProductLikeDAO getProductLikeDAO() {
		return new ProductLikeDAO();	
	}
	
	// TabletDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public TabletDAO getTabletDAO() {
		return new TabletDAO();	
	}
	
	 // CameraDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public CameraDAO getCameraDAO() {
	  return new CameraDAO();       
	}
	
	// InforestUserDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public InforestUserDAO getUserDAO() {
		return new InforestUserDAO();      
	}
	
	// LaptopDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public LaptopDAO getLaptopDAO() {
		return new LaptopDAO();       
	}	
	
	// PComment �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public PCommentDAO getPCommentDAO() {
	   return new PCommentDAO();       
	}
	   
	// PhoneDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public PhoneDAO getPhoneDAO() {
		return new PhoneDAO();       
	}
	   
	// PostingDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public PostingDAO getPostingDAO() {
		return new PostingDAO();       
	}		
}
