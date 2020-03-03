package vn.group.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


public class ComicDTO {

    private Integer comicId;

    private String name;

    private String description;

    private String author;

    private String banner;

    private Integer viewTotal;

    private String status;

    private UserDTO userDTO;

    private String avatar;
    private Set<ComicChapterDTO> comicChapterEntities;

    private Set<ComicGenresDTO> comicGenresEntities;

    private Timestamp createdDate;

    private Timestamp modifiedDate;
    private List<WeekdaysDTO> weekdaysEntities;

    private List<ComicCommentDTO> comicCommentEntities;

    private List<ComicReviewDTO> comicReviewEntities;
    public ComicDTO() {
    }

    public ComicDTO(Integer comicId, String name, String description, String author, String banner, Integer viewTotal, String status, UserDTO userDTO, String avatar, Set<ComicChapterDTO> comicChapterEntities, Set<ComicGenresDTO> comicGenresEntities, Timestamp createdDate, Timestamp modifiedDate, List<WeekdaysDTO> weekdaysEntities, List<ComicCommentDTO> comicCommentEntities, List<ComicReviewDTO> comicReviewEntities) {
        this.comicId = comicId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.banner = banner;
        this.viewTotal = viewTotal;
        this.status = status;
        this.userDTO = userDTO;
        this.avatar = avatar;
        this.comicChapterEntities = comicChapterEntities;
        this.comicGenresEntities = comicGenresEntities;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.weekdaysEntities = weekdaysEntities;
        this.comicCommentEntities = comicCommentEntities;
        this.comicReviewEntities = comicReviewEntities;
    }

    public List<ComicCommentDTO> getComicCommentEntities() {
        return comicCommentEntities;
    }

    public void setComicCommentEntities(List<ComicCommentDTO> comicCommentEntities) {
        this.comicCommentEntities = comicCommentEntities;
    }

    public List<ComicReviewDTO> getComicReviewEntities() {
        return comicReviewEntities;
    }

    public void setComicReviewEntities(List<ComicReviewDTO> comicReviewEntities) {
        this.comicReviewEntities = comicReviewEntities;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<WeekdaysDTO> getWeekdaysEntities() {
        return weekdaysEntities;
    }

    public void setWeekdaysEntities(List<WeekdaysDTO> weekdaysEntities) {
        this.weekdaysEntities = weekdaysEntities;
    }

    public Set<ComicChapterDTO> getComicChapterEntities() {
        return comicChapterEntities;
    }

    public void setComicChapterEntities(Set<ComicChapterDTO> comicChapterEntities) {
        this.comicChapterEntities = comicChapterEntities;
    }

    public Set<ComicGenresDTO> getComicGenresEntities() {
        return comicGenresEntities;
    }

    public void setComicGenresEntities(Set<ComicGenresDTO> comicGenresEntities) {
        this.comicGenresEntities = comicGenresEntities;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
}
