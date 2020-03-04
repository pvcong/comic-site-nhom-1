package vn.group.dto;

import java.sql.Timestamp;

public class ComicReviewDTO {
    private Integer comicReviewId;
    private Integer star;

    private Timestamp createdDate;

    private UserDTO userDTO;

    private ComicDTO comicDTO;

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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ComicDTO getComicDTO() {
        return comicDTO;
    }

    public void setComicDTO(ComicDTO comicDTO) {
        this.comicDTO = comicDTO;
    }
}
