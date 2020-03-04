package vn.group.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ComicCommentEntity> comicCommentEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ComicReviewEntity> comicReviewEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ComicEntity> comicEntities;

    public List<ComicEntity> getComicEntities() {
        return comicEntities;
    }

    public void setComicEntities(List<ComicEntity> comicEntities) {
        this.comicEntities = comicEntities;
    }

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

    @Override
    public String toString() {
        return "";
    }
}
