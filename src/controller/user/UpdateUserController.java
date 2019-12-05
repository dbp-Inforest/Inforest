package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.InforestUserDAO;
import model.dto.InforestUser;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		InforestUser updateUser = new InforestUser(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				Integer.valueOf( request.getParameter("age") ),
				Integer.valueOf( request.getParameter("gender") ),
				Integer.valueOf( request.getParameter("position") ),
				request.getParameter("introduce") );
    	
    	log.debug("Update User : {}", updateUser);

		InforestUserDAO manager = new InforestUserDAO();
		manager.updateInforestUser(updateUser);
		System.out.println("유저 수정 완료!");
        return "redirect:/mypage";			
    }
}
