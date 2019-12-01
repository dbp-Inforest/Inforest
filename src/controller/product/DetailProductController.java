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
  		
		//phone.jsp, camera.jsp등에서 파라미터로 kind2와 pId를 전달받아서
		//kind의 종류에 따라서 
		//그안의 함수를 이용하여 pid를 찾고 setAttribute로 해당pid의 세부정보를 넘겨주고
		// product-detail.jsp에서 컨트롤러부터 넘겨받은 pid세부정보의 컬럼 하나하나를 값으로 넣어준다. 
		
		//String pId = request.getParameter("");
    	if(request.getMethod().equals("GET")) {
    		String kind = request.getParameter("kind2"); //0,1,2,3
    		String pid = request.getParameter("pId");
    		System.out.println("kind: "+kind+ " pid: " + pid);
    		
    		if(kind.equals("0")) { //phone
    			Phone phoneDetail = phoneDAO.getPhoneById(pid);
    			request.setAttribute("phoneDetail", phoneDetail);	
    	        return "/phone-detail.jsp";	
    			
    		}else if(kind.equals("1")){ //laptop
    			Laptop laptopDetail = laptopDAO.getLaptopById(pid);
    			request.setAttribute("laptopDetail", laptopDetail);	
    	        return "/laptop-detail.jsp";	
    	        
    	    }else if(kind.equals("2")) { //camera
    			Camera cameraDetail = cameraDAO.getCameraById(pid);
    			request.setAttribute("cameraDetail", cameraDetail);	
    	        return "/camera-detail.jsp";	
    	        
    		}else if(kind.equals("3")) { //tablet
    			Tablet tabletDetail = tabletDAO.getTabletById(pid);
    			request.setAttribute("tabletDetail", tabletDetail);	
    	        return "/tablet-detail.jsp";	
    		}		
		
    		System.out.println("메인으로 이동");
    		return "redirect:/main";
		
    	}
    	return "redirect:/main";

	}
}
