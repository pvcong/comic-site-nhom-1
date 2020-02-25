package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.group.common.ServiceConstant;
import vn.group.dal.ComicChapterDAL;
import vn.group.dal.ComicDAL;
import vn.group.data.ComicEntity;
import vn.group.data.ComicGenresEntity;
import vn.group.data.UserEntity;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;
import vn.group.utils.ComicGenresUtils;
import vn.group.utils.ComicUtils;
import vn.group.utils.UploadUtils;
import vn.group.utils.UserUtils;

import java.util.*;

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

    public void save(ComicDTO comicDTO, MultipartFile[] multipartFile, String path) throws HibernateException {
        if(comicDTO != null){
            Object[] objects = UploadUtils.uploadFile(multipartFile, ServiceConstant.locationComicBanner,path);
            if((Boolean)objects[0] == false ){
                comicDTO.setBanner(objects[2].toString());
                ComicEntity comicEntity = ComicUtils.DTO2Entity(comicDTO);
                Set< ComicGenresEntity > comicGenresEntities = new LinkedHashSet<ComicGenresEntity>();
                for(ComicGenresDTO item : comicDTO.getComicGenresEntities() ){
                    ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(item);
                    comicGenresEntities.add(comicGenresEntity);
                }
                UserEntity userEntity = UserUtils.DTO2Entity(comicDTO.getUserDTO());
                comicEntity.setUserEntity(userEntity);
                comicEntity.setComicGenresEntities(comicGenresEntities);
                comicDAL.save(comicEntity);
            }

        }
    }

    public ComicDTO update(ComicDTO comicDTO, MultipartFile[] multipartFile,String path) throws HibernateException {
        if(comicDTO != null){ Object[] objects = UploadUtils.uploadFile(multipartFile, ServiceConstant.locationComicBanner,path);
            if((Boolean)objects[0] == false){
                comicDTO.setBanner(objects[2].toString());
                Set<ComicGenresEntity> comicGenresEntities = new LinkedHashSet<ComicGenresEntity>();
                for(ComicGenresDTO item : comicDTO.getComicGenresEntities()){
                    ComicGenresEntity comicGenresEntity = ComicGenresUtils.DTO2Entity(item);
                    comicGenresEntities.add(comicGenresEntity);
                }
                UserEntity userEntity = UserUtils.DTO2Entity(comicDTO.getUserDTO());
                ComicEntity comicEntity = ComicUtils.DTO2Entity(comicDTO);
                comicEntity.setUserEntity(userEntity);
                comicEntity.setComicGenresEntities(comicGenresEntities);
                comicDAL.update(comicEntity);
            }

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
            Set<ComicGenresEntity> comicGenresEntity = item.getComicGenresEntities();
            Set<ComicGenresDTO> comicGenresDTOS = new LinkedHashSet<ComicGenresDTO>();
            for(ComicGenresEntity genresEntity : comicGenresEntity){
                comicGenresDTOS.add(ComicGenresUtils.entity2DTO(genresEntity));
            }
            ComicDTO comicDTO = ComicUtils.entity2DTO(item);
            comicDTO.setComicGenresEntities(comicGenresDTOS);
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
            ComicEntity comicEntity = comicDAL.findDetailComicUnique(id);
            Set<ComicGenresEntity> comicGenresEntities = comicEntity.getComicGenresEntities();
            Set<ComicGenresDTO> comicGenresDTOS = new LinkedHashSet<ComicGenresDTO>();
            for(ComicGenresEntity item : comicGenresEntities){
                ComicGenresDTO comicGenresDTO = ComicGenresUtils.entity2DTO(item);
                comicGenresDTOS.add(comicGenresDTO);
            }
            comicDTO = ComicUtils.entity2DTO(comicDAL.findDetailComicUnique(id));
            comicDTO.setComicGenresEntities(comicGenresDTOS);
        }
        return comicDTO;
    }
}
