package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.group.dal.ComicGenresDAL;
import vn.group.data.ComicGenresEntity;
import vn.group.dto.ComicGenresDTO;
import vn.group.utils.ComicGenresUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicGenresServiceImpl implements ComicGenresService {
    @Autowired
    ComicGenresDAL comicGenresDAL;
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
            ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(comicGenresDTO);
            comicGenresDAL.save(comicGenresEntity);
        }
    }

    public ComicGenresDTO update(ComicGenresDTO comicGenresDTO) throws HibernateException {
        ComicGenresDTO result = null;
        if(comicGenresDTO != null){
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
            comicGenresDTO = ComicGenresUtils.entity2DTO(comicGenresDAL.findById(id));
        }
        return comicGenresDTO;
    }

    public List<ComicGenresDTO> findByproperties(Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException{
        List<ComicGenresDTO> comicGenresDTOList = new ArrayList<ComicGenresDTO>();
        List<ComicGenresEntity> comicGenresEntityList = comicGenresDAL.findByProperty(properties, sortProperties, limit, offset, whereClause);
        for(ComicGenresEntity item : comicGenresEntityList){
            ComicGenresDTO comicGenresDTO = ComicGenresUtils.entity2DTO(item);
            comicGenresDTOList.add(comicGenresDTO);
        }
        return comicGenresDTOList;
    }

    public ComicGenresDTO findByPropertyUnique(String property, Object propertyValue) throws HibernateException {
        ComicGenresDTO comicGenresDTO = null;
        if(!StringUtils.isEmpty(property) && !StringUtils.isEmpty(propertyValue)){
           comicGenresDTO = ComicGenresUtils.entity2DTO(comicGenresDAL.findByPropertyUnique(property,propertyValue));
        }
        return comicGenresDTO;
    }
}
