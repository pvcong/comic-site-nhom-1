package vn.group.dto;

import java.util.List;

public class WeekdaysDTO {
    private Integer weekdaysId;
    private String name;
    private List<ComicDTO> comicDTOList;

    public List<ComicDTO> getComicDTOList() {
        return comicDTOList;
    }

    public void setComicDTOList(List<ComicDTO> comicDTOList) {
        this.comicDTOList = comicDTOList;
    }

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
