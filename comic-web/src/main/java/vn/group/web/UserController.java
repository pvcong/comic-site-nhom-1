//package vn.group.web;//package vn.group.configuration;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@RestController
//public class UserController {
//    @Autowired
//    SessionFactory sessionFactory;
//    @RequestMapping("/")
//    public ModelAndView sayHello(HttpServletRequest req, HttpServletResponse response, HttpSession session){
//        sessionFactory.openSession();
//        return new ModelAndView("index");
//    }
//}
