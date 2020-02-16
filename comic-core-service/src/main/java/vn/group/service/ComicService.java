package vn.group.service;

import vn.group.dto.ComicDTO;
import vn.group.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface ComicService {
    List<ComicDTO> findAll() ;
    void save(ComicDTO comicDTO);
    ComicDTO update(ComicDTO comicDTO);
    void delete(List<ComicDTO> comicDTOS);
    ComicDTO findById(Integer id);
    List<ComicDTO> findByproperties(Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
    ComicDTO findByPropertyUnique(String property, Object propertyValue);
    ComicDTO findDetaiComicUntique(Integer id);
}
