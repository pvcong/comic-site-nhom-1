package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.group.dal.ComicChapterDAL;
import vn.group.data.ComicChapterEntity;
import vn.group.data.ComicEntity;
import vn.group.dto.ComicChapterDTO;
import vn.group.dto.ComicDTO;
import vn.group.utils.ComicChapterUtils;
import vn.group.utils.ComicUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicChapterServieImpl implements ComicChapterService {
    @Autowired
    ComicChapterDAL comicChapterDAL;
    public void save(ComicChapterDTO comicChapterDTO) throws HibernateException {
            if(comicChapterDTO != null)
            comicChapterDAL.save(ComicChapterUtils.DTO2EntityFull(comicChapterDTO));

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
            ComicChapterEntity comicChapterEntity = comicChapterDAL.findById(id);

            if(comicChapterEntity != null){
                ComicEntity comicEntity = new ComicEntity();
                if(comicChapterEntity.getComicEntity()!= null)
                    comicEntity.setComicId( comicChapterEntity.getComicEntity().getComicId());
                ComicDTO comicDTO = ComicUtils.entity2DTO(comicEntity);
                comicChapterDTO = ComicChapterUtils.entity2DTO(comicChapterEntity);
                comicChapterDTO.setComicDTO(comicDTO);
            }
        }
        return comicChapterDTO;
    }

    public ComicChapterDTO update(ComicChapterDTO comicChapterDTO) throws HibernateException{
        if(comicChapterDTO != null)
        comicChapterDTO = ComicChapterUtils.entity2DTOFull(comicChapterDAL.update(ComicChapterUtils.DTO2EntityFull(comicChapterDTO)));
        return comicChapterDTO;
    }

    public Object[] findByproperties(List<String> joinTables,Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) throws HibernateException {
        List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        Object[] objects = comicChapterDAL.findByProperty(joinTables,properties, sortProperties, limit, offset, whereClause);
        List<ComicChapterEntity> comicChapterEntities = (List<ComicChapterEntity>) objects[1];
         for(ComicChapterEntity comicChapterEntity : comicChapterEntities){
            ComicChapterDTO comicChapterDTO = ComicChapterUtils.entity2DTO(comicChapterEntity);
            comicChapterDTOList.add(comicChapterDTO);
        }

        return new Object[]{objects[0],comicChapterDTOList};
    }
}
