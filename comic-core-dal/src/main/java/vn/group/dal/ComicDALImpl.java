package vn.group.dal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import vn.group.data.ComicEntity;
import vn.group.data.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ComicDALImpl extends GenericDALImpl<Integer, ComicEntity> implements ComicDAL {
    public ComicEntity findDetailComicUnique(Integer id) {
        ComicEntity comicEntity = null;
                Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("FROM ComicEntity comic ")
                    .append(" JOIN FETCH comic.comicGenresEntities ")
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

    @Override
    public List<ComicEntity> findByProperty(Map<String, String> properties, Map<String, String> sortProperties, Integer limit, Integer offset, String whereClause) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StringBuilder sqlQuery = new StringBuilder("FROM ").append(persistenceClass.getSimpleName())
                .append(" cc JOIN FETCH cc.comicGenresEntities WHERE 1=1");
        if(properties != null){
            for(Map.Entry<String,String> item : properties.entrySet()){
                sqlQuery.append(" AND LOWER(").append(item.getKey()).append(") LIKE '%' || :").append(item.getKey()).append(" || '%' ");
            }

        }
        if(whereClause != null && !StringUtils.isEmpty(whereClause)){
            sqlQuery.append(" AND ").append(whereClause);
        }
        if(sortProperties != null){
            int i = 0;
            for(Map.Entry<String,String> item : sortProperties.entrySet()){
                if(i == 0){
                    sqlQuery.append(" order by ").append(item.getKey()).append(" ").append(item.getValue());
                }
                else {
                    sqlQuery.append(", ").append(item.getKey()).append(" ").append(item.getValue());
                }
                i++;
            }
        }

        Query query = session.createQuery(sqlQuery.toString());
        if(limit != null){
            query.setMaxResults(limit);
        }
        if(offset !=null){
            query.setFirstResult(offset);
        }
        if(properties !=null){
            for(Map.Entry<String,String> item : properties.entrySet()){
                query.setParameter(item.getKey(),item.getValue());
            }
        }
//        if(sortProperties != null){
//            for(Map.Entry<String,String> item : sortProperties.entrySet()){
//                query.setParameter(item.getKey(),item.getKey());
//            }
//        }

        List<ComicEntity> result = new ArrayList<ComicEntity>();
        try {
            result = query.list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            logger.error(e);
            throw e;
        }
        finally {
            session.close();
        }

        return result;
    }
}
