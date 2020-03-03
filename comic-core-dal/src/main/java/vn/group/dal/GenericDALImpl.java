package vn.group.dal;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.loader.plan.spi.QuerySpaceUidNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.util.NestedServletException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class GenericDALImpl<ID extends Serializable, T> implements GenericDAL<ID , T> {
    Class<T> persistenceClass;
    Logger logger = Logger.getLogger(GenericDALImpl.class);
    public GenericDALImpl()
    {
        this.persistenceClass=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    @Autowired
    SessionFactory sessionFactory;
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder("from ");
            stringBuilder.append(persistenceClass.getSimpleName());
            Query query = session.createQuery(stringBuilder.toString());
            list = query.list();

            transaction.commit();

        } catch (HibernateException e){
            transaction.rollback();
            logger.error(e);
            throw e;
        }
        finally {
            session.close();
        }
        return list;
    }

    public void save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch(HibernateException e){
            transaction.rollback();
            logger.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
    }

    public void delete(List<T> listEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(T item : listEntity){
                session.delete(item);

            }
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            logger.error(e);
            throw e;
        }
        finally {
            session.close();
        }
    }

    public T update(T entity) {
        Session session = sessionFactory.openSession();
        T result = null;
        Transaction transaction = session.beginTransaction();
        try {
          session.update(entity);
            //result = object;
            transaction.commit();
        } catch(HibernateException e){
            transaction.rollback();
            logger.error(e);
            throw  e;
        } finally {
            session.close();
        }
        return result;
    }

    public T findById(ID id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
           T object = (T) session.get(persistenceClass,id);
           result  = object;
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
    public Object[] findByProperty(List<String> joinTables,Map<String,String> properties,Map<String,String> sortProperties,Integer limit,Integer offset,String whereClause){
        Session session = sessionFactory.openSession();
       Transaction transaction = session.beginTransaction();
        StringBuilder sqlQuery = new StringBuilder("FROM ").append(persistenceClass.getSimpleName());
        if(joinTables != null){
            sqlQuery.append(" cc");
            for(String item : joinTables){
                sqlQuery.append(" LEFT JOIN FETCH ").append(item);
            }
        }
        sqlQuery.append(" WHERE 1=1");
        if(properties != null){
            for(Map.Entry<String,String> item : properties.entrySet()){
                sqlQuery.append(" AND LOWER(").append(item.getKey()).append(")= LOWER(:").append(item.getKey().replace(".","1")).append(") ");
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
        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(*) FROM ").append(persistenceClass.getSimpleName());
        if(joinTables != null){
            stringQueryCount.append(" cc");
            for(String item : joinTables){
                stringQueryCount.append(" JOIN ").append(item);
            }
        }
        stringQueryCount.append(" WHERE 1=1");
        if(properties != null){
            for(Map.Entry<String,String> item : properties.entrySet()){
                stringQueryCount.append(" AND LOWER(").append(item.getKey()).append(")= LOWER(:").append(item.getKey().replace(".","1")).append(") ");
             }
        }
        Query query2 = session.createQuery(stringQueryCount.toString());
        if(properties !=null){
            for(Map.Entry<String,String> item : properties.entrySet()){
                query.setParameter(item.getKey().replace(".","1"),item.getValue());
                query2.setParameter(item.getKey().replace(".","1"),item.getValue());
            }
        }
//        if(sortProperties != null){
//            for(Map.Entry<String,String> item : sortProperties.entrySet()){
//                query.setParameter(item.getKey().replace(""),item.getKey());
//            }
//        }



        Object countSize = 0;
        List<T> result = new ArrayList<T>();
        try {
            countSize = query2.list().get(0).toString();
            if(limit != 0)
            countSize = String.format("%.0f",Math.ceil(Double.parseDouble(countSize.toString())/limit));

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

        return new Object[]{countSize,result};
    }

    public T findByPropertyUnique(String property, Object propertyValue) {
        StringBuilder sqlQuery = new StringBuilder("FROM ").append(persistenceClass.getSimpleName())
                .append(" WHERE 1=1");

        if(!StringUtils.isEmpty(property) && !StringUtils.isEmpty(propertyValue)){
            sqlQuery.append(" AND ").append(property).append(" = ").append(" :propertyValue");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            Query query = session.createQuery(sqlQuery.toString());

            query.setParameter("propertyValue",propertyValue);
            result = (T) query.uniqueResult();
            transaction.commit();
         } catch (HibernateException e){
            transaction.rollback();
            logger.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

}
