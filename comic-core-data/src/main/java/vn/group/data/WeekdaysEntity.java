package vn.group.data;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weekdays")
public class WeekdaysEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer weekdaysId;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "weekdaysEntities")
    private List<ComicEntity> comicEntities;
    public Integer getWeekdaysId() {
        return weekdaysId;
    }

    public void setWeekdaysId(Integer weekdaysId) {
        this.weekdaysId = weekdaysId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
