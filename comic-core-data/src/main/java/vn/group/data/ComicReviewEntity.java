package vn.group.data;

import javax.annotation.Generated;
import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "comicreview")
public class ComicReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comicReviewId;
    @Column(name = "star")
    private Integer star;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comicid")
    private ComicEntity comicEntity;

    public Integer getComicReviewId() {
        return comicReviewId;
    }

    public void setComicReviewId(Integer comicReviewId) {
        this.comicReviewId = comicReviewId;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ComicEntity getComicEntity() {
        return comicEntity;
    }

    public void setComicEntity(ComicEntity comicEntity) {
        this.comicEntity = comicEntity;
    }
}
