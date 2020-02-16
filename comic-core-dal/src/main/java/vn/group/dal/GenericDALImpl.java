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
        T result;
        Transaction transaction = session.beginTransaction();
        try {
          T object = (T) session.merge(entity);
            result = object;
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
    public List<T> findByProperty(Map<String,String> properties,Map<String,String> sortProperties,Integer limit,Integer offset,String whereClause){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StringBuilder sqlQuery = new StringBuilder("FROM ").append(persistenceClass.getSimpleName())
                .append(" WHERE 1=1");
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

        List<T> result = new ArrayList<T>();
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
