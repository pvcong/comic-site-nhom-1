package vn.group.utils;

import vn.group.data.ComicChapterEntity;
import vn.group.data.ComicEntity;
import vn.group.data.ComicGenresEntity;
import vn.group.dto.ComicChapterDTO;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ComicUtils {
    public static ComicDTO entity2DTO(ComicEntity comicEntity){
        ComicDTO comicDTO = new ComicDTO();
        if(comicEntity != null){
            comicDTO.setComicId(comicEntity.getComicId());
            comicDTO.setName(comicEntity.getName());
            comicDTO.setAvatar(comicEntity.getAvatar());
            comicDTO.setStatus(comicEntity.getStatus());
            comicDTO.setAuthor(comicEntity.getAuthor());
            comicDTO.setViewTotal(comicEntity.getViewTotal());
            comicDTO.setBanner(comicEntity.getBanner());
            comicDTO.setCreatedDate(comicEntity.getCreatedDate());
            comicDTO.setModifiedDate(comicEntity.getModifiedDate());
            comicDTO.setDescription(comicEntity.getDescription());
//            Set<ComicChapterDTO> comicChapterDTOS = new LinkedHashSet<ComicChapterDTO>();
//            for(ComicChapterEntity item : comicEntity.getComicChapterEntities()){
//                ComicChapterDTO comicChapterDTO = ComicChapterUtils.entity2DTO(item);
//                comicChapterDTOS.add(comicChapterDTO);
//            }


        }
        return comicDTO;
    }

    public static ComicEntity DTO2Entity(ComicDTO comicDTO){
        ComicEntity comicEntity = new ComicEntity();
        if(comicDTO != null){
            comicEntity.setComicId(comicDTO.getComicId());
            comicEntity.setName(comicDTO.getName());
            comicEntity.setStatus(comicDTO.getStatus());
            comicEntity.setAvatar(comicDTO.getAvatar());
            comicEntity.setAuthor(comicDTO.getAuthor());
            comicEntity.setViewTotal(comicDTO.getViewTotal());
            comicEntity.setBanner(comicDTO.getBanner());
            comicEntity.setCreatedDate(comicDTO.getCreatedDate());
            comicEntity.setModifiedDate(comicDTO.getModifiedDate());
            comicEntity.setDescription(comicDTO.getDescription());
//            Set<ComicChapterEntity> comicChapterEntities = new HashSet<ComicChapterEntity>();
//            for(ComicChapterDTO item : comicDTO.getComicChapterEntities()){
//                ComicChapterEntity comicChapterEntity = ComicChapterUtils.DTO2Entity(item);
//                comicChapterEntities.add(comicChapterEntity);
//            }

        }
        return comicEntity;
    }
    public static ComicDTO entity2DTOUsedComicChapter(ComicEntity comicEntity){
        ComicDTO comicDTO = new ComicDTO();
        if(comicEntity != null){
            comicDTO.setComicId(comicEntity.getComicId());
            comicDTO.setName(comicEntity.getName());
            //comicDTO.setUserDTO(UserUtils.entity2DTO(comicEntity.getUserEntity()));
            comicDTO.setStatus(comicEntity.getStatus());
            comicDTO.setAuthor(comicEntity.getAuthor());
            comicDTO.setViewTotal(comicEntity.getViewTotal());
            comicDTO.setBanner(comicEntity.getBanner());
            comicDTO.setCreatedDate(comicEntity.getCreatedDate());
            comicDTO.setModifiedDate(comicEntity.getModifiedDate());
            comicDTO.setDescription(comicEntity.getDescription());
            //Set<ComicChapterDTO> comicChapterDTOS = new LinkedHashSet<ComicChapterDTO>();

            //Set<ComicGenresDTO> comicGenresDTOS = new LinkedHashSet<ComicGenresDTO>();

            //comicDTO.setComicChapterEntities(comicChapterDTOS);
            //comicDTO.setComicGenresEntities(comicGenresDTOS);

        }
        return comicDTO;
    }
    public static ComicEntity DTO2EntityUsedComicChapter(ComicDTO comicDTO){
        ComicEntity comicEntity = new ComicEntity();
        if(comicDTO != null){
            comicEntity.setComicId(comicDTO.getComicId());
            comicEntity.setName(comicDTO.getName());
            comicEntity.setStatus(comicDTO.getStatus());
            comicEntity.setUserEntity(UserUtils.DTO2Entity(comicDTO.getUserDTO()));
            comicEntity.setAuthor(comicDTO.getAuthor());
            comicEntity.setViewTotal(comicDTO.getViewTotal());
            comicEntity.setBanner(comicDTO.getBanner());
            comicEntity.setCreatedDate(comicDTO.getCreatedDate());
            comicEntity.setModifiedDate(comicDTO.getModifiedDate());
            comicEntity.setDescription(comicDTO.getDescription());
//            Set<ComicChapterEntity> comicChapterEntities = new HashSet<ComicChapterEntity>();
//
//            Set<ComicGenresEntity> comicGenresEntities = new HashSet<ComicGenresEntity>();
//
//            comicEntity.setComicChapterEntities(comicChapterEntities);
//            comicEntity.setComicGenresEntities(comicGenresEntities);
        }
        return comicEntity;
    }
}
