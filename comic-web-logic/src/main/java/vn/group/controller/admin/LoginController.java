package vn.group.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ViewResolver;

@Controller
public class LoginController {
    @RequestMapping(value = "/admin/login.html")
    public String getLogin(){
        return "login";
    }
}
