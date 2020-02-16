package vn.group.utils;

import vn.group.data.ComicChapterEntity;
import vn.group.dto.ComicChapterDTO;
import vn.group.dto.ComicDTO;

public class ComicChapterUtils {
    public static ComicChapterDTO entity2DTO(ComicChapterEntity comicChapterEntity){
        ComicChapterDTO comicChapterDTO = new ComicChapterDTO();
        if(comicChapterEntity != null){
            comicChapterDTO.setComicChapterId(comicChapterEntity.getComicChapterId());
            comicChapterDTO.setName(comicChapterEntity.getName());
            comicChapterDTO.setCreatedDate(comicChapterEntity.getCreatedDate());
            comicChapterDTO.setImages(comicChapterEntity.getImages());
            //comicChapterDTO.setComicDTO(ComicUtils.entity2DTOUsedComicChapter(comicChapterEntity.getComicEntity()));
        }
        return comicChapterDTO;
    }
    public static ComicChapterDTO entity2DTOFull(ComicChapterEntity comicChapterEntity){
        ComicChapterDTO comicChapterDTO = new ComicChapterDTO();
        if(comicChapterEntity != null){
            comicChapterDTO.setComicChapterId(comicChapterEntity.getComicChapterId());
            comicChapterDTO.setName(comicChapterEntity.getName());
            comicChapterDTO.setCreatedDate(comicChapterEntity.getCreatedDate());
            comicChapterDTO.setImages(comicChapterEntity.getImages());
            comicChapterDTO.setComicDTO(ComicUtils.entity2DTOUsedComicChapter(comicChapterEntity.getComicEntity()));
        }
        return comicChapterDTO;
    }

    public static ComicChapterEntity DTO2Entity(ComicChapterDTO comicChapterDTO){
        ComicChapterEntity comicChapterEntity = new ComicChapterEntity();
        if(comicChapterDTO != null){
            comicChapterEntity.setComicChapterId(comicChapterDTO.getComicChapterId());
            comicChapterEntity.setName(comicChapterDTO.getName());
            comicChapterEntity.setCreatedDate(comicChapterDTO.getCreatedDate());
            comicChapterEntity.setImages(comicChapterDTO.getImages());

           // comicChapterEntity.setComicEntity(ComicUtils.DTO2EntityUsedComicChapter(comicChapterDTO.getComicDTO()));

        }
        return comicChapterEntity;
    }
    public static ComicChapterEntity DTO2EntityFull(ComicChapterDTO comicChapterDTO){
        ComicChapterEntity comicChapterEntity = new ComicChapterEntity();
        if(comicChapterDTO != null){
            comicChapterEntity.setComicChapterId(comicChapterDTO.getComicChapterId());
            comicChapterEntity.setName(comicChapterDTO.getName());
            comicChapterEntity.setCreatedDate(comicChapterDTO.getCreatedDate());
            comicChapterEntity.setImages(comicChapterDTO.getImages());
            comicChapterEntity.setComicEntity(ComicUtils.DTO2EntityUsedComicChapter(comicChapterDTO.getComicDTO()));
            // comicChapterEntity.setComicEntity(ComicUtils.DTO2EntityUsedComicChapter(comicChapterDTO.getComicDTO()));

        }
        return comicChapterEntity;
    }
}
