package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.ComicDTO;
import vn.group.dto.UserDTO;
import vn.group.service.ComicService;
import vn.learn.web.utils.ComicGenresCommanderUtils;
import vn.learn.web.utils.ComicGenresCommanderUtilsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestComicController {
    @Autowired
    ComicService comicService;
    @RequestMapping( value = "/comic/{id}", method = RequestMethod.GET)
    public ComicDTO findDetailComic(@PathVariable( name = "id") Integer id){
        ComicDTO comicDTO = null;
        try {
            comicDTO =  comicService.findDetaiComicUntique(id);
        } catch (HibernateException e){

        }

        return comicDTO;
    }
    @RequestMapping( value = "/comic", method = RequestMethod.POST)
    public void saveComic(@ModelAttribute ComicDTO comicDTO){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicDTO.setCreatedDate(timestamp);
            comicService.save(comicDTO);
            } catch (HibernateException e){

            }
    }
    @RequestMapping( value = "/comic", method = RequestMethod.PUT)
    public void updateComic(@RequestBody  ComicDTO comicDTO){
        ComicDTO result = null;
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicDTO.setModifiedDate(timestamp);
            result = comicService.update(comicDTO);
        } catch (HibernateException e){

        }

    }
    @RequestMapping( value = "/comic", method = RequestMethod.DELETE)
    public void deleteComic(@RequestBody  List<ComicDTO> comicDTO){
        try {
            comicService.delete(comicDTO);
        } catch (HibernateException e){

        }

    }
//    @Transactional
//    @RequestMapping( value = "/comic", method = RequestMethod.GET)
//    public List<ComicDTO> getAllComic(){
//        List<ComicDTO> comicDTOList = null;
//        try {
//           comicDTOList = comicService.findAll();
//        } catch (HibernateException e){
//            String error ="";
//        }
//        return comicDTOList;
//    }
    @RequestMapping(value = "/comic/ch?{property}={value}", method = RequestMethod.GET)
    public ComicDTO findByPropertyUnique(
            HttpServletRequest req, HttpServletResponse res,
            @PathVariable(name = "property") String property, @PathVariable( name = "value") String propertyValue){
        ComicDTO comicDTO = null;
        try {
            comicDTO = comicService.findByPropertyUnique(property,propertyValue);

        } catch (HibernateException e) {

        }
        return comicDTO;
    }
    @RequestMapping (value = "comic", method = RequestMethod.GET)
    public List<ComicDTO> getComics(@ModelAttribute ComicGenresCommanderUtilsImpl comicGenresCommanderUtils){
        List<ComicDTO> comicDTOList = new ArrayList<ComicDTO>();
        try {
            //setupSortAndProperty(userCommanderUtils);
            comicDTOList =  comicService.findByproperties
                    (comicGenresCommanderUtils.getProperties(),
                            comicGenresCommanderUtils.getSortProperties(), comicGenresCommanderUtils.getLimit(),
                            comicGenresCommanderUtils.getOffset(),null);
        } catch (HibernateException e){

        }
        return comicDTOList;
    }


}
