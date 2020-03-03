package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.group.common.ServiceConstant;
import vn.group.dto.ComicChapterDTO;
import vn.group.exception.ExecDatabaseException;
import vn.group.exception.NotFoundObjectException;
import vn.group.service.ComicChapterService;
import vn.group.web.utils.ComicChapterCommanderUtilsImpl;
import vn.group.web.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
public class RestComicChapterController {
    @Autowired
    ComicChapterService comicChapterService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/chapters/{id}", method = RequestMethod.GET)
    public ComicChapterDTO findById(@PathVariable( name = "id") Integer id){
        ComicChapterDTO comicChapterDTO = null;
        try {
            comicChapterDTO =  comicChapterService.findById(id);
        } catch (HibernateException e){

        }
        if(comicChapterDTO != null && comicChapterDTO.getComicChapterId()!= null)
        return comicChapterDTO;
        else {
            throw new NotFoundObjectException("ID" + id);
        }
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/chapters", method = RequestMethod.POST)
    public void saveComicChapter(HttpServletRequest req, @ModelAttribute ComicChapterDTO comicChapterDTO, @RequestParam( name = "files")MultipartFile[] flImage){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicChapterDTO.setCreatedDate(timestamp);
            Object[] objects = new Object[0];
            try {
                objects = UploadUtils.uploadFile(flImage, ServiceConstant.locationComicChapterImage,req.getRealPath(""));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if((Boolean)objects[0] == false){
                    comicChapterDTO.setImages(objects[2].toString());
                comicChapterService.save(comicChapterDTO);

            }
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping( value = "/chapters-update", method = RequestMethod.POST)
    public void updateComicChapter(HttpServletRequest req,
                                   @ModelAttribute  ComicChapterDTO comicChapterDTO,
                                   @RequestParam( name = "image",required = true)MultipartFile[] flImage,String txtCreatedDate){
        ComicChapterDTO result = null;
        try {
            if(flImage.length > 0){
                Object[] objects = UploadUtils.uploadFile(flImage, ServiceConstant.locationComicChapterImage,req.getRealPath(""));
                comicChapterDTO.setImages(objects[2].toString());
            }
            else{
                ComicChapterDTO comicChapterDTO1 = comicChapterService.findById(comicChapterDTO.getComicChapterId());
                comicChapterDTO.setImages(comicChapterDTO1.getImages());
            }

            Timestamp timestamp = new Timestamp(Long.parseLong(txtCreatedDate));
            comicChapterDTO.setCreatedDate(timestamp);
            comicChapterService.update(comicChapterDTO);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/chapters", method = RequestMethod.DELETE)
    public void deleteComicChapter(@RequestBody List<ComicChapterDTO> comicChapterDTOList){
        try {
            comicChapterService.delete(comicChapterDTOList);
        } catch (HibernateException e){
            throw new ExecDatabaseException(e.getLocalizedMessage());
        }

    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping( value = "/chapters", method = RequestMethod.GET)
    public Object[] getComicChapters(@ModelAttribute ComicChapterCommanderUtilsImpl commanderUtils ){
        //List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        Object[] objects = null;
        try {
            setUpPropertiesAndSort(commanderUtils);
            objects = comicChapterService.
                    findByproperties(null,commanderUtils.getProperties(),
                            commanderUtils.getSortPropertiesMap(),commanderUtils.getLimit(),commanderUtils.getOffset(),null);

        } catch (HibernateException e){

        }
        return objects;
    }

    private void setUpPropertiesAndSort(ComicChapterCommanderUtilsImpl commanderUtils) {
        if(commanderUtils.getObjectDTO() != null){
            ComicChapterDTO comicChapterDTO = commanderUtils.getObjectDTO();
            Map<String,String> propertiesMap = new HashMap<String, String>();
            if(comicChapterDTO.getComicChapterId() != null){
                propertiesMap.put("comicChapterId", comicChapterDTO.getComicChapterId().toString());
            }
            if(comicChapterDTO.getName() != null){
                propertiesMap.put("name", comicChapterDTO.getName());
            }
            if(comicChapterDTO.getCreatedDate() != null){
                propertiesMap.put("createddate", comicChapterDTO.getCreatedDate().toString());
            }
            commanderUtils.setProperties(propertiesMap);
        }
        if(commanderUtils.getSortProperty() != null && commanderUtils.getSortValue() != null){
            Map<String,String> sortMap = new HashMap<String, String>();
            sortMap.put(commanderUtils.getSortProperty(),commanderUtils.getSortValue());
            commanderUtils.setSortPropertiesMap(sortMap);
        }
    }
}
