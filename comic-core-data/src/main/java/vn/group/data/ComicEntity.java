package vn.group.data;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comic")

public class ComicEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer comicId;
    @Column( name = "name")
    private String name;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "banner")
    private String banner;
    @Column(name = "viewtotal")
    private Integer viewTotal;
    @Column(name = "status")
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
    @OneToMany( fetch = FetchType.LAZY,mappedBy = "comicEntity",cascade = CascadeType.ALL)
    private Set<ComicChapterEntity> comicChapterEntities;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "comichavgenres", joinColumns = {@JoinColumn(name = "comicid")},
    inverseJoinColumns = {@JoinColumn(name = "comicgenresid")})
    private Set<ComicGenresEntity> comicGenresEntities;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "comichavweekdays", joinColumns = {@JoinColumn(name = "comicid")},
    inverseJoinColumns = {@JoinColumn(name = "weekdaysid")})
    private List<WeekdaysEntity> weekdaysEntities;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comicEntity", cascade = CascadeType.ALL)
    private List<ComicCommentEntity> comicCommentEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comicEntity", cascade = CascadeType.ALL)
    private List<ComicReviewEntity> comicReviewEntities;
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public List<WeekdaysEntity> getWeekdaysEntities() {
        return weekdaysEntities;
    }

    public void setWeekdaysEntities(List<WeekdaysEntity> weekdaysEntities) {
        this.weekdaysEntities = weekdaysEntities;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ComicCommentEntity> getComicCommentEntities() {
        return comicCommentEntities;
    }

    public void setComicCommentEntities(List<ComicCommentEntity> comicCommentEntities) {
        this.comicCommentEntities = comicCommentEntities;
    }

    public List<ComicReviewEntity> getComicReviewEntities() {
        return comicReviewEntities;
    }

    public void setComicReviewEntities(List<ComicReviewEntity> comicReviewEntities) {
        this.comicReviewEntities = comicReviewEntities;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<ComicChapterEntity> getComicChapterEntities() {
        return comicChapterEntities;
    }

    public void setComicChapterEntities(Set<ComicChapterEntity> comicChapterEntities) {
        this.comicChapterEntities = comicChapterEntities;
    }

    public Set<ComicGenresEntity> getComicGenresEntities() {
        return comicGenresEntities;
    }

    public void setComicGenresEntities(Set<ComicGenresEntity> comicGenresEntities) {
        this.comicGenresEntities = comicGenresEntities;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Integer getViewTotal() {
        return viewTotal;
    }

    public void setViewTotal(Integer viewTotal) {
        this.viewTotal = viewTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "";
    }
}
