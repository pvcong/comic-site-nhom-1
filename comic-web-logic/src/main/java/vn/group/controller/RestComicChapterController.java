package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.ComicChapterDTO;
import vn.group.dto.ComicDTO;
import vn.group.service.ComicChapterService;

import java.util.List;

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
    public void saveComicChapter(@ModelAttribute ComicChapterDTO comicChapterDTO){
        try {
            comicChapterService.save(comicChapterDTO);
        } catch (HibernateException e){

        }
    }
    @RequestMapping( value = "/comic/chapter", method = RequestMethod.PUT)
    public void updateComicChapter(@RequestBody  ComicChapterDTO comicChapterDTO){
        ComicChapterDTO result = null;
        try {
            result = comicChapterService.update(comicChapterDTO);
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
//    @RequestMapping( value = "/comic/chapter", method = RequestMethod.GET)
//    public List<ComicChapterDTO> getAllComicChapter(){
//        List<ComicChapterDTO> comicChapterDTOList = null;
//        try {
//            comicChapterDTOList = comicChapterService.findAll();
//        } catch (HibernateException e){
//
//        }
//        return comicDTOList;
//    }
}
