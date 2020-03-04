package vn.group.service;

import vn.group.dto.WeekdaysDTO;

import java.util.List;
import java.util.Map;

public interface WeekdaysService {
    List<WeekdaysDTO> findAll() ;
    void save(WeekdaysDTO weekdaysDTO);
    WeekdaysDTO update(WeekdaysDTO weekdaysDTO);
    void delete(List<WeekdaysDTO> weekdaysDTOS);
    WeekdaysDTO findById(Integer id);
    Object[] findByproperties(List<String> joinTables, Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
    WeekdaysDTO findByPropertyUnique(String property, Object propertyValue);
    Object[] findComicOfWeekdays(List<String> joinTables, Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause);
}
