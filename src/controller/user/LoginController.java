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
    @SuppressWarnings("static-access")
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	HttpSession session = request.getSession();
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		InforestUserDAO userDAO = new InforestUserDAO();
		InforestUser user = userDAO.getInforestUserById(userId);
		
		System.out.println("login controller");
		try {
			if(userDAO.login(userId, password) == true) {
	            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
	            session.setAttribute(UserSessionUtils.USER_NAME_KEY, user.getName());
	            session.setAttribute(UserSessionUtils.USER_POSITION_KEY, user.getPosition());
	            System.out.println("userPosition : " + user.getPosition());
	            System.out.println("login success : " + user.getUserId());
	            
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
