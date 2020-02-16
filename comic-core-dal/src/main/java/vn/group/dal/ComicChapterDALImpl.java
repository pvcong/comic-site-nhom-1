package vn.group.dal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import vn.group.data.ComicChapterEntity;
@Repository
public class ComicChapterDALImpl extends GenericDALImpl<Integer, ComicChapterEntity> implements ComicChapterDAL {
    @Override
    public ComicChapterEntity findById(Integer integer) {
        Session session = sessionFactory.openSession();
        ComicChapterEntity comicChapterEntity = null;
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("FROM ComicChapterEntity cc")
                    .append(" JOIN FETCH cc.comicEntity comic WHERE cc.id = :id");
            Query query = session.createQuery(stringBuilder.toString());
            query.setParameter("id",integer);
            comicChapterEntity = (ComicChapterEntity) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e){
            logger.error(e.getMessage(),e);
            transaction.rollback();
            throw  e;
        } finally {
            session.close();
        }
        return comicChapterEntity;
    }
}
