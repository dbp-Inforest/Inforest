package controller.product;

import java.util.List;
import java.util.List;
import model.dao.*;
import model.dto.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.CameraDAO;
import model.dao.LaptopDAO;
import model.dao.PhoneDAO;
import model.dao.TabletDAO;
import model.dto.Ranking;
import model.dao.ProductLikeDAO;

public class RankingProductController implements Controller{

	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	private ProductLikeDAO ProductLikeDAO = new ProductLikeDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DB���� ���ƿ�� count�ϰ� sql�� ���� 10�� �����ֱ� 
		//sql���� dao�� �Լ��� �������� ���⼭�� �Լ�ȣ���ؼ� �����ֱ� 
		List<Ranking> phoneLikeList = ProductLikeDAO.getPhoneLikeList();
		List<Ranking> laptopLikeList = ProductLikeDAO.getLaptopLikeList();
		List<Ranking> cameraLikeList = ProductLikeDAO.getCameraLikeList();
		List<Ranking> tabletLikeList = ProductLikeDAO.getTabletLikeList();
		
		
		List phone = phoneDAO.getPhoneList();
		List laptop = laptopDAO.getLaptopList();
		List camera = cameraDAO.getCameraList();
		List tablet = tabletDAO.getTabletList();
		request.setAttribute("phone", phone);
		request.setAttribute("laptop", laptop);
		request.setAttribute("camera", camera);
		request.setAttribute("phoneR", phoneLikeList);
		request.setAttribute("laptopR", laptopLikeList);
		request.setAttribute("cameraR", cameraLikeList);
		request.setAttribute("tabletR", tabletLikeList);
			
		
        return "redirect: /rank";	
	}

}
