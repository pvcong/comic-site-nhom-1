package vn.group.data;

import javax.annotation.Generated;
import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "comiccomment")
public class ComicCommentEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer comicCommentId;
    @Column(name = "content")
    private String content;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comicid")
    private ComicEntity comicEntity;

    public Integer getComicCommentId() {
        return comicCommentId;
    }

    public void setComicCommentId(Integer comicCommentId) {
        this.comicCommentId = comicCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
