package vn.group.service;

import vn.group.dto.ComicCommentDTO;
import vn.group.dto.ComicReviewDTO;

import java.util.List;
import java.util.Map;

public interface ComicReviewService {
    void save(ComicReviewDTO comicReviewDTO);
    void delete(List<ComicReviewDTO> comicReviewDTOS);
    ComicReviewDTO update(ComicReviewDTO comicReviewDTO);
    Object[] findByProperties(List<String> joinTables,Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String where);
    ComicReviewDTO findByPropertiesUnique(String property,String value);
    ComicReviewDTO findByid(Integer id);
}
