package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.user.*;
import controller.product.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
       // �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/main", new ForwardController("/home.jsp"));   //home.jsp �̵�
        
        /* User ���� Mapping */
        mappings.put("/signIn", new ForwardController("/sign-in.jsp"));   // �α���
        mappings.put("/signUp", new ForwardController("/sign-up.jsp")); // ȸ������ â
        mappings.put("/signUpdate", new ForwardController("/sign-update.jsp")); // ���� ���� ����
        mappings.put("/register", new RegisterUserController()); // ȸ������ �Ϸ� (DB����)
        mappings.put("/login", new LoginController());
        mappings.put("/logout", new LogoutController());
        mappings.put("/user_update_form", new UpdateUserFormController()); //
        mappings.put("/user_update", new UpdateUserController());
        mappings.put("/user_delete", new DeleteUserController());
       
        mappings.put("/user_list", new ListUserController());
        mappings.put("/user_view", new ViewUserController());
        
        /* Product ����  Mapping*/
        mappings.put("/productList", new ListProductController());
        mappings.put("/productDetail", new DetailProductController());
        mappings.put("/phone", new ForwardController("/phone.jsp"));
        mappings.put("/productSearch", new ViewProductController());
        mappings.put("/productLike", new LikeProductController());
        
        /* Ranking ���� Mapping */
        mappings.put("/rankCont", new RankingProductController());
     
        /* Admin Management ���� Mapping */
        
        /* �ڵ��� Insert & Update ������ forwarding ���� Mapping */
        mappings.put("/phone_insert", new ForwardController("/phone/phone-insert.jsp")); 
        mappings.put("/phone_update", new ForwardController("/phone/phone-update.jsp")); 

        /* ��Ʈ�� Insert & Update ������ forwarding ���� Mapping */
        mappings.put("/laptop_insert", new ForwardController("/laptop/laptop-insert.jsp")); 
        mappings.put("/laptop_update", new ForwardController("/laptop/laptop-update.jsp"));
        
        /* ī�޶� Insert & Update ������ forwarding ���� Mapping */
        mappings.put("/camera_insert", new ForwardController("/camera/camera-insert.jsp"));
        mappings.put("/camera_update", new ForwardController("/camera/camera-update.jsp")); 

        /* �º� Insert & Update ������ forwarding ���� Mapping */
        mappings.put("/tablet_insert", new ForwardController("/tablet/tablet-insert.jsp"));
        mappings.put("/tablet_update", new ForwardController("/tablet/tablet-update.jsp"));

        /* ��ǰ ���  & ����  & ���� ���� Mapping */
        mappings.put("/insertProduct", new CreateProductController()); // ��ǰ ���
        mappings.put("/updateProduct", new UpdateProductController()); // ��ǰ ���� 
        mappings.put("/deleteProduct", new DeleteProductController()); // ��ǰ ����
        
        /* Home Header ����  Mapping*/
        mappings.put("/rank", new ForwardController("/rank.jsp"));   //rank.jsp�̵�
        mappings.put("/product", new ForwardController("/product.jsp"));   //product.jsp�̵�
        mappings.put("/post", new ForwardController("/post.jsp"));   //post.jsp�̵�
        mappings.put("/mypage", new ForwardController("/mypage.jsp"));   //mypage.jsp�̵�
        mappings.put("/management", new ForwardController("/manage.jsp"));   //management.jsp�� �̵�

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {   
       // �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}

