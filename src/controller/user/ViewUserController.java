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
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/register/form";		// login form 요청으로 redirect
        }
    	
		InforestUserDAO manager = new InforestUserDAO();
		String userId = request.getParameter("userId");
		
		InforestUser user = null;
		try {
			user = manager.getInforestUserById(userId);	// 사용자 정보 검색
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/login";
		}	
		
		request.setAttribute("user", user);		// 사용자 정보 저장				
		return "/Inforest/home.jsp";				// 사용자 보기 화면으로 이동
    }
}
