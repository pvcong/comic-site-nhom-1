package vn.group.dal;

import org.hibernate.*;
import org.springframework.stereotype.Repository;
import sun.security.ssl.HandshakeInStream;
import vn.group.data.ComicReviewEntity;
@Repository
public class ComicReviewDALImpl extends GenericDALImpl<Integer, ComicReviewEntity> implements ComicReviewDAL {
    public ComicReviewEntity findDetailComicReview(Integer id) {
        Session session = sessionFactory.openSession();
        ComicReviewEntity comicReviewEntity = null;
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("From ComicReviewEntity cc")
                    .append(" JOIN FETCH cc.comicEntity JOIN FETCH cc.userEntity WHERE cc.id = :id");
            Query query = session.createQuery(stringBuilder.toString());
            query.setParameter("id",id);
            comicReviewEntity = (ComicReviewEntity) query.uniqueResult();
        } catch (HibernateException e){
            logger.error(e.getMessage(),e);
            throw  e;
        }
        return comicReviewEntity;

    }

    @Override
    public ComicReviewEntity findById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ComicReviewEntity result = null;
        try {
            result = (ComicReviewEntity) session.get(persistenceClass,id);
            Hibernate.initialize(result.getComicEntity());
            Hibernate.initialize(result.getUserEntity());
            transaction.commit();

        }
        catch (HibernateException e){
            transaction.rollback();
            logger.error(e);
            throw  e;
        }
        finally {
            session.close();
        }
        return result;
    }
}
