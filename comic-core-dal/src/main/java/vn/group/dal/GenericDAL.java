package vn.group.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAL<ID extends Serializable,T> {
     List<T> findAll();
     void save(T entity);
     void delete(List<T> listEntity);
     T update(T entity);
     T findById(ID id);
     Object[] findByProperty(List<String> joinTables,Map<String,String> properties,Map<String,String> sortProperties,Integer limit, Integer offset, String whereclause);
     T findByPropertyUnique(String property,Object propertyValue);

}
