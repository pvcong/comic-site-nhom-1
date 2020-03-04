package vn.group.dal;

import vn.group.data.ComicReviewEntity;

public interface ComicReviewDAL extends GenericDAL<Integer, ComicReviewEntity> {
    ComicReviewEntity findDetailComicReview(Integer id);
}
