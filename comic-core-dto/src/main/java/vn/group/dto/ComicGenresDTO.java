package vn.group.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class ComicGenresDTO {

    private Integer comicGenresId;

    private String name;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private List<ComicDTO> comicEntities;

    public ComicGenresDTO() {
    }

    public ComicGenresDTO(Integer comicGenresId, String name, Timestamp createdDate, Timestamp modifiedDate, List<ComicDTO> comicEntities) {
        this.comicGenresId = comicGenresId;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.comicEntities = comicEntities;
    }

    public Integer getComicGenresId() {
        return comicGenresId;
    }

    public void setComicGenresId(Integer comicGenresId) {
        this.comicGenresId = comicGenresId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
