package vn.group.utils;

import vn.group.data.WeekdaysEntity;
import vn.group.dto.WeekdaysDTO;

public class WeekdaysUtils {
    public static WeekdaysDTO entity2DTO(WeekdaysEntity weekdaysEntity){
        WeekdaysDTO weekdaysDTO = new WeekdaysDTO();
        if(weekdaysEntity != null){
            weekdaysDTO.setName(weekdaysEntity.getName());
            weekdaysDTO.setWeekdaysId(weekdaysEntity.getWeekdaysId());
        }
        return weekdaysDTO;
    }
    public static WeekdaysEntity dto2Entity(WeekdaysDTO weekdaysDTO){
        WeekdaysEntity weekdaysEntity = new WeekdaysEntity();
        if(weekdaysDTO != null){
            weekdaysEntity.setName(weekdaysDTO.getName());
            weekdaysEntity.setWeekdaysId(weekdaysDTO.getWeekdaysId());
        }
        return weekdaysEntity;
    }
}
