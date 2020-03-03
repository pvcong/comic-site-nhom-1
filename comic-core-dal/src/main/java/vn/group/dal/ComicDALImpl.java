package vn.group.dal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import vn.group.data.ComicChapterEntity;
import vn.group.data.ComicEntity;
import vn.group.data.UserEntity;

import java.util.*;

@Repository
public class ComicDALImpl extends GenericDALImpl<Integer, ComicEntity> implements ComicDAL {
    public ComicEntity findDetailComicUnique(Integer id) {
        ComicEntity comicEntity = null;
                Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("FROM ComicEntity comic ")
                    .append(" JOIN FETCH comic.comicGenresEntities JOIN FETCH comic.userEntity JOIN FETCH comic.weekdaysEntities ")
                    .append(" WHERE comic.id = :comicid");

            Query query = session.createQuery(stringBuilder.toString());
            query.setParameter("comicid",id);
            comicEntity = (ComicEntity) query.uniqueResult();

        } catch (HibernateException e){
            transaction.rollback();
            logger.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return comicEntity ;
    }



}
