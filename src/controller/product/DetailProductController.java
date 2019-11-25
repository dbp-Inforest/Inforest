package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.CameraDAO;
import model.dao.LaptopDAO;
import model.dao.PhoneDAO;
import model.dao.TabletDAO;
import model.dto.Camera;
import model.dto.Laptop;
import model.dto.Phone;
import model.dto.Tablet;

public class DetailProductController implements Controller{
	
	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
  		
		//phone.jsp, camera.jsp��� �Ķ���ͷ� kind2�� pId�� ���޹޾Ƽ�
		//kind�� ������ ���� 
		//�׾��� �Լ��� �̿��Ͽ� pid�� ã�� setAttribute�� �ش�pid�� ���������� �Ѱ��ְ�
		// product-detail.jsp���� ��Ʈ�ѷ����� �Ѱܹ��� pid���������� �÷� �ϳ��ϳ��� ������ �־��ش�. 
		
		//String pId = request.getParameter("");
    	if(request.getMethod().equals("GET")) {
    		String kind = request.getParameter("kind2"); //0,1,2,3
    		String pid = request.getParameter("pId");
    		System.out.print("kind: "+kind+ " pid: " + pid);
    		
    		if(kind.equals("0")) { //phone
    			Phone phoneDetail = phoneDAO.getPhoneById("pId");
    			request.setAttribute("phoneDetail", phoneDetail);	
    	        return "/Inforest/product-detail.jsp";	
    			
    		}else if(kind.equals("1")){ //laptop
    			Laptop laptopDetail = laptopDAO.getLaptopById("pId");
    			request.setAttribute("laptopDetail", laptopDetail);	
    	        return "/Inforest/product-detail.jsp";	
    	        
    	    }else if(kind.equals("2")) { //camera
    			Camera cameraDetail = cameraDAO.getCameraById("pId");
    			request.setAttribute("cameraDetail", cameraDetail);	
    	        return "/Inforest/product-detail.jsp";	
    	        
    		}else if(kind.equals("3")) { //tablet
    			Tablet tabletDetail = tabletDAO.getTabletById("pId");
    			request.setAttribute("tabletDetail", tabletDetail);	
    	        return "/Inforest/product-detail.jsp";	
    		}		
		
    		System.out.println("�������� �̵�");
    		return "redirect:/main";
		
    	}
    	return "redirect:/main";

	}
}
