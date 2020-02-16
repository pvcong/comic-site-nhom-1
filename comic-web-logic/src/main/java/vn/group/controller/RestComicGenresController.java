package vn.group.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.group.dto.ComicDTO;
import vn.group.dto.ComicGenresDTO;
import vn.group.service.ComicGenresService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class RestComicGenresController {
    @Autowired
    ComicGenresService comicGenresService;
    @RequestMapping( value = "/comic/genres/{id}", method = RequestMethod.GET)
    public ComicGenresDTO findGenresById(@PathVariable( name = "id") Integer id){
        ComicGenresDTO comicGenresDTO = null;
        try {
            comicGenresDTO =  comicGenresService.findById(id);
        } catch (HibernateException e){

        }

        return comicGenresDTO;
    }

    @RequestMapping( value = "/comic/genres", method = RequestMethod.POST)
    public void saveGenres(@ModelAttribute ComicGenresDTO comicGenresDTO){
        try {
            comicGenresService.save(comicGenresDTO);
        } catch (HibernateException e){

        }
    }
    @RequestMapping( value = "/comic/genres", method = RequestMethod.PUT)
    public void updateGenres(@RequestBody  ComicGenresDTO comicGenresDTO){
        ComicGenresDTO result = null;
        try {
            result = comicGenresService.update(comicGenresDTO);
        } catch (HibernateException e){

        }

    }
    @RequestMapping( value = "/comic/genres", method = RequestMethod.DELETE)
    public void deleteGenres(@RequestBody List<ComicGenresDTO> comicGenresDTOList){
        try {
            comicGenresService.delete(comicGenresDTOList);
        } catch (HibernateException e){

        }

    }
    @RequestMapping( value = "/comic/genres", method = RequestMethod.GET)
    public List<ComicGenresDTO> getAllGenres(){
        List<ComicGenresDTO> comicGenresDTOList = null;
        try {
            comicGenresDTOList = comicGenresService.findAll();
        } catch (HibernateException e){

        }
        return comicGenresDTOList;
    }
    @RequestMapping(value = "/comic/genres?{property}={value}", method = RequestMethod.GET)
    public ComicGenresDTO findByPropertyUnique(
            HttpServletRequest req, HttpServletResponse res,
            @PathVariable(name = "property") String property, @PathVariable( name = "value") String propertyValue){
        ComicGenresDTO comicGenresDTO = null;
        try {
            comicGenresDTO = comicGenresService.findByPropertyUnique(property,propertyValue);
        } catch (HibernateException e) {

        }
        return comicGenresDTO;
    }
}
