package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.CameraDAO;
import model.dao.LaptopDAO;
import model.dao.PhoneDAO;
import model.dao.TabletDAO;

public class RankingProductController implements Controller{

	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DB���� ���ƿ�� count�ϰ� sql�� ���� 10�� �����ֱ� 
		//sql���� dao�� �Լ��� �������� ���⼭�� �Լ�ȣ���ؼ� �����ֱ� 
		
		List phone = phoneDAO.getPhoneList();
		List laptop = laptopDAO.getLaptopList();
		List camera = cameraDAO.getCameraList();
		List tablet = tabletDAO.getTabletList();
		request.setAttribute("phone", phone);
		request.setAttribute("laptop", laptop);
		request.setAttribute("camera", camera);
		request.setAttribute("tablet", tablet);

			
        return "/Inforest/rank.jsp";	
	}

}
