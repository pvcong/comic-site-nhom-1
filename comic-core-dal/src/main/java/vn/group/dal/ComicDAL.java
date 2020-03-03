package vn.group.dal;

import vn.group.data.ComicEntity;

import java.util.List;
import java.util.Map;

public interface ComicDAL extends GenericDAL<Integer, ComicEntity> {
    public ComicEntity findDetailComicUnique(Integer id);
}
