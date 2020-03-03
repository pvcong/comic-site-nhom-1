package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.group.dal.ComicDAL;
import vn.group.dal.ComicGenresDAL;
import vn.group.data.ComicEntity;
import vn.group.data.ComicGenresEntity;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;
import vn.group.dto.UserDTO;
import vn.group.utils.ComicGenresUtils;
import vn.group.utils.ComicUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicGenresServiceImpl implements ComicGenresService {
    @Autowired
    ComicGenresDAL comicGenresDAL;
    @Autowired
    ComicDAL comicDAL;
    public List<ComicGenresDTO> findAll() throws HibernateException {
        List<ComicGenresDTO> comicGenresDTOList = new ArrayList<ComicGenresDTO>();
        List<ComicGenresEntity> comicGenresEntityList = comicGenresDAL.findAll();
        for (ComicGenresEntity item : comicGenresEntityList){
            ComicGenresDTO comicGenresDTO = ComicGenresUtils.entity2DTO(item);
            comicGenresDTOList.add(comicGenresDTO);
        }
        return comicGenresDTOList;
    }

    public void save(ComicGenresDTO comicGenresDTO) throws HibernateException {
        if(comicGenresDTO != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicGenresDTO.setCreatedDate(timestamp);
            comicGenresDTO.setModifiedDate(timestamp);
            ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(comicGenresDTO);
            comicGenresDAL.save(comicGenresEntity);
        }
    }

    public ComicGenresDTO update(ComicGenresDTO comicGenresDTO) throws HibernateException {
        ComicGenresDTO result = null;
        if(comicGenresDTO != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicGenresDTO.setModifiedDate(timestamp);
            ComicGenresEntity comicGenresEntity = comicGenresDAL.update(ComicGenresUtils.DTO2Entity(comicGenresDTO));
            result = ComicGenresUtils.entity2DTO(comicGenresEntity);
        }
        return result;
    }

    public void delete(List<ComicGenresDTO> comicGenresDTOS) throws HibernateException{
        if(comicGenresDTOS != null){
            List<ComicGenresEntity> comicGenresEntityList = new ArrayList<ComicGenresEntity>();
            for(ComicGenresDTO item : comicGenresDTOS){
                ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(item);
                comicGenresEntityList.add(comicGenresEntity);
            }
            comicGenresDAL.delete(comicGenresEntityList);
        }
    }

    public ComicGenresDTO findById(Integer id) throws HibernateException {
        ComicGenresDTO comicGenresDTO = null;
        if(id != null){
            ComicGenresEntity comicGenresEntity = comicGenresDAL.findById(id);
            comicGenresDTO = ComicGenresUtils.entity2DTO(comicGenresEntity);
        }
        return comicGenresDTO;
    }

    public Object[] findByproperties(List<String> joinTables,Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException{
        List<ComicGenresDTO> comicGenresDTOList = new ArrayList<ComicGenresDTO>();
        Object[] objects = comicGenresDAL.findByProperty(joinTables,properties, sortProperties, limit, offset, whereClause);
        List<ComicGenresEntity> comicGenresEntityList = (List<ComicGenresEntity>) objects[1];
        for(ComicGenresEntity item : comicGenresEntityList){
            ComicGenresDTO comicGenresDTO = ComicGenresUtils.entity2DTO(item);
            comicGenresDTOList.add(comicGenresDTO);
        }
        return new Object[]{objects[0],comicGenresDTOList};
    }

    public ComicGenresDTO findByPropertyUnique(String property, Object propertyValue) throws HibernateException {
        ComicGenresDTO comicGenresDTO = null;
        if(!StringUtils.isEmpty(property) && !StringUtils.isEmpty(propertyValue)){
           comicGenresDTO = ComicGenresUtils.entity2DTO(comicGenresDAL.findByPropertyUnique(property,propertyValue));
        }
        return comicGenresDTO;
    }

    public Object[] findComicsOfGenres(List<String> joinTables, Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) {
        ComicGenresDTO comicGenresDTO = null;
        Object[] objects = comicDAL.findByProperty(joinTables,properties, sortProperties, limit, offset, whereClause);
        Integer genresId = null;
        for(Map.Entry<String,String> item :  properties.entrySet()){
            genresId = Integer.parseInt(item.getValue());
        }
        ComicGenresEntity comicGenresEntity = comicGenresDAL.findById(genresId);
        List<ComicEntity> comicEntities = (List<ComicEntity>) objects[1];
        List<ComicDTO> comicDTOS = new ArrayList<ComicDTO>();
        for(ComicEntity item : comicEntities){
                ComicDTO comicDTO = ComicUtils.entity2DTO(item);
                comicDTOS.add(comicDTO);
            }
        comicGenresDTO = ComicGenresUtils.entity2DTO(comicGenresEntity);
        comicGenresDTO.setComicEntities(comicDTOS);


        return new Object[]{objects[0],comicGenresDTO};
    }
}
