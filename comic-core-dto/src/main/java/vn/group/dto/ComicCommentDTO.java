package vn.group.dto;

import java.sql.Timestamp;

public class ComicCommentDTO {

   private Integer comicCommentId;
    private String content;

    private Timestamp createdDate;

    private UserDTO userDTO;
    private ComicDTO comicDTO;


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
