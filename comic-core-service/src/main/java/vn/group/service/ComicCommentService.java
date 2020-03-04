package vn.group.service;

import vn.group.dto.ComicCommentDTO;

import java.util.List;
import java.util.Map;

public interface ComicCommentService {
    void save(ComicCommentDTO comicCommentDTO);
    void delete(List<ComicCommentDTO> comicCommentDTOS);
    ComicCommentDTO update(ComicCommentDTO comicCommentDTO);
    Object[] findByProperties(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String where);
    ComicCommentDTO findByPropertiesUnique(String property,String value);
    ComicCommentDTO findById(Integer id);
}
