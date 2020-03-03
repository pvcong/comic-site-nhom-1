package vn.group.service;

import org.springframework.web.multipart.MultipartFile;
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
    Object[] findByproperties(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );
    ComicDTO findByPropertyUnique(String property, Object propertyValue);
    ComicDTO findDetaiComicUntique(Integer id);
    Object[] findComicChapterOfComic(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause);

    Object[] findCommentsOfComic(List<String> joinTable, Map<String, String> propertiesMap, Map<String, String> sortPropertiesMap, Integer limit, int offset, String whereClause);

    Object[] findReviewOfComic(List<String> joinTable, Map<String, String> propertiesMap, Map<String, String> sortPropertiesMap, Integer limit, int offset, String whereClause);
}
