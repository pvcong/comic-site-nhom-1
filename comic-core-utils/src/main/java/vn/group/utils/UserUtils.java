package vn.group.utils;

import vn.group.data.UserEntity;
import vn.group.dto.UserDTO;

public class UserUtils {

    public static UserDTO entity2DTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        if(userEntity != null){
            userDTO.setUserId(userEntity.getUserId());
            userDTO.setUserName(userEntity.getUserName());
            userDTO.setFullName(userEntity.getFullName());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setRole(userEntity.getRole());
            userDTO.setCreatedDate(userEntity.getCreatedDate());

        }
        return userDTO;
    }

    public static UserEntity DTO2Entity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        if(userDTO != null){
            userEntity.setUserId(userDTO.getUserId());
            userEntity.setUserName(userDTO.getUserName());
            userEntity.setFullName(userDTO.getFullName());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setRole(userDTO.getRole());
            userEntity.setCreatedDate(userDTO.getCreatedDate());

        }
        return userEntity;
    }
}
