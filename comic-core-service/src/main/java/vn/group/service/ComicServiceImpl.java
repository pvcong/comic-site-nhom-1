package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import vn.group.dal.ComicChapterDAL;
import vn.group.dal.ComicDAL;
import vn.group.data.ComicEntity;
import vn.group.dto.ComicDTO;
import vn.group.utils.ComicUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicDAL comicDAL;
    @Autowired
    ComicChapterDAL comicChapterDAL;

    public List<ComicDTO> findAll() throws HibernateException {
        List<ComicDTO> comicDTOS = new ArrayList<ComicDTO>();
        List<ComicEntity> comicEntities = comicDAL.findAll();
        for(ComicEntity comicEntity : comicEntities){
            ComicDTO comicDTO = ComicUtils.entity2DTO(comicEntity);
            comicDTOS.add(comicDTO);
        }
        return comicDTOS;
    }

    public void save(ComicDTO comicDTO) throws HibernateException {
        if(comicDTO != null){
            comicDAL.save(ComicUtils.DTO2Entity(comicDTO));
        }
    }

    public ComicDTO update(ComicDTO comicDTO) throws HibernateException {
        if(comicDTO != null){
            comicDAL.update(ComicUtils.DTO2Entity(comicDTO));
        }
        return comicDTO;
    }

    public void delete(List<ComicDTO> comicDTOS) throws HibernateException {
        if(comicDTOS != null){
            List<ComicEntity> comicEntities = new ArrayList<ComicEntity>();
            for(ComicDTO item : comicDTOS){
                ComicEntity comicEntity = ComicUtils.DTO2Entity(item);
                comicEntities.add(comicEntity);
            }
            comicDAL.delete(comicEntities);
        }
    }

    public ComicDTO findById(Integer id) {

        return null;
    }

    public List<ComicDTO> findByproperties(Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException {
        List<ComicDTO> comicDTOS = new ArrayList<ComicDTO>();
        List<ComicEntity> comicEntities = comicDAL.findByProperty(properties,sortProperties,limit,offset,whereClause);
        for(ComicEntity item : comicEntities){
            ComicDTO comicDTO = ComicUtils.entity2DTO(item);
            comicDTOS.add(comicDTO);
        }
        return comicDTOS;
    }

    public ComicDTO findByPropertyUnique(String property, Object propertyValue) throws HibernateException{
        ComicDTO comicDTO = null;
        if(!StringUtils.isEmpty(property) && StringUtils.isEmpty(propertyValue)){
            comicDTO = ComicUtils.entity2DTO(comicDAL.findByPropertyUnique(property,propertyValue));
        }
        return comicDTO;
    }

    public ComicDTO findDetaiComicUntique(Integer id) throws HibernateException{
        ComicDTO comicDTO = null;
        if(id != null){
            comicDTO = ComicUtils.entity2DTO(comicDAL.findDetailComicUnique(id));
        }
        return comicDTO;
    }
}
