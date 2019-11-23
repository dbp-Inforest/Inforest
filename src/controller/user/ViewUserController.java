package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserNotFoundException;
import model.dao.InforestUserDAO;
import model.dto.InforestUser;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/register/form";		// login form ��û���� redirect
        }
    	
		InforestUserDAO manager = new InforestUserDAO();
		String userId = request.getParameter("userId");
		
		InforestUser user = null;
		try {
			user = manager.getInforestUserById(userId);	// ����� ���� �˻�
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/login";
		}	
		
		request.setAttribute("user", user);		// ����� ���� ����				
		return "/Inforest/home.jsp";				// ����� ���� ȭ������ �̵�
    }
}
