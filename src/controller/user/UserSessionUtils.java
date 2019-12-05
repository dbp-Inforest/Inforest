package controller.user;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import model.dao.InforestUserDAO;
import model.dto.InforestUser;
import model.service.UserNotFoundException;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";
    public static final String USER_POSITION_KEY = "position";
    public static final String USER_NAME_KEY = "name";

    /* ���� �α����� ������� ID�� ���� */
    public static String getLoginUserId(HttpSession session) {
        String userId = (String)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }
    
    /* ���� �α����� ������� �������� ���� */
    public static int getLoginUserPosition(HttpSession session) {
        int pos = (int)session.getAttribute(USER_POSITION_KEY);
        return pos;
    }
    
    /* ���� �α����� ������� �̸��� ���� */
    public static String getLoginUserName(HttpSession session) {
        String name = (String)session.getAttribute(USER_NAME_KEY);
        return name;
    }   

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != null) {
            return true;
        }
        return false;
    }

    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginUser(String userId, HttpSession session) {
        String loginUser = getLoginUserId(session);
        if (loginUser == null) {
            return false;
        }
        return loginUser.equals(userId);
    }
}
