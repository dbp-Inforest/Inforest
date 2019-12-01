package controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cont.Controller;
import controller.user.UserSessionUtils;
import model.Community;
import model.service.UserManager;

public class ListProductController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수
	/*
	private PhoneDAO phoneDAO = new PhoneDAO(); 
	private LaptopDAO laptopDAO = new LaptopDAO();
	private CameraDAO cameraDAO = new CameraDAO();
	private TabletDAO tabletDAO = new TabletDAO();
	*/
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
       		
    		String kind = request.getParameter("kind"); //0,1,2,3,4
 
    		try {
    			// DAOImpl을 직접 호출하여 작업 수행 
  
    					
    					
    			//InforestUserManager manager = InforestUserManager.getInstance();
    			//manager.login(userId, password);
    	
    			// 세션에 사용자 이이디 저장
    			//HttpSession session = request.getSession();
                //session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
                
                return "redirect:/user/list";			
    		} catch (Exception e) {
    			/* UserNotFoundException이나 PasswordMismatchException 발생 시
    			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
    			 */
                request.setAttribute("loginFailed", true);
    			request.setAttribute("exception", e);
                return "/user/loginForm.jsp";	    	
    	
    	
    	
    	/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
		InforestUserManager manager = InforestUserManager.getInstance();
		List<User> userList = manager.findUserList();
		// List<User> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("userList", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/user/list.jsp";  
		*/
		
    		}	
    }
}
