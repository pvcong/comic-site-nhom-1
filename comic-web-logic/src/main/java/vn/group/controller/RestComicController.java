package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.group.data.UserEntity;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;
import vn.group.dto.UserDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.ComicService;
import vn.group.utils.UserUtils;
import vn.learn.web.utils.ComicCommanderUtils;
import vn.learn.web.utils.ComicCommanderUtilsImpl;
import vn.learn.web.utils.ComicGenresCommanderUtils;
import vn.learn.web.utils.ComicGenresCommanderUtilsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@RestController
public class RestComicController {
    @Autowired
    ComicService comicService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comic/{id}", method = RequestMethod.GET)
    public ComicDTO getDetailComicById(@PathVariable( name = "id") Integer id){
        ComicDTO comicDTO = null;
        try {
            comicDTO =  comicService.findDetaiComicUntique(id);
        } catch (HibernateException e){

        }
        if(comicDTO!= null && comicDTO.getComicId()!= null) {
            return comicDTO;
        }
         else {
            throw new NotFoundObjectException(id);
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/comic", method = RequestMethod.POST)
    public void saveComic(HttpServletRequest req,@ModelAttribute ComicDTO comicDTO, @RequestParam MultipartFile[] file, String[] comicGenresId){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Set<ComicGenresDTO> comicGenresDTOS = new HashSet<ComicGenresDTO>();
            for(String item : comicGenresId){
                ComicGenresDTO comicGenresDTO = new ComicGenresDTO();
                comicGenresDTO.setComicGenresId(Integer.parseInt(item));
                comicGenresDTOS.add(comicGenresDTO);
            }
            comicDTO.setCreatedDate(timestamp);
            comicDTO.setModifiedDate(timestamp);
            comicDTO.setComicGenresEntities(comicGenresDTOS);
            comicService.save(comicDTO,file,req.getRealPath(""));
            } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
            }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/comic-update", method = RequestMethod.POST)
    public void updateComic(HttpServletRequest req,@ModelAttribute  ComicDTO comicDTO, @RequestParam MultipartFile[] file, String[] comicGenresId){
        ComicDTO result = null;
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Set<ComicGenresDTO> comicGenresDTOS = new HashSet<ComicGenresDTO>();
            for(String item : comicGenresId){
                ComicGenresDTO comicGenresDTO = new ComicGenresDTO();
                comicGenresDTO.setComicGenresId(Integer.parseInt(item));
                comicGenresDTOS.add(comicGenresDTO);
            }
            UserEntity userEntity = UserUtils.DTO2Entity(comicDTO.getUserDTO());
            comicDTO.setModifiedDate(timestamp);
            comicDTO.setComicGenresEntities(comicGenresDTOS);
            result = comicService.update(comicDTO, file,req.getRealPath("w"));
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/comic", method = RequestMethod.DELETE)
    public void deleteComic(@RequestBody  List<ComicDTO> comicDTO){
        try {
            comicService.delete(comicDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
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
//    @RequestMapping(value = "/comic/ch?{property}={value}", method = RequestMethod.GET)
//    public ComicDTO findByPropertyUnique(
//            HttpServletRequest req, HttpServletResponse res,
//            @PathVariable(name = "property") String property, @PathVariable( name = "value") String propertyValue){
//        ComicDTO comicDTO = null;
//        try {
//            comicDTO = comicService.findByPropertyUnique(property,propertyValue);
//
//        } catch (HibernateException e) {
//
//        }
//        return comicDTO;
//    }
@ResponseStatus(code = HttpStatus.OK)
    @RequestMapping (value = "comic", method = RequestMethod.GET)
    public List<ComicDTO> getComics(@ModelAttribute ComicCommanderUtilsImpl comicCommanderUtils){
        List<ComicDTO> comicDTOList = new ArrayList<ComicDTO>();
        try {
            setupSortAndProperty(comicCommanderUtils);
            comicDTOList =  comicService.findByproperties
                    (comicCommanderUtils.getProperties(),
                            comicCommanderUtils.getSortPropertiesMap(), comicCommanderUtils.getLimit(),
                            comicCommanderUtils.getOffset(),null);
        } catch (HibernateException e){

        }
        return comicDTOList;
    }

    private void setupSortAndProperty(ComicCommanderUtilsImpl comicCommanderUtils) {
        if(comicCommanderUtils.getObjectDTO() != null){
            ComicDTO comicDTO = (ComicDTO)comicCommanderUtils.getObjectDTO();
            Map<String,String> properties = new HashMap<String, String>();
            if(comicDTO.getAuthor() != null){
                properties.put("author",comicDTO.getAuthor());
            }
            if(comicDTO.getName() != null){
                properties.put("name", comicDTO.getName());

            }
            if(comicDTO.getComicId() != null){
                properties.put("id", comicDTO.getComicId().toString());
            }
            if(comicDTO.getCreatedDate() != null){
                properties.put("createddate", comicDTO.getCreatedDate().toString());
            }
            if(comicDTO.getDescription() != null){
                properties.put("description", comicDTO.getDescription());
            }
            if(comicDTO.getModifiedDate() != null){
                properties.put("modifieddate", comicDTO.getModifiedDate().toString());
            }
            if(comicDTO.getStatus() != null){
                properties.put("status", comicDTO.getStatus());
            }
            comicCommanderUtils.setProperties(properties);

        }
        if(comicCommanderUtils.getSortProperty() != null && comicCommanderUtils.getSortProperty() != null){
            Map<String,String> sortPropertiesMap = new HashMap<String, String>();
            sortPropertiesMap.put(comicCommanderUtils.getSortProperty(), comicCommanderUtils.getSortValue());
            comicCommanderUtils.setSortPropertiesMap(sortPropertiesMap);
        }
    }


}
