package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.UserDTO;
import vn.group.service.UserService;
import vn.learn.web.utils.GenericCommanderUtilsImpl;
import vn.learn.web.utils.UserCommanderUtils;
import vn.learn.web.utils.UserCommanderUtilsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestUserController {

    @Autowired
    UserService userService;
    @RequestMapping (value = "user", method = RequestMethod.GET)
    public List<UserDTO> getUsers(@ModelAttribute UserCommanderUtilsImpl userCommanderUtils){
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        try {
            setupSortAndProperty(userCommanderUtils);
          userDTOList =  userService.findByproperties
                  (userCommanderUtils.getProperties(),
                          userCommanderUtils.getSortProperties(), userCommanderUtils.getLimit(),
                          userCommanderUtils.getOffset(),null);
        } catch (HibernateException e){

        }
        return userDTOList;
    }

    private void setupSortAndProperty(UserCommanderUtilsImpl userCommanderUtils) {
        if(userCommanderUtils.getObjectDTO() != null){
            //Map<String,String> propertis = new HashMap<String, String>()
        }
    }

    @RequestMapping( value = "/user/{id}",method = RequestMethod.GET)
    public UserDTO findUser(HttpServletResponse rep, HttpServletRequest req, @PathVariable(name = "id") Integer id){
        UserDTO userDTOS = null;
        try {
             userDTOS = userService.findById(id);
        } catch (HibernateException e) {

        }
        return userDTOS;
    }
    @RequestMapping(value = "/user?{property}={value}", method = RequestMethod.GET)
    public UserDTO findByPropertyUnique(
            HttpServletRequest req, HttpServletResponse res,
            @PathVariable(name = "property") String property, @PathVariable( name = "value") String propertyValue){
        UserDTO userDTOS = null;
        try {
            userDTOS = userService.findByPropertyUnique(property,propertyValue);
        } catch (HibernateException e) {

        }
        return userDTOS;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public String saveUser(@RequestBody UserDTO userDTO){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            userDTO.setCreatedDate(timestamp);
            userService.save(userDTO);

        } catch (HibernateException e){
            return "error";
        }
        return "complet";

    }
    @RequestMapping(value = "user", method = RequestMethod.PUT)
   public void updateUser(@RequestBody UserDTO userDTO){
        try {

            userService.update(userDTO);
        } catch (HibernateException e){

        }
    }
    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody List<UserDTO> userDTOList){
        try {
            userService.delete(userDTOList);
        } catch (HibernateException e){

        }
    }

}
