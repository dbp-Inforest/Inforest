package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cont.Controller;
import model.User;
import model.dto.InforestUserDTO;
import model.service.ExistingUserException;
import model.service.InforestUserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InforestUserDTO user = new InforestUserDTO(
			request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("name"),
			Integer.valueOf( request.getParameter("age") ),
			Integer.valueOf( request.getParameter("gender") ),
			Integer.valueOf( request.getParameter("position") ),
			request.getParameter("introduce") );
        log.debug("Create User : {}", user);

		try {
			InforestUserManager manager = InforestUserManager.getInstance();
			manager.create(user);
	        return "redirect:/user/list";		// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}
