package vn.group.service;

import vn.group.dto.ComicChapterDTO;

import java.util.List;
import java.util.Map;

public interface ComicChapterService {
    void save(ComicChapterDTO comicChapterDTO);
    void delete(List<ComicChapterDTO> comicChapterDTOList);
    ComicChapterDTO findById(Integer id);
    ComicChapterDTO update(ComicChapterDTO comicChapterDTO);
    List<ComicChapterDTO> findByproperties(Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );

}