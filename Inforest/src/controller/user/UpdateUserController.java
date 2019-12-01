package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cont.Controller;
import model.service.InforestUserManager;
import model.User;
import model.dto.InforestUserDTO;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		InforestUserDTO updateUser = new InforestUserDTO(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				Integer.valueOf( request.getParameter("age") ),
				Integer.valueOf( request.getParameter("gender") ),
				Integer.valueOf( request.getParameter("position") ),
				request.getParameter("introduce") );
    	
    	log.debug("Update User : {}", updateUser);

		InforestUserManager manager = InforestUserManager.getInstance();
		manager.update(updateUser);			
        return "redirect:/user/list";			
    }
}
