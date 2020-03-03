package vn.group.service;

import org.springframework.web.multipart.MultipartFile;
import vn.group.dto.ComicChapterDTO;

import java.util.List;
import java.util.Map;

public interface ComicChapterService {
    void save(ComicChapterDTO comicChapterDTO);
    void delete(List<ComicChapterDTO> comicChapterDTOList);
    ComicChapterDTO findById(Integer id);
    ComicChapterDTO update(ComicChapterDTO comicChapterDTO);
    Object[] findByproperties(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );

}
