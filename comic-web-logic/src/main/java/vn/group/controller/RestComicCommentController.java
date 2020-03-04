package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.ComicCommentDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.ComicCommentService;
import vn.group.utils.ComicCommentUtils;
import vn.group.web.utils.ComicCommentCommanderImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestComicCommentController {
    @Autowired
    ComicCommentService comicCommentService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comments/{id}", method = RequestMethod.GET)
    public ComicCommentDTO getComicCommentById(@PathVariable(name = "id") Integer id){
        ComicCommentDTO comicCommentDTO = null;
        try {
            comicCommentDTO = comicCommentService.findById(id);
        } catch (HibernateException e){

        }
        if(comicCommentDTO!= null && comicCommentDTO.getComicCommentId()!= null) {
            return comicCommentDTO;
        }
         else {
            throw new NotFoundObjectException("ID" + id);
        }
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comments", method = RequestMethod.GET)
    public Object[] getComicComments(@ModelAttribute ComicCommentCommanderImpl comicCommentCommander){
       Object[] objects = null;
        try {
            List<String> joinTables = new ArrayList<String>();
            joinTables.add("cc.userEntity u");
            comicCommentCommander.setJoinTables(joinTables);
            setupPropertiesAndSort(comicCommentCommander);
            objects = comicCommentService.findByProperties(comicCommentCommander.getJoinTables(),comicCommentCommander.getProperties(),
                    comicCommentCommander.getSortPropertiesMap()
                    ,comicCommentCommander.getLimit(),comicCommentCommander.getOffset(),null);
        } catch (HibernateException e){

        }
        return objects;
    }

    private void setupPropertiesAndSort(ComicCommentCommanderImpl comicCommentCommander) {
        if(comicCommentCommander.getObjectDTO() != null){
            Map<String,String> propertiesMap = new HashMap<String, String>();
            ComicCommentDTO comicCommentDTO = comicCommentCommander.getObjectDTO();
            if(comicCommentDTO.getComicCommentId() != null){
                propertiesMap.put("comicCommentId",comicCommentDTO.getComicCommentId().toString());
            }
            if(comicCommentDTO.getContent() != null){
                propertiesMap.put("content", comicCommentDTO.getContent());
            }
            if(comicCommentDTO.getCreatedDate() != null){
                propertiesMap.put("createddate",comicCommentDTO.getCreatedDate().toString());
            }
            comicCommentCommander.setProperties(propertiesMap);
        }
        if(comicCommentCommander.getSortProperty() != null && comicCommentCommander.getSortValue()!= null){
            Map<String,String> sortMap = new HashMap<String, String>();
            sortMap.put(comicCommentCommander.getSortProperty(),comicCommentCommander.getSortValue());
            comicCommentCommander.setSortPropertiesMap(sortMap);
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/comments", method = RequestMethod.POST)
    public void saveComicComment(@RequestBody ComicCommentDTO comicCommentDTO){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicCommentDTO.setCreatedDate(timestamp);
            comicCommentService.save(comicCommentDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/comments", method = RequestMethod.PUT)
    public void updateComicComment(@RequestBody  ComicCommentDTO comicCommentDTO){
        ComicCommentDTO result = null;
        try {
            result = comicCommentService.update(comicCommentDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/comments", method = RequestMethod.DELETE)
    public void deleteComicComment(@RequestBody  List<ComicCommentDTO> comicCommentDTOS){
        try {
            comicCommentService.delete(comicCommentDTOS);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }

}
