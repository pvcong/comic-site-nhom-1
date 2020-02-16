package vn.group.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.group.dal.ComicChapterDAL;
import vn.group.data.ComicChapterEntity;
import vn.group.dto.ComicChapterDTO;
import vn.group.utils.ComicChapterUtils;
import vn.group.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ComicChapterServieImpl implements ComicChapterService {
    @Autowired
    ComicChapterDAL comicChapterDAL;
    public void save(ComicChapterDTO comicChapterDTO) throws HibernateException {
        if(comicChapterDTO != null){
            comicChapterDAL.save(ComicChapterUtils.DTO2Entity(comicChapterDTO));
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

    public ComicChapterDTO update(ComicChapterDTO comicChapterDTO) {
        ComicChapterDTO comicChapterDTO1 = null;
        if(comicChapterDTO != null){
            comicChapterDTO1 = ComicChapterUtils.entity2DTO(comicChapterDAL.update(ComicChapterUtils.DTO2Entity(comicChapterDTO)));
        }
        return comicChapterDTO1;
    }

    public List<ComicChapterDTO> findByproperties(Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) {
        List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        List<ComicChapterEntity> comicChapterEntities = comicChapterDAL.findByProperty(properties, sortProperties, limit, offset, whereClause);
        for(ComicChapterEntity comicChapterEntity : comicChapterEntities){
            ComicChapterDTO comicChapterDTO = ComicChapterUtils.entity2DTO(comicChapterEntity);
            comicChapterDTOList.add(comicChapterDTO);
        }

        return comicChapterDTOList;
    }
}
