package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";
    public static final String USER_POSITION_KEY = "position";

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
