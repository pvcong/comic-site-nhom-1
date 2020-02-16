package vn.group.service;

import org.springframework.web.multipart.MultipartFile;
import vn.group.dto.ComicChapterDTO;

import java.util.List;
import java.util.Map;

public interface ComicChapterService {
    void save(ComicChapterDTO comicChapterDTO, MultipartFile[] multipartFiles);
    void delete(List<ComicChapterDTO> comicChapterDTOList);
    ComicChapterDTO findById(Integer id);
    ComicChapterDTO update(ComicChapterDTO comicChapterDTO,MultipartFile[] multipartFiles);
    List<ComicChapterDTO> findByproperties(Map<String,String> properties, Map<String,String> sortProperties, Integer limit, Integer offset, String whereClause );

}
