package vn.group.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class UserDTO {

    private Integer userId;

    private String userName;

    private String fullName;

    private String password;

    private String role;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    public UserDTO() {
    }

    public UserDTO(Integer userId, String userName, String fullName, String password, String role, Timestamp createdDate, Timestamp modifiedDate, List<ComicDTO> comicEntities) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.comicEntities = comicEntities;
    }

    private List<ComicDTO> comicEntities;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


}
