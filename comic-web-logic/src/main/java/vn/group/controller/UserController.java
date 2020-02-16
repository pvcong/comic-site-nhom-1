package vn.group.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.group.dto.UserDTO;
import vn.group.service.UserService;
import vn.group.service.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController

public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/")
    public ModelAndView sayHello(HttpServletRequest req, HttpServletResponse response, HttpSession session){
       Map<String,String> mapProperties = new HashMap<String,String>();
        Map<String,String> mapSort = new HashMap<String,String>();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("admin");
        try {
            userService.findById(1);
        } catch (HibernateException e){

        }

       //mapProperties.put("userid","1");
       // userService.findByproperties(mapProperties,mapSort,null,null,null);

        return new ModelAndView("index");
    }
}
