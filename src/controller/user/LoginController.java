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
		
		//try {
			System.out.print(userId + password);
		
			boolean user = new InforestUserDAO().login(userId, password);
			
			if(user == true) {
				return "redirect:/main";
			}
			else {
				//request.setAttribute("loginFailed", true);
				//request.setAttribute("exception", e);
	            return "/Inforest/sign-in.jsp";
			}
			
			// 세션에 사용자 이이디 저장
			//HttpSession session = request.getSession();
            //session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            			
		//} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            			
		//}	
    }
}
