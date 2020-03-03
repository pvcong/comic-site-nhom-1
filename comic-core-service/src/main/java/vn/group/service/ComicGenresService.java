package vn.group.service;

import vn.group.dto.ComicGenresDTO;

import java.util.List;
import java.util.Map;

public interface ComicGenresService {
    List<ComicGenresDTO> findAll() ;
    void save(ComicGenresDTO comicGenresDTO);
    ComicGenresDTO update(ComicGenresDTO comicGenresDTO);
    void delete(List<ComicGenresDTO> comicGenresDTOS);
    ComicGenresDTO findById(Integer id);
    Object[] findByproperties(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
    ComicGenresDTO findByPropertyUnique(String property, Object propertyValue);
    Object[] findComicsOfGenres(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
}
