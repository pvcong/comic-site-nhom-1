package vn.group.data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comicchapter")
public class ComicChapterEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer comicChapterId;
    @Column(name = "name")
    private String name;
    @Column(name = "images")
    private String images;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "comicid")
    private ComicEntity comicEntity;
    @Column( name = "createddate")
    private Timestamp createdDate;

    public void setComicEntity(ComicEntity comicEntity) {
        this.comicEntity = comicEntity;
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

    public ComicEntity getComicEntity() {
        return comicEntity;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "";
    }
}
