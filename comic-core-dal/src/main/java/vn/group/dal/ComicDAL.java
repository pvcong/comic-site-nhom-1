package vn.group.dal;

import vn.group.data.ComicEntity;

public interface ComicDAL extends GenericDAL<Integer, ComicEntity> {
    public ComicEntity findDetailComicUnique(Integer id);
}
