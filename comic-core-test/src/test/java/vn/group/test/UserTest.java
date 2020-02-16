//package vn.group.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.Test;
//import vn.group.dal.UserDAL;
//import vn.group.dal.UserDALImpl;
//import vn.group.web.SpringConfiguration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ContextConfiguration( classes = SpringConfiguration.class)
//public class UserTest extends AbstractTestNGSpringContextTests {
//
//    public void findAll(){
//
//    }
//    @Autowired
//    UserDAL userDAL;
//    @Test
//    public void findByProperty(){
//        UserDAL userDAL = new UserDALImpl();
//        Map<String,String> mapProperties = new HashMap<String,String>();
//        mapProperties.put("userid","admin");
//        userDAL.findByProperty(mapProperties,null ,null ,null,null);
//    }
//}
