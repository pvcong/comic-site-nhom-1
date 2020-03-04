package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.group.dal.ComicReviewDAL;
import vn.group.data.ComicReviewEntity;
import vn.group.data.UserEntity;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicReviewDTO;
import vn.group.dto.UserDTO;
import vn.group.utils.ComicReviewUtils;
import vn.group.utils.ComicUtils;
import vn.group.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicReviewServiceImpl implements ComicReviewService {
    @Autowired
    ComicReviewDAL comicReviewDAL;
    public void save(ComicReviewDTO comicReviewDTO) throws HibernateException {
        if(comicReviewDTO != null){
            ComicReviewEntity comicReviewEntity = ComicReviewUtils.DTO2Entity(comicReviewDTO);
            comicReviewEntity.setComicEntity(ComicUtils.DTO2Entity(comicReviewDTO.getComicDTO()));
            comicReviewEntity.setUserEntity(UserUtils.DTO2Entity(comicReviewDTO.getUserDTO()));
            comicReviewDAL.save(comicReviewEntity);
        }
    }

    public void delete(List<ComicReviewDTO> comicReviewDTOS) throws HibernateException {
        List<ComicReviewEntity> comicReviewEntities = new ArrayList<ComicReviewEntity>();
        if(comicReviewDTOS != null){
            for(ComicReviewDTO item : comicReviewDTOS){
                comicReviewEntities.add(ComicReviewUtils.DTO2Entity(item));
            }
            comicReviewDAL.delete(comicReviewEntities);
        }
    }

    public ComicReviewDTO update(ComicReviewDTO comicReviewDTO) throws HibernateException {
        if(comicReviewDTO != null){
            ComicReviewEntity comicReviewEntity = ComicReviewUtils.DTO2Entity(comicReviewDTO);
            comicReviewEntity.setComicEntity(ComicUtils.DTO2Entity(comicReviewDTO.getComicDTO()));
            comicReviewEntity.setUserEntity(UserUtils.DTO2Entity(comicReviewDTO.getUserDTO()));
            comicReviewDTO = ComicReviewUtils.entity2DTO(comicReviewDAL.update(comicReviewEntity));
        }
        return comicReviewDTO;
    }

    public Object[] findByProperties(List<String> joinTables,Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String where) throws HibernateException {
        Object[] objects = comicReviewDAL.findByProperty(joinTables,properties, sortProperties, limit, offset, where);
        List<ComicReviewEntity> comicReviewEntities = (List<ComicReviewEntity>) objects[1];
         List<ComicReviewDTO> comicReviewDTOS = new ArrayList<ComicReviewDTO>();
        for(ComicReviewEntity item : comicReviewEntities){
            comicReviewDTOS.add(ComicReviewUtils.entity2DTO(item));
        }
        return new Object[]{objects[0],comicReviewDTOS};
    }

    public ComicReviewDTO findByPropertiesUnique(String property, String value) throws  HibernateException{
        ComicReviewDTO comicReviewDTO = ComicReviewUtils.entity2DTO(comicReviewDAL.findByPropertyUnique(property,value));
        return comicReviewDTO;
    }

    public ComicReviewDTO findByid(Integer id) throws HibernateException {
        ComicReviewDTO comicReviewDTO = null;
        if(id != null){
            ComicReviewEntity comicReviewEntity = comicReviewDAL.findDetailComicReview(id);
            comicReviewDTO = ComicReviewUtils.entity2DTO(comicReviewEntity);
            if(comicReviewEntity != null){
                ComicDTO comicDTO = new ComicDTO();
                if(ComicUtils.entity2DTO(comicReviewEntity.getComicEntity()) != null)
                    comicDTO.setComicId(ComicUtils.entity2DTO(comicReviewEntity.getComicEntity()).getComicId());
                UserDTO userDTO = new UserDTO();
                if(UserUtils.entity2DTO(comicReviewEntity.getUserEntity()) != null)
                    userDTO.setUserId(UserUtils.entity2DTO(comicReviewEntity.getUserEntity()).getUserId());
                comicReviewDTO.setComicDTO(comicDTO);
                comicReviewDTO.setUserDTO(userDTO);
            }

        }
        return  comicReviewDTO;
    }

}
