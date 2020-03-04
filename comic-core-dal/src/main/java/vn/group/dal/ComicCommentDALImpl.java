package vn.group.dal;

import org.hibernate.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import vn.group.data.ComicCommentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ComicCommentDALImpl extends GenericDALImpl<Integer, ComicCommentEntity> implements ComicCommentDAL{
    public ComicCommentEntity findDetailComicComment(Integer id) {
        Session session = sessionFactory.openSession();
        ComicCommentEntity comicCommentEntity = null;
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("From ComicCommentEntity cc")
                    .append(" JOIN FETCH cc.comicEntity JOIN FETCH cc.userEntity WHERE cc.id = :id");
            Query query = session.createQuery(stringBuilder.toString());
            query.setParameter("id",id);
            comicCommentEntity = (ComicCommentEntity) query.uniqueResult();
        } catch (HibernateException e){
            logger.error(e.getMessage(),e);
            throw  e;
        }
        return comicCommentEntity;
    }

    @Override
    public ComicCommentEntity findById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ComicCommentEntity result = null;
        try {
            result = (ComicCommentEntity) session.get(persistenceClass,id);
            Hibernate.initialize(result.getUserEntity());
            Hibernate.initialize(result.getComicEntity());
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
