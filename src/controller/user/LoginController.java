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
		InforestUserDAO userDAO = new InforestUserDAO();
		
		try {
			if(userDAO.login(userId, password) == true) {
				HttpSession session = request.getSession();
	            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
				return "redirect:/main";
			}	
			return "/sign-in.jsp";
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/sign-in.jsp";  			
		}	
    }
}
