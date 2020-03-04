package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.group.dal.ComicDAL;
import vn.group.dal.WeekdaysDAL;
import vn.group.data.ComicEntity;
import vn.group.data.ComicGenresEntity;
import vn.group.data.WeekdaysEntity;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;
import vn.group.dto.WeekdaysDTO;
import vn.group.utils.ComicGenresUtils;
import vn.group.utils.ComicUtils;
import vn.group.utils.WeekdaysUtils;

import java.util.*;

@Service
public class WeekdaysServiceImpl implements WeekdaysService {
    @Autowired
    WeekdaysDAL weekdaysDAL;
    @Autowired
    ComicDAL comicDAL;
    public List<WeekdaysDTO> findAll() throws HibernateException {
        return null;
    }

    public void save(WeekdaysDTO weekdaysDTO) throws HibernateException {
        if(weekdaysDTO != null){
            weekdaysDAL.save(WeekdaysUtils.dto2Entity(weekdaysDTO));
        }
    }

    public WeekdaysDTO update(WeekdaysDTO weekdaysDTO) throws HibernateException {
        if(weekdaysDTO != null){
            WeekdaysEntity weekdaysEntity = weekdaysDAL.update(WeekdaysUtils.dto2Entity(weekdaysDTO));
        }
        return null;
    }

    public void delete(List<WeekdaysDTO> weekdaysDTOS) throws HibernateException {
        if(weekdaysDTOS != null){
            List<WeekdaysEntity> weekdaysEntities = new ArrayList<WeekdaysEntity>();
            for(WeekdaysDTO item : weekdaysDTOS){
                weekdaysEntities.add(WeekdaysUtils.dto2Entity(item));
            }
            weekdaysDAL.delete(weekdaysEntities);
        }
    }

    public WeekdaysDTO findById(Integer id) throws HibernateException {
        WeekdaysDTO weekdaysDTO = null;
        if(id != null){
            weekdaysDTO = WeekdaysUtils.entity2DTO(weekdaysDAL.findById(id));
        }
        return weekdaysDTO;
    }

    public Object[] findByproperties(List<String> joinTables, Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException{
       Object[] objects = weekdaysDAL.findByProperty(joinTables, properties, sortProperties, limit, offset, whereClause);
       List<WeekdaysEntity> weekdaysEntities = (List<WeekdaysEntity>) objects[1];
       List<WeekdaysDTO> weekdaysDTOS = new ArrayList<WeekdaysDTO>();
       for(WeekdaysEntity item : weekdaysEntities){
           weekdaysDTOS.add(WeekdaysUtils.entity2DTO(item));
       }

        return new Object[]{objects[0],weekdaysDTOS};
    }

    public WeekdaysDTO findByPropertyUnique(String property, Object propertyValue) throws HibernateException {
        return null;
    }

    public Object[] findComicOfWeekdays(List<String> joinTables, Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) {
        Object[] comicResults = comicDAL.findByProperty(joinTables, properties, sortProperties, limit, offset, whereClause);
        List<ComicEntity> comicEntities = (List<ComicEntity>) comicResults[1];
        List<ComicDTO> comicDTOS = new ArrayList<ComicDTO>();
        for(ComicEntity item : comicEntities){
            ComicDTO comicDTO = ComicUtils.entity2DTO(item);
            Set<ComicGenresDTO> comicGenresDTOList = new LinkedHashSet<ComicGenresDTO>();
            for(ComicGenresEntity itemGenres : item.getComicGenresEntities()){
                comicGenresDTOList.add(ComicGenresUtils.entity2DTO(itemGenres));
            }
            comicDTO.setComicGenresEntities(comicGenresDTOList);
            comicDTOS.add(comicDTO);
        }
        Integer weekdaysId = null;
        for(Map.Entry<String,String> item : properties.entrySet()){
            weekdaysId = Integer.parseInt(item.getValue());
        }
        WeekdaysEntity weekdaysEntity = weekdaysDAL.findById(weekdaysId);
        WeekdaysDTO weekdaysDTO = WeekdaysUtils.entity2DTO(weekdaysEntity);
        weekdaysDTO.setComicDTOList(comicDTOS);
        return new Object[]{comicResults[0],weekdaysDTO};
    }
}
