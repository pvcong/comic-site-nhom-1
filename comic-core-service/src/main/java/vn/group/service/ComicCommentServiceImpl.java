package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.group.dal.ComicCommentDAL;
import vn.group.data.ComicCommentEntity;
import vn.group.data.ComicEntity;
import vn.group.data.UserEntity;
import vn.group.dto.ComicCommentDTO;
import vn.group.dto.ComicDTO;
import vn.group.dto.UserDTO;
import vn.group.utils.ComicCommentUtils;
import vn.group.utils.ComicUtils;
import vn.group.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicCommentServiceImpl implements ComicCommentService {
    @Autowired
    ComicCommentDAL comicCommentDAL;
    public void save(ComicCommentDTO comicCommentDTO) throws HibernateException {
        if(comicCommentDTO != null){
            ComicCommentEntity comicCommentEntity = ComicCommentUtils.DTO2Entity(comicCommentDTO);
            comicCommentEntity.setUserEntity(UserUtils.DTO2Entity(comicCommentDTO.getUserDTO()));
            comicCommentEntity.setComicEntity(ComicUtils.DTO2Entity(comicCommentDTO.getComicDTO()));
            comicCommentDAL.save(comicCommentEntity);
        }
    }

    public void delete(List<ComicCommentDTO> comicCommentDTOS) throws HibernateException {
        if(comicCommentDTOS != null){
            List<ComicCommentEntity> commentEntityList = new ArrayList<ComicCommentEntity>();
            for(ComicCommentDTO item : comicCommentDTOS){
                commentEntityList.add(ComicCommentUtils.DTO2Entity(item));
            }
            comicCommentDAL.delete(commentEntityList);
        }
    }

    public ComicCommentDTO update(ComicCommentDTO comicCommentDTO) throws HibernateException  {

        if(comicCommentDTO != null){
            ComicCommentEntity comicCommentEntity = ComicCommentUtils.DTO2Entity(comicCommentDTO);
            comicCommentEntity.setUserEntity(UserUtils.DTO2Entity(comicCommentDTO.getUserDTO()));
            comicCommentEntity.setComicEntity(ComicUtils.DTO2Entity(comicCommentDTO.getComicDTO()));
            comicCommentDTO = ComicCommentUtils.entity2DTO(comicCommentDAL.update(comicCommentEntity));
        }
        return comicCommentDTO;
    }

    public Object[] findByProperties(List<String> joinTables,Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String where) throws HibernateException{
       Object objects[] = comicCommentDAL.findByProperty(joinTables,properties, sortProperties, limit, offset, where);
        List<ComicCommentEntity> commentEntityList = (List<ComicCommentEntity>) objects[1];
       List<ComicCommentDTO> comicCommentDTOS = new ArrayList<ComicCommentDTO>();
        for(ComicCommentEntity comicCommentEntity : commentEntityList){
            UserDTO userDTO = UserUtils.entity2DTO(comicCommentEntity.getUserEntity());
            //ComicDTO comicDTO = ComicUtils.entity2DTO(comicCommentEntity.getComicEntity());
            ComicCommentDTO comicCommentDTO = ComicCommentUtils.entity2DTO(comicCommentEntity);
            comicCommentDTO.setUserDTO(userDTO);
            //comicCommentDTO.setComicDTO(comicDTO);
            comicCommentDTOS.add(comicCommentDTO);
        }
        return new Object[]{objects[0],comicCommentDTOS};
    }

    public ComicCommentDTO findByPropertiesUnique(String property, String value) throws HibernateException{
        ComicCommentDTO comicCommentDTO = null;
        if(property != null && value != null){
            ComicCommentEntity comicCommentEntity =comicCommentDAL.findByPropertyUnique(property,value);
             UserEntity userEntity = comicCommentEntity.getUserEntity();
             UserDTO userDTO = UserUtils.entity2DTO(userEntity);
             ComicDTO comicDTO = ComicUtils.entity2DTO(comicCommentEntity.getComicEntity());
             comicCommentDTO = ComicCommentUtils.entity2DTO(comicCommentEntity);
             comicCommentDTO.setUserDTO(userDTO);
             comicCommentDTO.setComicDTO(comicDTO);
        }
        return comicCommentDTO;
    }

    public ComicCommentDTO findById(Integer id) throws HibernateException {
        ComicCommentDTO comicCommentDTO = null;
        if(id != null){
            ComicCommentEntity comicCommentEntity = comicCommentDAL.findDetailComicComment(id);
            comicCommentDTO = ComicCommentUtils.entity2DTO(comicCommentEntity);
            if(comicCommentEntity != null){
                ComicDTO comicDTO = new ComicDTO();
                if(UserUtils.entity2DTO(comicCommentEntity.getUserEntity()) != null)
                    comicDTO.setComicId(UserUtils.entity2DTO(comicCommentEntity.getUserEntity()).getUserId());
                UserDTO userDTO = new UserDTO();
                if(ComicUtils.entity2DTO(comicCommentEntity.getComicEntity()) != null)
                    userDTO.setUserId(ComicUtils.entity2DTO(comicCommentEntity.getComicEntity()).getComicId());
                comicCommentDTO.setUserDTO(userDTO);
                comicCommentDTO.setComicDTO(comicDTO);
            }
        }


        return  comicCommentDTO;
    }
}
