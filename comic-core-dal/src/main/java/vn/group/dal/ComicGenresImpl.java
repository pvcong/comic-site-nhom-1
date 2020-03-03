package vn.group.dal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import vn.group.data.ComicGenresEntity;
import vn.group.data.ComicReviewEntity;

@Repository
public class ComicGenresImpl extends GenericDALImpl<Integer, ComicGenresEntity> implements ComicGenresDAL  {
    public ComicGenresEntity findDetailUnique(Integer id) {
        Session session = sessionFactory.openSession();
        ComicGenresEntity comicGenresEntity = null;
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("FROM ComicGenresEntity cc")
                    .append(" JOIN FETCH cc.comicEntities WHERE cc.comicGenresId = :id");
            Query query = session.createQuery(stringBuilder.toString());
            query.setParameter("id",id);
            comicGenresEntity = (ComicGenresEntity) query.uniqueResult();
        } catch (HibernateException e){
            logger.error(e.getMessage(),e);
            throw  e;
        }
        return comicGenresEntity;
    }
}
