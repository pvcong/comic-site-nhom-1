package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.UserDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.UserService;
import vn.group.web.utils.UserCommanderUtilsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestUserController {

    @Autowired
    UserService userService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping (value = "users", method = RequestMethod.GET)
    public Object[] getUsers(@ModelAttribute UserCommanderUtilsImpl userCommanderUtils){
        //List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        Object[] objects = null;
        try {
            setupSortAndProperty(userCommanderUtils);
          objects =  userService.findByproperties
                  (null,userCommanderUtils.getProperties(),
                          userCommanderUtils.getSortPropertiesMap(), userCommanderUtils.getLimit(),
                          userCommanderUtils.getOffset(),null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
        return objects;
    }

    private void setupSortAndProperty(UserCommanderUtilsImpl userCommanderUtils) {
        if(userCommanderUtils.getObjectDTO() != null){
            Map<String,String> propertis = new HashMap<String, String>();
            UserDTO userDTO = userCommanderUtils.getObjectDTO();
            if(userDTO.getUserId() != null){
                propertis.put("userId",userDTO.getUserId().toString());
            }
            if(userDTO.getFullName() != null){
                propertis.put("fullname",userDTO.getFullName());
            }
            if(userDTO.getUserName() != null){
                propertis.put("username",userDTO.getUserName());
            }
            if(userDTO.getPassword() != null){
                propertis.put("password", userDTO.getPassword());
            }
            if(userDTO.getRole() != null){
                propertis.put("role",userDTO.getRole());
            }
            if(userDTO.getCreatedDate() != null){
                propertis.put("createddate",userDTO.getCreatedDate().toString());
            }
            userCommanderUtils.setProperties(propertis);

        }
        Map<String,String> sortPropertiesMap = new HashMap<String, String>();
        if(userCommanderUtils.getSortProperty()!= null && userCommanderUtils.getSortValue() != null){
            sortPropertiesMap.put(userCommanderUtils.getSortProperty(),userCommanderUtils.getSortValue());
        }
        userCommanderUtils.setSortPropertiesMap(sortPropertiesMap);
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/users/{id}",method = RequestMethod.GET)
    public UserDTO findUser(HttpServletResponse rep, HttpServletRequest req, @PathVariable(name = "id") Integer id){
        UserDTO userDTOS = null;
        try {
             userDTOS = userService.findById(id);
        } catch (HibernateException e) {
            throw new ExecDatabaseException(e.getLocalizedMessage());

        }
        if(userDTOS != null && userDTOS.getUserId() != null)
        return userDTOS;
        else throw new NotFoundObjectException("ID " +id);
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/login",method = RequestMethod.POST)
    public Object[] login(HttpServletResponse rep,
                          HttpServletRequest req,
                          @RequestBody UserDTO userDTO, HttpSession httpSession){
        Object[] objects = null;
        try {
            objects = userService.checkLogin(userDTO.getUserName(),userDTO.getPassword());
        } catch (HibernateException e) {
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
        if((Boolean)objects[0] == true){
            httpSession.setAttribute("userId",objects[2]);
            return objects;
        }

        else throw new NotFoundObjectException("username:" + userDTO.getUserName() + " and password:" +userDTO.getPassword());
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/login/session",method = RequestMethod.GET)
    public Object getLoginSession(HttpSession session){
        if(session.getAttribute("userId") != null){
             return  session.getAttribute("userId");
         }
         else throw new NotFoundObjectException("session");
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(HttpSession session){
         session.removeAttribute("userId");
    }
//    @RequestMapping(value = "/user?{property}={value}", method = RequestMethod.GET)
//    public UserDTO findByPropertyUnique(
//            HttpServletRequest req, HttpServletResponse res,
//            @PathVariable(name = "property") String property, @PathVariable( name = "value") String propertyValue){
//        UserDTO userDTOS = null;
//        try {
//            userDTOS = userService.findByPropertyUnique(property,propertyValue);
//        } catch (HibernateException e) {
//
//        }
//        return userDTOS;
//    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDTO userDTO){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            userDTO.setCreatedDate(timestamp);
            Object checkExist =userService.checkExits(userDTO.getUserName());
            userService.save(userDTO);

        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
        //return "complet";

    }
    @RequestMapping(value = "users", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.CREATED)
   public void updateUser(@RequestBody UserDTO userDTO){
        try {
            userService.update(userDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "users", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody List<UserDTO> userDTOList){
        try {
            userService.delete(userDTOList);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }

}
