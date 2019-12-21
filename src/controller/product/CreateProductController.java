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
		    		System.out.println("�� ������� �Դ� ��ü����");
		    		laptop.setlPurpose(request.getParameter("lPurpose"));
		    		laptop.setlDisplay(request.getParameter("lDisplay"));
		    		laptop.setlCPU(request.getParameter("lCPU"));
		    		laptop.setlRAMMemory(request.getParameter("lRAMMemory"));
		    		laptop.setlOS(request.getParameter("lOS"));
		    		laptop.setProductId(request.getParameter("productId"));
		    		laptop.setlSSD(request.getParameter("lSSD"));
		            //--
		    		laptop.setName(request.getParameter("pName"));
		    		laptop.setColor(request.getParameter("pColor"));
		    		laptop.setPrice(request.getParameter("pPrice"));
		    		laptop.setBrand(request.getParameter("pBrand"));
		    		laptop.setReleased_date(Date.valueOf(request.getParameter("pDate")));
		    		laptop.setWeight(Double.valueOf(request.getParameter("pWeight")));
		    		laptop.setpKind(Integer.valueOf(request.getParameter("pKind")));
		    		
		    		laptopDAO.insertLaptop(laptop);
		            log.debug("Create Product : {}", laptop);
		    		break;
		    	case 2:
		    		Camera camera = new Camera();	    		
		    		CameraDAO cameraDAO = new CameraDAO();
		    		System.out.println("�� ������� �Դ� ��ü����");
		    		camera.setProductId(request.getParameter("productId"));
		    		camera.setcBattery(request.getParameter("cBattery"));
		    		camera.setcPixel(Integer.valueOf(request.getParameter("cPixel")));
		    		camera.setcBurstshot(Double.valueOf(request.getParameter("cBurstshot")));
		    		camera.setcDisplay(Double.valueOf(request.getParameter("cDisplay")));
		    		camera.setcLens(request.getParameter("cLens"));
		    		camera.setcVibration(request.getParameter("cVibration"));
		            //--
		    		camera.setName(request.getParameter("pName"));
		    		camera.setColor(request.getParameter("pColor"));
		    		camera.setPrice(request.getParameter("pPrice"));
		    		camera.setBrand(request.getParameter("pBrand"));
		    		camera.setReleased_date(Date.valueOf(request.getParameter("pDate")));
		    		camera.setWeight(Double.valueOf(request.getParameter("pWeight")));
		    		camera.setpKind(Integer.valueOf(request.getParameter("pKind")));
		    		
		    		cameraDAO.insertCamera(camera);
		            log.debug("Create Product : {}", camera);
		    		break;
		    	case 3:
		    		Tablet tablet = new Tablet();
		    		TabletDAO tabletDAO = new TabletDAO();
		    		tablet.settBattery(request.getParameter("tBattery"));
		    		tablet.settMemory(request.getParameter("tMemory"));
		    		tablet.settOS(request.getParameter("tOS"));
		    		tablet.settSize(Double.valueOf(request.getParameter("tSize"))); 
		    		tablet.setProductId(request.getParameter("productId"));
		            //--
		    		tablet.setName(request.getParameter("pName"));
		    		tablet.setColor(request.getParameter("pColor"));
		    		tablet.setPrice(request.getParameter("pPrice"));
		    		tablet.setBrand(request.getParameter("pBrand"));
		    		tablet.setReleased_date(Date.valueOf(request.getParameter("pDate")));
		    		tablet.setWeight(Double.valueOf(request.getParameter("pWeight")));
		    		tablet.setpKind(Integer.valueOf(request.getParameter("pKind")));
		    		
		    		tabletDAO.insertTablet(tablet);
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
