import com.aTourism.ZhouXY.bean.User;
import com.aTourism.ZhouXY.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserInfoTest {
    private static SqlSession session;

    private static ApplicationContext ac = new ClassPathXmlApplicationContext("com/aTourism/ZhouXY/config/ApplicationContext.xml");

    static{
        session = (SqlSession) ac.getBean("sqlSession");
        System.out.println(session);
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("ZhouXY");
        user.setUserPassword("zxy");
        UserService userService = (UserService) ac.getBean("userService");
        int resutltInt = userService.addUser(user);
        System.out.println(resutltInt);
    }
}
