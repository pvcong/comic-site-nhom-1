package vn.group.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.group.dal.UserDAL;
import vn.group.dal.UserDALImpl;
import vn.group.data.UserEntity;
import vn.group.dto.UserDTO;
import vn.group.utils.UserUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAL userDAL;

    public List<UserDTO> findAll() throws HibernateException {
        List<UserEntity> userEntities = userDAL.findAll();
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(UserEntity item : userEntities){
            userDTOS.add(UserUtils.entity2DTO(item));
        }
        return userDTOS;
    }

    public void save(UserDTO userDTO) throws HibernateException {
        if(userDTO != null){
            userDAL.save(UserUtils.DTO2Entity(userDTO));
        }
    }

    public UserDTO update(UserDTO userDTO) throws HibernateException {
        UserDTO result = null;
        if(userDTO != null){
           UserEntity userEntity = userDAL.update(UserUtils.DTO2Entity(userDTO));
           result = UserUtils.entity2DTO(userEntity);
        }
        return result;
    }

    public void delete(List<UserDTO> userDTOS) throws HibernateException {
        if(userDTOS != null){
            List<UserEntity> userEntities = new ArrayList<UserEntity>();
            for(UserDTO item : userDTOS){
                userEntities.add(UserUtils.DTO2Entity(item));
            }
            userDAL.delete(userEntities);
        }
    }

    public UserDTO findById(Integer id) throws HibernateException {
        UserDTO userDTO = null;
        if(id != null){
            UserEntity userEntity = userDAL.findById(id);
            userDTO = UserUtils.entity2DTO(userEntity);

        }
        return userDTO;
    }

    public List<UserDTO> findByproperties(Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException {
        List<UserEntity> userEntities = userDAL.findByProperty(properties, sortProperties, limit, offset, whereClause);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for(UserEntity item : userEntities){
            userDTOS.add(UserUtils.entity2DTO(item));
        }
        return userDTOS;
    }
    public UserDTO findByPropertyUnique(String property, Object propertyValue) throws HibernateException{
        UserDTO userDTO = null;
        if(property != null && propertyValue!=null){
            UserEntity userEntity = userDAL.findByPropertyUnique(property,propertyValue);
            userDTO = UserUtils.entity2DTO(userEntity);
        }
        return userDTO;
    }
}
