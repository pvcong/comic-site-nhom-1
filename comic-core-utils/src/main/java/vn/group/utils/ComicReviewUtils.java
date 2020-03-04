package vn.group.utils;

import vn.group.data.ComicReviewEntity;
import vn.group.dto.ComicReviewDTO;

public class ComicReviewUtils {
    public static ComicReviewDTO entity2DTO(ComicReviewEntity comicReviewEntity){
        ComicReviewDTO comicReviewDTO = new ComicReviewDTO();
        if(comicReviewEntity != null){
            comicReviewDTO.setComicReviewId(comicReviewEntity.getComicReviewId());
            comicReviewDTO.setCreatedDate(comicReviewEntity.getCreatedDate());
            comicReviewDTO.setStar(comicReviewEntity.getStar());
           // comicReviewDTO.setUserDTO(UserUtils.entity2DTO(comicReviewEntity.getUserEntity()));
           // comicReviewDTO.setComicDTO(ComicUtils.entity2DTO(comicReviewEntity.getComicEntity()));
        }
        return comicReviewDTO;
    }
    public static ComicReviewEntity DTO2Entity(ComicReviewDTO comicReviewDTO){
        ComicReviewEntity comicReviewEntity = new ComicReviewEntity();
        if(comicReviewDTO != null){
            comicReviewEntity.setStar(comicReviewDTO.getStar());
            comicReviewEntity.setCreatedDate(comicReviewDTO.getCreatedDate());
            comicReviewEntity.setComicReviewId(comicReviewDTO.getComicReviewId());
           // comicReviewEntity.setUserEntity(UserUtils.DTO2Entity(comicReviewDTO.getUserDTO()));
           //comicReviewEntity.setComicEntity(ComicUtils.DTO2Entity(comicReviewDTO.getComicDTO()));
        }
        return comicReviewEntity;
    }
}
