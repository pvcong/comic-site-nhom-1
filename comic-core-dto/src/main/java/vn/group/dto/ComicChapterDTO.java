package vn.group.dto;

import javax.persistence.*;


public class ComicChapterDTO {

    private Integer comicChapterId;

    private String name;

    private String images;

    private ComicDTO comicDTO;
    private String createdDate;

    public ComicChapterDTO() {
    }

    public ComicChapterDTO(Integer comicChapterId, String name, String images, ComicDTO comicDTO, String createdDate) {
        this.comicChapterId = comicChapterId;
        this.name = name;
        this.images = images;
        this.comicDTO = comicDTO;
        this.createdDate = createdDate;
    }



    public Integer getComicChapterId() {
        return comicChapterId;
    }

    public void setComicChapterId(Integer comicChapterId) {
        this.comicChapterId = comicChapterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public ComicDTO getComicDTO() {
        return comicDTO;
    }

    public void setComicDTO(ComicDTO comicDTO) {
        this.comicDTO = comicDTO;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
