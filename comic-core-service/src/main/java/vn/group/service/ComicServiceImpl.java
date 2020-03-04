package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.group.dal.ComicChapterDAL;
import vn.group.dal.ComicCommentDAL;
import vn.group.dal.ComicDAL;
import vn.group.dal.ComicReviewDAL;
import vn.group.data.*;
import vn.group.dto.*;
import vn.group.utils.*;

import java.util.*;

@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicDAL comicDAL;
    @Autowired
    ComicChapterDAL comicChapterDAL;
    @Autowired
    ComicCommentDAL comicCommentDAL;
    @Autowired
    ComicReviewDAL comicReviewDAL;
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
                ComicEntity comicEntity = ComicUtils.DTO2Entity(comicDTO);
                Set< ComicGenresEntity > comicGenresEntities = new LinkedHashSet<ComicGenresEntity>();
                for(ComicGenresDTO item : comicDTO.getComicGenresEntities() ){
                    ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(item);
                    comicGenresEntities.add(comicGenresEntity);
                }
                List<WeekdaysEntity> weekdaysEntities = new ArrayList<WeekdaysEntity>();
                for(WeekdaysDTO item : comicDTO.getWeekdaysEntities() ){
                    WeekdaysEntity weekdaysEntity = WeekdaysUtils.dto2Entity(item);
                    weekdaysEntities.add(weekdaysEntity);
                }
                UserEntity userEntity = UserUtils.DTO2Entity(comicDTO.getUserDTO());
                comicEntity.setUserEntity(userEntity);
                comicEntity.setWeekdaysEntities(weekdaysEntities);
                comicEntity.setComicGenresEntities(comicGenresEntities);
                comicDAL.save(comicEntity);
            }

        }


    public ComicDTO update(ComicDTO comicDTO) throws HibernateException {
        if(comicDTO != null){
                Set<ComicGenresEntity> comicGenresEntities = new LinkedHashSet<ComicGenresEntity>();
                for(ComicGenresDTO item : comicDTO.getComicGenresEntities()){
                    ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(item);
                    comicGenresEntities.add(comicGenresEntity);
                }
                List<WeekdaysEntity> weekdaysEntities = new ArrayList<WeekdaysEntity>();
                for(WeekdaysDTO item : comicDTO.getWeekdaysEntities()){
                    WeekdaysEntity weekdaysEntity = WeekdaysUtils.dto2Entity(item);
                    weekdaysEntities.add(weekdaysEntity);
                }
                UserEntity userEntity = UserUtils.DTO2Entity(comicDTO.getUserDTO());
                ComicEntity comicEntity = ComicUtils.DTO2Entity(comicDTO);
                comicEntity.setUserEntity(userEntity);
                comicEntity.setWeekdaysEntities(weekdaysEntities);
                comicEntity.setComicGenresEntities(comicGenresEntities);

                comicDAL.update(comicEntity);
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

    public Object[] findByproperties(List<String> joinTables,Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException {
        List<ComicDTO> comicDTOS = new ArrayList<ComicDTO>();
         Object[] objects = comicDAL.findByProperty(joinTables,properties,sortProperties,limit,offset,whereClause);
        List<ComicEntity> comicEntities = (List<ComicEntity>) objects[1];
         for(ComicEntity item : comicEntities){
            Set<ComicGenresEntity> comicGenresEntity = item.getComicGenresEntities();
            Set<ComicGenresDTO> comicGenresDTOS = new LinkedHashSet<ComicGenresDTO>();
            for(ComicGenresEntity genresEntity : comicGenresEntity){
                comicGenresDTOS.add(ComicGenresUtils.entity2DTO(genresEntity));
            }
//             List<WeekdaysDTO> weekdaysDTOS = new ArrayList<WeekdaysDTO>();
//             List<WeekdaysEntity> weekdaysEntities = item.getWeekdaysEntities();
//             for(WeekdaysEntity weekdaysEntity : weekdaysEntities ){
//                 WeekdaysDTO weekdaysDTO = WeekdaysUtils.entity2DTO(weekdaysEntity);
//                 weekdaysDTOS.add(weekdaysDTO);
//             }
            //UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());
            ComicDTO comicDTO = ComicUtils.entity2DTO(item);
            comicDTO.setComicGenresEntities(comicGenresDTOS);
            //comicDTO.setUserDTO(userDTO);
           // comicDTO.setWeekdaysEntities(weekdaysDTOS);
            comicDTOS.add(comicDTO);
        }
        return new Object[]{objects[0],comicDTOS};
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
            ComicEntity comicEntity = comicDAL.findDetailComicUnique(id);
            if(comicEntity != null){

                Set<ComicGenresEntity> comicGenresEntities = comicEntity.getComicGenresEntities();
                Set<ComicGenresDTO> comicGenresDTOS = new LinkedHashSet<ComicGenresDTO>();
                List<WeekdaysEntity> weekdaysEntities = comicEntity.getWeekdaysEntities();
                List<WeekdaysDTO> weekdaysDTOS = new ArrayList<WeekdaysDTO>();
                for(WeekdaysEntity item : weekdaysEntities){
                    weekdaysDTOS.add(WeekdaysUtils.entity2DTO(item));
                }
                for(ComicGenresEntity item : comicGenresEntities){
                    ComicGenresDTO comicGenresDTO = ComicGenresUtils.entity2DTO(item);
                    comicGenresDTOS.add(comicGenresDTO);
                }
                UserDTO userDTO = UserUtils.entity2DTO(comicEntity.getUserEntity());
                comicDTO = ComicUtils.entity2DTO(comicDAL.findDetailComicUnique(id));
                comicDTO.setUserDTO(userDTO);
                comicDTO.setWeekdaysEntities(weekdaysDTOS);
                comicDTO.setComicGenresEntities(comicGenresDTOS);
            }
        }
        return comicDTO;
    }

    public Object[] findComicChapterOfComic(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause) {
        Object[] object = null;
        Object[] resultChapter = comicChapterDAL.findByProperty(joinTables, properties, sortProperties, limit, offset, whereClause);
        Integer idComic = null;
        for(Map.Entry<String,String> item : properties.entrySet()){
            idComic = Integer.parseInt(item.getValue());
        }
        ComicEntity resultComic = comicDAL.findById(idComic);
        List<ComicChapterEntity> comicChapterEntities = (List<ComicChapterEntity>) resultChapter[1];
        Set<ComicChapterDTO> comicChapterDTOS = new LinkedHashSet<ComicChapterDTO>();
        for(ComicChapterEntity item : comicChapterEntities ){
            comicChapterDTOS.add(ComicChapterUtils.entity2DTO(item));
        }
        ComicDTO comicDTO = ComicUtils.entity2DTO(resultComic);
        comicDTO.setComicChapterEntities(comicChapterDTOS);
        object = new Object[]{resultChapter[0],comicDTO};

        return object;

    }

    public Object[] findCommentsOfComic(List<String> joinTable, Map<String, String> propertiesMap, Map<String, String> sortPropertiesMap, Integer limit, int offset, String whereClause) {
        Object[] object = null;
        Object[] resultChapter = comicCommentDAL.findByProperty(joinTable, propertiesMap, sortPropertiesMap, limit, offset, null);
        Integer idComic = null;
        for(Map.Entry<String,String> item : propertiesMap.entrySet()){
            idComic = Integer.parseInt(item.getValue());
        }
        ComicEntity resultComic = comicDAL.findById(idComic);
        List<ComicCommentEntity> comicCommentEntities = (List<ComicCommentEntity>) resultChapter[1];
        List<ComicCommentDTO> comicCommentDTOS = new ArrayList<ComicCommentDTO>();
        for(ComicCommentEntity item : comicCommentEntities ){
            UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());
            ComicCommentDTO comicCommentDTO = ComicCommentUtils.entity2DTO(item);
            comicCommentDTO.setUserDTO(userDTO);
            comicCommentDTOS.add(comicCommentDTO);
        }
        ComicDTO comicDTO = ComicUtils.entity2DTO(resultComic);
        comicDTO.setComicCommentEntities(comicCommentDTOS);
        object = new Object[]{resultChapter[0],comicDTO};

        return object;
    }

    public Object[] findReviewOfComic(List<String> joinTable, Map<String, String> propertiesMap, Map<String, String> sortPropertiesMap, Integer limit, int offset, String whereClause) {
        Object[] object = null;
        Object[] resultChapter = comicReviewDAL.findByProperty(joinTable, propertiesMap, sortPropertiesMap, limit, offset, null);
        Integer idComic = null;
        for(Map.Entry<String,String> item : propertiesMap.entrySet()){
            idComic = Integer.parseInt(item.getValue());
        }
        ComicEntity resultComic = comicDAL.findById(idComic);
        List<ComicReviewEntity> comicReviewEntities = (List<ComicReviewEntity>) resultChapter[1];
        List<ComicReviewDTO> comicReviewDTOS = new ArrayList<ComicReviewDTO>();
        for(ComicReviewEntity item : comicReviewEntities ){
            comicReviewDTOS.add(ComicReviewUtils.entity2DTO(item));
        }
        ComicDTO comicDTO = ComicUtils.entity2DTO(resultComic);
        comicDTO.setComicReviewEntities(comicReviewDTOS);
        object = new Object[]{resultChapter[0],comicDTO};

        return object;

    }
}
