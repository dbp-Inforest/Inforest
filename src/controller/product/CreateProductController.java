package controller.product;
import java.sql.Date;

// create는 관리자가 하는 것임  이번 중간구현 이후에 할 것 
// 지은 구현 중
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
			System.out.println("자 여기까지 왔다 switch문 시작");
	    	switch(kind) {
		    	case 0:
		    		Phone phone = new Phone();
		    		PhoneDAO phoneDAO = new PhoneDAO();
		    		System.out.println("자 여기까지 왔다 객체만듬");
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
		    		System.out.println("자 여기까지 왔다 객체만듬");
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
		    		System.out.println("자 여기까지 왔다 객체만듬");
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
		    		System.out.println("자 여기까지 왔다 default문");
	    	}
	        return "redirect:/management";		// 성공 시 관리 화면으로 redirect
		} catch (Exception e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			e.printStackTrace();
			System.out.println("Error in insertProduct");
			return "redirect:/main";
		}
    }
}
