package vn.group.data;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "comicgenres")

public class ComicGenresEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer comicGenresId;
    @Column( name = "name")
    private String name;
    @Column(name = "createddate")
    private Timestamp createdDate;
    @Column(name = "modifieddate")
    private Timestamp modifiedDate;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "comicGenresEntities")
    private List<ComicEntity> comicEntities;
    public Integer getComicGenresId() {
        return comicGenresId;
    }

    public List<ComicEntity> getComicEntities() {
        return comicEntities;
    }

    public void setComicEntities(List<ComicEntity> comicEntities) {
        this.comicEntities = comicEntities;
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

    @Override
    public String toString() {
        return "";
    }
}
