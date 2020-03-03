package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.ComicGenresDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.ComicGenresService;
import vn.group.web.utils.ComicGenresCommanderUtilsImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestComicGenresController {
    @Autowired
    ComicGenresService comicGenresService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/genres/{id}", method = RequestMethod.GET)
    public ComicGenresDTO getGenresById(@PathVariable( name = "id") Integer id){
        ComicGenresDTO comicGenresDTO = null;
        try {


            comicGenresDTO =  comicGenresService.findById(id);

        } catch (HibernateException e){

        }
        if(comicGenresDTO != null && comicGenresDTO.getComicGenresId()!= null){
            return comicGenresDTO;
        }
        else {
            throw new NotFoundObjectException("ID " + id);
        }

    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/genres", method = RequestMethod.POST)
    public void saveGenres(@RequestBody ComicGenresDTO comicGenresDTO){
        try {
            comicGenresService.save(comicGenresDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/genres", method = RequestMethod.PUT)
    public void updateGenres(@RequestBody  ComicGenresDTO comicGenresDTO){
        ComicGenresDTO result = null;
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicGenresDTO.setModifiedDate(timestamp);
            result = comicGenresService.update(comicGenresDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/genres", method = RequestMethod.DELETE)
    public void deleteGenres(@RequestBody List<ComicGenresDTO> comicGenresDTOList){
        try {
            comicGenresService.delete(comicGenresDTOList);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/genres", method = RequestMethod.GET)
    public Object[] getGenres(@ModelAttribute ComicGenresCommanderUtilsImpl comicGenresCommanderUtils){
        //List<ComicGenresDTO> comicGenresDTOList = null;
        Object[] objects = null;
        try {
            setupPropertiesandSort(comicGenresCommanderUtils);
            objects = comicGenresService.findByproperties(null,comicGenresCommanderUtils.getProperties(),
                    comicGenresCommanderUtils.getSortPropertiesMap(),
                    comicGenresCommanderUtils.getLimit(),
                    comicGenresCommanderUtils.getOffset(),null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
        return objects;
    }

    private void setupPropertiesandSort(ComicGenresCommanderUtilsImpl comicGenresCommanderUtils) {
        if(comicGenresCommanderUtils.getObjectDTO() != null){
            ComicGenresDTO comicGenresDTO = comicGenresCommanderUtils.getObjectDTO();
            Map<String,String> propertiesMap = new HashMap<String, String>();
            if(comicGenresDTO.getName() != null){
                propertiesMap.put("name", comicGenresDTO.getName());
            }
            if(comicGenresDTO.getComicGenresId() != null){
                propertiesMap.put("comicGenresId", comicGenresDTO.getComicGenresId().toString());
            }
            if(comicGenresDTO.getModifiedDate() != null){
                propertiesMap.put("modifieddate", comicGenresDTO.getModifiedDate().toString());
            }
            if(comicGenresDTO.getCreatedDate() != null){
                propertiesMap.put("createddate", comicGenresDTO.getCreatedDate().toString());
            }
            comicGenresCommanderUtils.setProperties(propertiesMap);
        }
        if(comicGenresCommanderUtils.getSortProperty() != null && comicGenresCommanderUtils.getProperties() != null){
            if(comicGenresCommanderUtils.getProperties().equals("view")){
                Map<String,String> sortMap = new HashMap<String, String>();
                comicGenresCommanderUtils.setSortProperty("cc.viewTotal");
                sortMap.put(comicGenresCommanderUtils.getSortProperty(), comicGenresCommanderUtils.getSortValue());
                comicGenresCommanderUtils.setSortPropertiesMap(sortMap);
            }

        }
    }
//    @RequestMapping(value = "/genres?{id}/comics", method = RequestMethod.GET)
//    public ComicGenresDTO findByPropertyUnique(
//            HttpServletRequest req, HttpServletResponse res,
//            @PathVariable(name = "id") Integer integer){
//        ComicGenresDTO comicGenresDTO = null;
//        try {
//           // comicGenresDTO = comicGenresService.findByPropertyUnique("id",id);
//        } catch (HibernateException e) {
//
//        }
//        return comicGenresDTO;
//    }
@ResponseStatus(code = HttpStatus.OK)
@RequestMapping (value = "genres/{id}/comics", method = RequestMethod.GET)
public Object[] getComicsOfGenres(@PathVariable(name = "id") Integer id,ComicGenresCommanderUtilsImpl comicGenresCommanderUtils){
    //List<ComicDTO> comicDTOList = new ArrayList<ComicDTO>();
    Object[] objects = null;
    try {
        List<String> joinTables = new ArrayList<String>();
        joinTables.add("cc.comicGenresEntities cge");
        Map<String,String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("cge.comicGenresId",id.toString());
        setupPropertiesandSort(comicGenresCommanderUtils);
        objects =  comicGenresService.findComicsOfGenres
                (joinTables,propertiesMap,
                        null, comicGenresCommanderUtils.getLimit(),
                        comicGenresCommanderUtils.getOffset(),null);
    } catch (HibernateException e){
        throw new ExecDatabaseException(e.getLocalizedMessage());
    }

    return objects;
}
}
