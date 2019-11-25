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
	// private static final int countPerPage = 100;	// �� ȭ�鿡 ����� ����� ��
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
    			// DAOImpl�� ���� ȣ���Ͽ� �۾� ���� 
  
    					
    					
    			//InforestUserManager manager = InforestUserManager.getInstance();
    			//manager.login(userId, password);
    	
    			// ���ǿ� ����� ���̵� ����
    			//HttpSession session = request.getSession();
                //session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
                
                return "redirect:/user/list";			
    		} catch (Exception e) {
    			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
    			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
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

		// userList ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("userList", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/user/list.jsp";  
		*/
		
    		}	
    }
}
