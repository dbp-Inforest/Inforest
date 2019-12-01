package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cont.Controller;
import model.service.InforestUserManager;
import model.service.UserNotFoundException;
import model.User;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
		InforestUserManager manager = InforestUserManager.getInstance();
		String userId = request.getParameter("userId");
		
    	User user = null;
		try {
			user = manager.findUser(userId);	// ����� ���� �˻�
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/list";
		}	
		
		request.setAttribute("user", user);		// ����� ���� ����				
		return "/user/view.jsp";				// ����� ���� ȭ������ �̵�
    }
}
