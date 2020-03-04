package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.WeekdaysDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.WeekdaysService;
import vn.group.web.utils.WeekdaysCommanderUtilsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestWeekdaysController {
    @Autowired
    WeekdaysService weekdaysService;
    @RequestMapping(value = "/weekdays/{id}")
    public WeekdaysDTO getWeekdaysById(@PathVariable(name = "id") Integer id){
        WeekdaysDTO weekdaysDTO = null;
        try {
            weekdaysDTO = weekdaysService.findById(id);
        } catch (HibernateException e){
            throw new ExecDatabaseException((e.getLocalizedMessage()));
        }
        if(weekdaysDTO!= null && weekdaysDTO.getWeekdaysId()!= null)
        return weekdaysDTO;
        else throw new NotFoundObjectException("ID" + id);
    }
    @RequestMapping(value = "/weekdays")
    public Object[] getWeekdays(WeekdaysCommanderUtilsImpl commanderUtils){
        Object[] objects = null;
        try {
            setupCommander(commanderUtils);
            objects = weekdaysService.findByproperties(commanderUtils.getJoinTables()
            ,commanderUtils.getProperties(),commanderUtils.getSortPropertiesMap(),
                    commanderUtils.getLimit(),commanderUtils.getOffset(),null);
        } catch (HibernateException e){
            throw new ExecDatabaseException((e.getLocalizedMessage()));
        }
        return objects;
    }

    private void setupCommander(WeekdaysCommanderUtilsImpl commanderUtils) {
        if(commanderUtils.getObjectDTO() != null){
            WeekdaysDTO weekdaysDTO = commanderUtils.getObjectDTO();
            Map<String,String> mapProperties = new HashMap<String, String>();
            if(!StringUtils.isEmpty(weekdaysDTO.getName())){
                mapProperties.put("name",weekdaysDTO.getName());
            }
            if(weekdaysDTO.getWeekdaysId() != null){
                mapProperties.put("weekdaysId",weekdaysDTO.getWeekdaysId().toString());
            }
            commanderUtils.setProperties(mapProperties);
        }
        if(!StringUtils.isEmpty(commanderUtils.getSortProperty()) && !StringUtils.isEmpty(commanderUtils.getSortValue())){
            Map<String,String> sortMap = new HashMap<String, String>();
            sortMap.put(commanderUtils.getSortProperty(),commanderUtils.getSortValue());
            commanderUtils.setSortPropertiesMap(sortMap);
        }
    }

    @RequestMapping(value = "/weekdays/{id}/comics")
    public Object[] getComicOfWeekdays(WeekdaysCommanderUtilsImpl commanderUtils,
                                       @PathVariable(name = "id") Integer id){
        Object[] objects = null;
        try {
            List<String> joinTables = new ArrayList<String>();
            joinTables.add("cc.weekdaysEntities we");
            joinTables.add("cc.comicGenresEntities cge");
            Map<String,String> propertiesMap = new HashMap<String, String>();
            Map<String,String> sortProperties = new HashMap<String, String>();
            sortProperties.put("cc.viewTotal","DESC");
            propertiesMap.put("we.weekdaysId",id.toString());
            commanderUtils.setSortPropertiesMap(sortProperties);
            commanderUtils.setProperties(propertiesMap);
            commanderUtils.setJoinTables(joinTables);
            objects = weekdaysService.findComicOfWeekdays(commanderUtils.getJoinTables()
                    ,commanderUtils.getProperties(),commanderUtils.getSortPropertiesMap(),
                    commanderUtils.getLimit(),commanderUtils.getOffset(),null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
        if(Integer.parseInt(objects[0].toString()) == 0){
            throw new NotFoundObjectException("ID" + id);
        }
        return objects;
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/weekdays", method = RequestMethod.POST)
    public void saveWeekDays(@RequestBody WeekdaysDTO weekdaysDTO){
        try{
            weekdaysService.save(weekdaysDTO);
        }
        catch(HibernateException e){
            throw new ExecDatabaseException((e.getLocalizedMessage()));
        }

    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/weekdays", method = RequestMethod.PUT)
    public void updateWeekDays(@RequestBody WeekdaysDTO weekdaysDTO){
        try{
            weekdaysService.update(weekdaysDTO);
        }
        catch(HibernateException e){
            throw new ExecDatabaseException((e.getLocalizedMessage()));
        }

    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/weekdays", method = RequestMethod.DELETE)
    public void deleteWeekDays(@RequestBody List<WeekdaysDTO> weekdaysDTOS){
        try{
            weekdaysService.delete(weekdaysDTOS);
        }
        catch(HibernateException e){
            throw new ExecDatabaseException((e.getLocalizedMessage()));
        }

    }
}
