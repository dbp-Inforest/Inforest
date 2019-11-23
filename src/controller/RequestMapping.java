package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
       // 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/main", new ForwardController("/home.jsp"));   //home.jsp 이동
        mappings.put("/signIn", new ForwardController("/sign-in.jsp"));   //sign-in 이동
        mappings.put("/rank", new ForwardController("/rank.jsp"));   //rank.jsp이동
        mappings.put("/product", new ForwardController("/product.jsp"));   //product.jsp이동
        mappings.put("/post", new ForwardController("/post.jsp"));   //post.jsp이동
        mappings.put("/mypage", new ForwardController("/mypage.jsp"));   //mypage.jsp이동
        mappings.put("/user_login_form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user_login", new LoginController());
        mappings.put("/user_logout", new LogoutController());
        mappings.put("/user_list", new ListUserController());
        mappings.put("/user_view", new ViewUserController());
        mappings.put("/user_register_form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user_register", new RegisterUserController());
        mappings.put("/user_update_form", new UpdateUserFormController());
        mappings.put("/user_update", new UpdateUserController());
        mappings.put("/user_delete", new DeleteUserController());
        mappings.put("/register_form", new ForwardController("/sign-up.jsp"));
        mappings.put("/register", new RegisterUserController());
       
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {   
       // 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}

