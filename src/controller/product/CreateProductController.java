package controller.product;
import java.sql.Date;

// create�� �����ڰ� �ϴ� ����  �̹� �߰����� ���Ŀ� �� �� 
// ���� ���� ��
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.*;
import model.dto.*;

public class CreateProductController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateProductController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int kind = Integer.valueOf(request.getParameter("pKind"));
		try {
			System.out.println("�� ������� �Դ� switch�� ����");
	    	switch(kind) {
		    	case 0:
		    		Phone phone = new Phone();
		    		PhoneDAO phoneDAO = new PhoneDAO();
		    		System.out.println("�� ������� �Դ� ��ü����");
		    		phone.setpBattery(request.getParameter("pBattery"));
		    		phone.setpMemory(request.getParameter("pMemory"));
		    		phone.setpDisplay(request.getParameter("pDisplay"));
		    		phone.setpRAM(request.getParameter("pRam"));
		    		phone.setpSize(Integer.valueOf(request.getParameter("pSize")));
		    		phone.setpCamera(request.getParameter("pCamera"));
		    		phone.setProductId(request.getParameter("productId"));
		    		phone.setpOS(request.getParameter("pOS"));
		            //--
		    		phone.setName(request.getParameter("pName"));
		    		phone.setColor(request.getParameter("pColor"));
		    		phone.setPrice(request.getParameter("pPrice"));
		    		phone.setBrand(request.getParameter("pBrand"));
		    		phone.setReleased_date(Date.valueOf(request.getParameter("pDate")));
		    		phone.setWeight(Double.valueOf(request.getParameter("pWeight")));
		    		phone.setpKind(Integer.valueOf(request.getParameter("pKind")));
		    		phoneDAO.insertPhone(phone);
		    		
		            log.debug("Create Product : {}", phone);
		    		break;
		    	case 1:
		    		Laptop laptop = new Laptop(); 
		    		
		    		LaptopDAO laptopDAO = new LaptopDAO();
		    		laptopDAO.insertLaptop(laptop);
		            log.debug("Create Product : {}", laptop);
		    		break;
		    	case 2:
		    		Camera camera = new Camera();
		    		
		    		CameraDAO cameraDAO = new CameraDAO();
		    		cameraDAO.insertCamera(camera);
		            log.debug("Create Product : {}", camera);
		    		break;
		    	case 3:
		    		Tablet tablet = new Tablet();
		            log.debug("Create Product : {}", tablet);
		    		break;
		    	default:
		    		System.out.println("�� ������� �Դ� default��");
	    	}
	        return "redirect:/management";		// ���� �� ���� ȭ������ redirect
		} catch (Exception e) {		// ���� �߻� �� ȸ������ form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			e.printStackTrace();
			System.out.println("Error in insertProduct");
			return "redirect:/main";
		}
    }
}
