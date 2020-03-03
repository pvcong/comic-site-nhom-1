package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.group.common.ServiceConstant;
import vn.group.data.UserEntity;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;
import vn.group.dto.WeekdaysDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.ComicService;
import vn.group.utils.UserUtils;
import vn.group.web.utils.ComicCommanderUtilsImpl;
import vn.group.web.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static sun.misc.Signal.handle;

@RestController
public class RestComicController {
    @Autowired
    ComicService comicService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comics/{id}", method = RequestMethod.GET)
    public ComicDTO getDetailComicById(@PathVariable( name = "id") Integer id){
        ComicDTO comicDTO = null;
        try{
            comicDTO =  comicService.findDetaiComicUntique(id);

        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
        return comicDTO;


    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comics/{id}/chapters/{idchapter}", method = RequestMethod.GET)
    public Object[] getComicChaptersByComicId(
            @PathVariable(name = "id") Integer id, @PathVariable(name = "idchapter") Integer comicChapter, ComicCommanderUtilsImpl comicCommanderUtils ){
        //List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        Object[] objects = null;
        Map<String,String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("ce.comicId",id.toString());
        List<String> joinTable = new ArrayList<String>();
        joinTable.add("cc.comicEntity ce");
        try{
            objects = comicService.
                    findComicChapterOfComic(joinTable,propertiesMap,comicCommanderUtils.getSortPropertiesMap(),1,comicChapter-1,null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

        return objects;
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comics/{id}/chapters", method = RequestMethod.GET)
    public Object[] getComicsChaptersByComic(
            @PathVariable(name = "id") Integer id, ComicCommanderUtilsImpl comicCommanderUtils){
        //List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        Object[] objects = null;
        Map<String,String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("ce.comicId",id.toString());
        List<String> joinTable = new ArrayList<String>();
        joinTable.add("cc.comicEntity ce");
        comicCommanderUtils.setSortProperty("cc.comicChapterId");
        comicCommanderUtils.setSortValue("DESC");
        setupSortAndProperty(comicCommanderUtils);
        try {
            objects = comicService.
                    findComicChapterOfComic(joinTable,propertiesMap,comicCommanderUtils.getSortPropertiesMap(),comicCommanderUtils.getLimit(),comicCommanderUtils.getOffset(),null);

        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());

        }

        return objects;
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comics/{id}/comments", method = RequestMethod.GET)
    public Object[] getComicCommentsByComic(
            @PathVariable(name = "id") Integer id, ComicCommanderUtilsImpl comicCommanderUtils){
        //List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        Object[] objects = null;
        Map<String,String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("ce.comicId",id.toString());
        List<String> joinTable = new ArrayList<String>();
        joinTable.add("cc.comicEntity ce");
        comicCommanderUtils.setSortValue("DESC");
        setupSortAndProperty(comicCommanderUtils);
        try {
            objects = comicService.
                    findCommentsOfComic(joinTable,propertiesMap,comicCommanderUtils.getSortPropertiesMap(),comicCommanderUtils.getLimit(),comicCommanderUtils.getOffset(),null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

        return objects;
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/comics/{id}/reviews", method = RequestMethod.GET)
    public Object[] getComicReviewsByComic(
            @PathVariable(name = "id") Integer id, ComicCommanderUtilsImpl comicCommanderUtils){
        //List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        Object[] objects = null;
        Map<String,String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("ce.comicId",id.toString());
        List<String> joinTable = new ArrayList<String>();
        joinTable.add("cc.comicEntity ce");
        comicCommanderUtils.setSortValue("DESC");
        setupSortAndProperty(comicCommanderUtils);
        try {
            objects = comicService.findReviewOfComic(joinTable,propertiesMap,comicCommanderUtils.getSortPropertiesMap(),comicCommanderUtils.getLimit(),comicCommanderUtils.getOffset(),null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

        return objects;
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/comics", method = RequestMethod.POST)
    public void saveComic(HttpServletRequest req, ComicDTO comicDTO, @RequestParam(required = true) MultipartFile flavatar,@RequestParam(required = true) MultipartFile flbanner,@RequestParam String[] comicGenresId,@RequestParam  String[] weekdaysId){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Set<ComicGenresDTO> comicGenresDTOS = new HashSet<ComicGenresDTO>();
        for(String item : comicGenresId){
            ComicGenresDTO comicGenresDTO = new ComicGenresDTO();
            comicGenresDTO.setComicGenresId(Integer.parseInt(item));
            comicGenresDTOS.add(comicGenresDTO);
        }
        List<WeekdaysDTO> weekdaysDTOS = new ArrayList<WeekdaysDTO>();
        for(String item : weekdaysId){
            WeekdaysDTO weekdaysDTO = new WeekdaysDTO();
            weekdaysDTO.setWeekdaysId(Integer.parseInt(item));
            weekdaysDTOS.add(weekdaysDTO);
        }
        comicDTO.setCreatedDate(timestamp);
        comicDTO.setModifiedDate(timestamp);
        comicDTO.setWeekdaysEntities(weekdaysDTOS);
        comicDTO.setComicGenresEntities(comicGenresDTOS);
        try{
            Object[] objects = UploadUtils.uploadFile(new MultipartFile[]{flavatar,flbanner}, ServiceConstant.locationComicBanner,req.getRealPath(""));
            if((Boolean)objects[0] == false){
                String[] pathFiles =  objects[2].toString().split(",");
                comicDTO.setAvatar(pathFiles[0]);
                comicDTO.setBanner(pathFiles[1]);
                comicService.save(comicDTO);

            }
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/comics-update", method = RequestMethod.POST)
    public void updateComic( HttpServletRequest req,String txtCreatedDate, @ModelAttribute  ComicDTO comicDTO, MultipartFile flavatar, MultipartFile flbanner, Integer[] comicGenresId, Integer[] weekdaysId){
        ComicDTO result = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Set<ComicGenresDTO> comicGenresDTOS = new HashSet<ComicGenresDTO>();
        for(Integer item : comicGenresId){
            ComicGenresDTO comicGenresDTO = new ComicGenresDTO();
            comicGenresDTO.setComicGenresId(item);
            comicGenresDTOS.add(comicGenresDTO);
        }
        List<WeekdaysDTO> weekdaysDTOS = new ArrayList<WeekdaysDTO>();
        for(Integer item : weekdaysId){
            WeekdaysDTO weekdaysDTO = new WeekdaysDTO();
            weekdaysDTO.setWeekdaysId(item);
            weekdaysDTOS.add(weekdaysDTO);
        }
        UserEntity userEntity = UserUtils.DTO2Entity(comicDTO.getUserDTO());
        comicDTO.setCreatedDate(new Timestamp(Long.parseLong(txtCreatedDate)));
        comicDTO.setModifiedDate(timestamp);
        comicDTO.setComicGenresEntities(comicGenresDTOS);
        comicDTO.setWeekdaysEntities(weekdaysDTOS);
        try {
            if(flavatar.isEmpty()){
                ComicDTO comicDTO1 = comicService.findById(comicDTO.getComicId());
                comicDTO.setAvatar(comicDTO1.getAvatar());
            }else{
                Object[] objects = UploadUtils.uploadFile(new MultipartFile[]{flavatar}, ServiceConstant.locationComicBanner,req.getRealPath(""));
                comicDTO.setAvatar(objects[2].toString().substring(0,objects[2].toString().length() -1));
            }
            if(flbanner.isEmpty()){
                ComicDTO comicDTO1 = comicService.findById(comicDTO.getComicId());
                comicDTO.setBanner(comicDTO1.getBanner());
            }
            else{
                Object[] objects = UploadUtils.uploadFile(new MultipartFile[]{flbanner}, ServiceConstant.locationComicBanner,req.getRealPath(""));
                comicDTO.setBanner(objects[2].toString().substring(0,objects[2].toString().length() -1));
            }
            result = comicService.update(comicDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/comics", method = RequestMethod.DELETE)
    public void deleteComic(@RequestBody  List<ComicDTO> comicDTO){
        try {
            comicService.delete(comicDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping (value = "comics", method = RequestMethod.GET)
    public Object[] getComics(@ModelAttribute ComicCommanderUtilsImpl comicCommanderUtils){
        Object[] objects = null;
        List<String> joinTables = new ArrayList<String>();
        joinTables.add("cc.comicGenresEntities cge");
        comicCommanderUtils.setJoinTables(joinTables);
        setupSortAndProperty(comicCommanderUtils);
        try {
            objects =  comicService.findByproperties
                    (comicCommanderUtils.getJoinTables()
                            ,comicCommanderUtils.getProperties(),
                            comicCommanderUtils.getSortPropertiesMap(),
                            comicCommanderUtils.getLimit(), comicCommanderUtils.getOffset()
                            ,null);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

        return objects;
    }

    private void setupSortAndProperty(ComicCommanderUtilsImpl comicCommanderUtils) {
        if(comicCommanderUtils.getObjectDTO() != null){
            ComicDTO comicDTO = (ComicDTO)comicCommanderUtils.getObjectDTO();
            Map<String,String> properties = new HashMap<String, String>();
            if(comicDTO.getAuthor() != null){
                properties.put("cc.author",comicDTO.getAuthor());
            }
            if(comicDTO.getName() != null){
                properties.put("cc.name", comicDTO.getName());

            }
            if(comicDTO.getComicId() != null){
                properties.put("cc.comicId", comicDTO.getComicId().toString());
            }
            if(comicDTO.getCreatedDate() != null){
                properties.put("cc.createddate", comicDTO.getCreatedDate().toString());
            }
            if(comicDTO.getDescription() != null){
                properties.put("cc.description", comicDTO.getDescription());
            }
            if(comicDTO.getModifiedDate() != null){
                properties.put("cc.modifieddate", comicDTO.getModifiedDate().toString());
            }
            if(comicDTO.getStatus() != null){
                properties.put("cc.status", comicDTO.getStatus());
            }
            comicCommanderUtils.setProperties(properties);

        }
        if(comicCommanderUtils.getSortProperty() != null && comicCommanderUtils.getSortProperty() != null){
            Map<String,String> sortPropertiesMap = new HashMap<String, String>();
            if(comicCommanderUtils.getSortProperty().equals("view")) {
                comicCommanderUtils.setSortProperty("cc.viewTotal");
                sortPropertiesMap.put(comicCommanderUtils.getSortProperty(), comicCommanderUtils.getSortValue());
                comicCommanderUtils.setSortPropertiesMap(sortPropertiesMap);
            }
        }
    }


}
