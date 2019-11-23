package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.InforestUserDAO;
import model.dto.InforestUser;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InforestUser user = new InforestUser(
			request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("name"),
			Integer.valueOf( request.getParameter("age") ),
			Integer.valueOf( Integer.parseInt(request.getParameter("gender")) ),
			Integer.valueOf( request.getParameter("position") ),
			request.getParameter("introduce") );
		
        log.debug("Create User : {}", user);

		try {
			InforestUserDAO manager = new InforestUserDAO();
			manager.insertInforestUser(user);
	        return "redirect:/Inforest/sign-in.jsp";		// 성공 시 로그인 화면으로 redirect
		} catch (Exception e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			System.out.println("Error in insertUser");
			return "/Inforest/sign-up.jsp";
		}
    }
}
