package vn.group.service;

import vn.group.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> findAll() ;
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete(List<UserDTO> userDTOS);
    UserDTO findById(Integer id);
    Object[] findByproperties(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
     UserDTO findByPropertyUnique(String property, Object propertyValue);
    Object[] checkLogin(String username, String password);
    Object checkExits(String username);
}
