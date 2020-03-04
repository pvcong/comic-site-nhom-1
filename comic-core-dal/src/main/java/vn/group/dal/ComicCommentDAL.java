package vn.group.dal;

import vn.group.data.ComicCommentEntity;

public interface ComicCommentDAL extends GenericDAL<Integer,ComicCommentEntity> {
    ComicCommentEntity findDetailComicComment(Integer id);

}
