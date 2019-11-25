package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.InforestUserDAO;
import model.dto.InforestUser;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		InforestUser user = new InforestUser();
		InforestUserDAO userDAO = new InforestUserDAO();
		
		try {
			if(userDAO.login(userId, password) == true) {
				HttpSession session = request.getSession();
				user = userDAO.getInforestUserById(userId);
	            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
	            session.setAttribute(UserSessionUtils.USER_POSITION_KEY, user.getPosition());
	            System.out.println("userPosition : " + user.getPosition());
				return "redirect:/main";
			}	
			return "/sign-in.jsp";
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/sign-in.jsp";  			
		}	
    }
}
