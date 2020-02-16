package vn.group.utils;

import vn.group.data.ComicGenresEntity;
import vn.group.dto.ComicGenresDTO;

public class ComicGenresUtils {
    public static ComicGenresDTO entity2DTO(ComicGenresEntity comicGenresEntity){
        ComicGenresDTO comicGenresDTO = new ComicGenresDTO();
        if(comicGenresEntity != null){
            comicGenresDTO.setComicGenresId(comicGenresEntity.getComicGenresId());
            comicGenresDTO.setName(comicGenresEntity.getName());
            comicGenresDTO.setCreatedDate(comicGenresEntity.getCreatedDate());
            comicGenresDTO.setModifiedDate(comicGenresEntity.getModifiedDate());
        }
        return comicGenresDTO;
    }

    public static ComicGenresEntity DTO2Entity(ComicGenresDTO comicGenresDTO){
        ComicGenresEntity comicGenresEntity = new ComicGenresEntity();
        if(comicGenresDTO != null){
            comicGenresEntity.setComicGenresId(comicGenresDTO.getComicGenresId());
            comicGenresEntity.setName(comicGenresDTO.getName());
            comicGenresEntity.setCreatedDate(comicGenresDTO.getCreatedDate());
            comicGenresEntity.setModifiedDate(comicGenresDTO.getModifiedDate());
        }
        return comicGenresEntity;
    }
}
