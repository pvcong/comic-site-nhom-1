package vn.group.utils;

import vn.group.data.ComicCommentEntity;
import vn.group.dto.ComicCommentDTO;

public class ComicCommentUtils {
    public static ComicCommentDTO entity2DTO(ComicCommentEntity commentEntity){
        ComicCommentDTO comicCommentDTO = new ComicCommentDTO();
        if(commentEntity != null){
            comicCommentDTO.setContent(commentEntity.getContent());
            comicCommentDTO.setCreatedDate(commentEntity.getCreatedDate());
            comicCommentDTO.setComicCommentId(commentEntity.getComicCommentId());
            //comicCommentDTO.setUserDTO(UserUtils.entity2DTO(commentEntity.getUserEntity()));
           // comicCommentDTO.setComicDTO(ComicUtils.entity2DTO(commentEntity.getComicEntity()));
        }
        return comicCommentDTO;
    }
    public static ComicCommentEntity DTO2Entity(ComicCommentDTO comicCommentDTO){
        ComicCommentEntity comicCommentEntity = new ComicCommentEntity();
        if(comicCommentDTO != null){
            comicCommentEntity.setContent(comicCommentDTO.getContent());
            comicCommentEntity.setCreatedDate(comicCommentDTO.getCreatedDate());
            comicCommentEntity.setComicCommentId(comicCommentDTO.getComicCommentId());
           // comicCommentEntity.setUserEntity(UserUtils.DTO2Entity(comicCommentDTO.getUserDTO()));
            //comicCommentEntity.setComicEntity(ComicUtils.DTO2Entity(comicCommentDTO.getComicDTO()));
        }
        return comicCommentEntity;
    }
}
