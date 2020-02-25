package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.group.common.ServiceConstant;
import vn.group.dal.ComicChapterDAL;
import vn.group.data.ComicChapterEntity;
import vn.group.dto.ComicChapterDTO;
import vn.group.utils.ComicChapterUtils;
import vn.group.utils.UploadUtils;
import vn.group.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicChapterServieImpl implements ComicChapterService {
    @Autowired
    ComicChapterDAL comicChapterDAL;
    public void save(ComicChapterDTO comicChapterDTO, MultipartFile[] multipartFile,String path) throws HibernateException {
        if(comicChapterDTO != null && multipartFile != null){
            Object[] result = UploadUtils.uploadFile(multipartFile, ServiceConstant.locationComicChapterImage,path);
            if((Boolean)result[0] == false)
            {
                comicChapterDTO.setImages( result[2].toString());
                comicChapterDAL.save(ComicChapterUtils.DTO2EntityFull(comicChapterDTO));
            }

        }
    }

    public void delete(List<ComicChapterDTO> comicChapterDTOList) throws HibernateException {
        if(comicChapterDTOList != null){
            List<ComicChapterEntity> comicChapterEntities = new ArrayList<ComicChapterEntity>();
            for(ComicChapterDTO comicChapterDTO : comicChapterDTOList){
                ComicChapterEntity comicChapterEntity = ComicChapterUtils.DTO2Entity(comicChapterDTO);
                comicChapterEntities.add(comicChapterEntity);
            }
            comicChapterDAL.delete(comicChapterEntities);
        }
    }

    public ComicChapterDTO findById(Integer id) throws HibernateException {
        ComicChapterDTO comicChapterDTO = null;
        if(id != null){
            comicChapterDTO = ComicChapterUtils.entity2DTOFull(comicChapterDAL.findById(id));
        }
        return comicChapterDTO;
    }

    public ComicChapterDTO update(ComicChapterDTO comicChapterDTO,MultipartFile[] multipartFile,String path) throws HibernateException{
        ComicChapterDTO comicChapterDTO1 = null;
        if(comicChapterDTO != null) {
            Object[] result = UploadUtils.uploadFile(multipartFile, ServiceConstant.locationComicChapterImage,path);
            if ((Boolean) result[0] == false) {
                comicChapterDTO.setImages(result[2].toString());
                comicChapterDTO1 = ComicChapterUtils.entity2DTOFull(comicChapterDAL.update(ComicChapterUtils.DTO2EntityFull(comicChapterDTO)));
            }
        }
        return comicChapterDTO1;
    }

    public List<ComicChapterDTO> findByproperties(Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException {
        List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        List<ComicChapterEntity> comicChapterEntities = comicChapterDAL.findByProperty(properties, sortProperties, limit, offset, whereClause);
        for(ComicChapterEntity comicChapterEntity : comicChapterEntities){
            ComicChapterDTO comicChapterDTO = ComicChapterUtils.entity2DTO(comicChapterEntity);
            comicChapterDTOList.add(comicChapterDTO);
        }

        return comicChapterDTOList;
    }
}
