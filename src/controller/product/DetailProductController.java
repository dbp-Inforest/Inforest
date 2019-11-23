package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.CameraDAO;
import model.dao.LaptopDAO;
import model.dao.PhoneDAO;
import model.dao.TabletDAO;

public class DetailProductController implements Controller{
	
	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
  		
		//productId�� ���� �� ���̺��� ������ �������� ����Ʈ�� ��ȯ�ϱ�  
		//re 
		
        return "/Inforest/product-detail.jsp";	
	}

}
