package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.ComicReviewDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.ComicReviewService;
import vn.group.web.utils.ComicReviewCommanderImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class RestComicReviewController {
    @Autowired
    ComicReviewService comicReviewService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/reviews/{id}", method = RequestMethod.GET)
    public ComicReviewDTO getComicReviewById(@PathVariable(name = "id") Integer id){
        ComicReviewDTO comicReviewDTO = null;
        try {
            comicReviewDTO = comicReviewService.findByid(id);
        } catch (HibernateException e){

        }
        if(comicReviewDTO != null && comicReviewDTO.getComicReviewId() != null){
            return comicReviewDTO;
        }
        else {
            throw new NotFoundObjectException("ID" + id);
        }

    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/reviews", method = RequestMethod.GET)
    public Object[] getComicReview(@ModelAttribute ComicReviewCommanderImpl comicReviewCommander){
        //List<ComicReviewDTO> comicReviewDTOList = null;
        Object[] objects = null;
        try {
            setupPropertiesAndSort(comicReviewCommander);
            objects = comicReviewService.findByProperties(null,comicReviewCommander.getProperties(),
                    comicReviewCommander.getSortPropertiesMap()
                    ,comicReviewCommander.getLimit(),comicReviewCommander.getOffset(),null);
        } catch (HibernateException e){

        }
        return objects;
    }

    private void setupPropertiesAndSort(ComicReviewCommanderImpl comicReviewCommander) {
        if(comicReviewCommander.getObjectDTO() != null){
            Map<String,String> propertiesMap = new HashMap<String, String>();
            ComicReviewDTO comicReviewDTO = comicReviewCommander.getObjectDTO();
            if(comicReviewDTO.getComicReviewId() != null){
                propertiesMap.put("comicReviewId",comicReviewDTO.getComicReviewId().toString());
            }
            if(comicReviewDTO.getStar() != null){
                propertiesMap.put("content", comicReviewDTO.getStar().toString());
            }
            if(comicReviewDTO.getCreatedDate() != null){
                propertiesMap.put("createddate",comicReviewDTO.getCreatedDate().toString());
            }
            comicReviewCommander.setProperties(propertiesMap);
        }
        if(comicReviewCommander.getSortProperty() != null && comicReviewCommander.getSortValue()!= null){
            Map<String,String> sortMap = new HashMap<String, String>();
            sortMap.put(comicReviewCommander.getSortProperty(),comicReviewCommander.getSortValue());
            comicReviewCommander.setSortPropertiesMap(sortMap);
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/reviews", method = RequestMethod.POST)
    public void saveComicReview(@RequestBody ComicReviewDTO comicCommentDTO){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicCommentDTO.setCreatedDate(timestamp);
            comicReviewService.save(comicCommentDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/reviews", method = RequestMethod.PUT)
    public void updateComicReview(@RequestBody  ComicReviewDTO comicReviewDTO){
        ComicReviewDTO result = null;
        try {
            result = comicReviewService.update(comicReviewDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/reviews", method = RequestMethod.DELETE)
    public void deleteComicReview(@RequestBody  List<ComicReviewDTO> comicReviewDTOS){
        try {
            comicReviewService.delete(comicReviewDTOS);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
}
