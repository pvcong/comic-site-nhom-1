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
    List<UserDTO> findByproperties(Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
     UserDTO findByPropertyUnique(String property, Object propertyValue);
}
