package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.group.dto.ComicChapterDTO;
import vn.group.dto.ComicDTO;
import vn.group.service.ComicChapterService;
import vn.learn.web.utils.ComicChapterCommanderUtils;
import vn.learn.web.utils.ComicChapterCommanderUtilsImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestComicChapterController {
    @Autowired
    ComicChapterService comicChapterService;

    @RequestMapping( value = "/comic/chapter/{id}", method = RequestMethod.GET)
    public ComicChapterDTO findById(@PathVariable( name = "id") Integer id){
        ComicChapterDTO comicChapterDTO = null;
        try {
            comicChapterDTO =  comicChapterService.findById(id);
        } catch (HibernateException e){

        }

        return comicChapterDTO;
    }
    @RequestMapping( value = "/comic/chapter", method = RequestMethod.POST)
    public void saveComicChapter(@ModelAttribute ComicChapterDTO comicChapterDTO, @RequestParam( name = "files")MultipartFile[] multipartFiles){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicChapterDTO.setCreatedDate(timestamp);
            comicChapterService.save(comicChapterDTO,multipartFiles);
        } catch (HibernateException e){

        }
    }
    @RequestMapping( value = "/comic/chapter-update", method = RequestMethod.POST)
    public void updateComicChapter(@ModelAttribute  ComicChapterDTO comicChapterDTO,@RequestParam( name = "files")MultipartFile[] multipartFiles){
        ComicChapterDTO result = null;
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comicChapterDTO.setCreatedDate(timestamp);
            result = comicChapterService.update(comicChapterDTO,multipartFiles);
        } catch (HibernateException e){

        }

    }
    @RequestMapping( value = "/comic/chapter", method = RequestMethod.DELETE)
    public void deleteComicChapter(@RequestBody List<ComicChapterDTO> comicChapterDTOList){
        try {
            comicChapterService.delete(comicChapterDTOList);
        } catch (HibernateException e){

        }

    }
    @RequestMapping( value = "/comic/chapter", method = RequestMethod.GET)
    public List<ComicChapterDTO> getComicChapters(@ModelAttribute ComicChapterCommanderUtilsImpl commanderUtils ){
        List<ComicChapterDTO> comicChapterDTOList = new ArrayList<ComicChapterDTO>();
        try {
            setUpPropertiesAndSort(commanderUtils);
            comicChapterDTOList = comicChapterService.
                    findByproperties(commanderUtils.getProperties(),
                            commanderUtils.getSortPropertiesMap(),commanderUtils.getLimit(),commanderUtils.getOffset(),null);

        } catch (HibernateException e){

        }
        return comicChapterDTOList;
    }

    private void setUpPropertiesAndSort(ComicChapterCommanderUtilsImpl commanderUtils) {
        if(commanderUtils.getObjectDTO() != null){
            ComicChapterDTO comicChapterDTO = commanderUtils.getObjectDTO();
            Map<String,String> propertiesMap = new HashMap<String, String>();
            if(comicChapterDTO.getComicChapterId() != null){
                propertiesMap.put("id", comicChapterDTO.getComicChapterId().toString());
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
