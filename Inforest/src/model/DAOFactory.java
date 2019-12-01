package model;

import model.dao.CameraDAO;
import model.dao.InforestUserDAO;
import model.dao.LaptopDAO;
import model.dao.PCommentDAO;
import model.dao.PhoneDAO;
import model.dao.PostingDAO;
import model.dao.ProductLikeDAO;
import model.dao.TabletDAO;

// DAO 를 구현한 Implementation 객체를 생성하는 클래스
public class DAOFactory {
	
	public ProductLikeDAO getProductLikeDAO() {
		return new ProductLikeDAO();	
	}
	
	// TabletDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public TabletDAO getTabletDAO() {
		return new TabletDAO();	
	}
	
	 // CameraDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public CameraDAO getCameraDAO() {
	  return new CameraDAO();       
	}
	
	// InforestUserDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public InforestUserDAO getUserDAO() {
		return new InforestUserDAO();      
	}
	
	// LaptopDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public LaptopDAO getLaptopDAO() {
		return new LaptopDAO();       
	}	
	
	// PComment 를 위한 RDB 용 DAO Implementation 객체를 반환
	public PCommentDAO getPCommentDAO() {
	   return new PCommentDAO();       
	}
	   
	// PhoneDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public PhoneDAO getPhoneDAO() {
		return new PhoneDAO();       
	}
	   
	// PostingDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public PostingDAO getPostingDAO() {
		return new PostingDAO();       
	}		
}
