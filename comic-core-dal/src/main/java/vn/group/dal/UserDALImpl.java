package vn.group.dal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import vn.group.data.UserEntity;

import java.util.List;
import java.util.Map;

@Repository
public class UserDALImpl extends GenericDALImpl<Integer, UserEntity> implements UserDAL {

    public Object[] findUserNameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity userEntity = null;
        String role = "";
        Integer id = null;
        Boolean isExits = true;
        try {
            StringBuilder stringQuery = new StringBuilder("FROM ")
                    .append(persistenceClass.getSimpleName()).append(" WHERE username = :name")
                    .append(" AND password = :password");
            Query query = session.createQuery(stringQuery.toString());
            query.setParameter("name",username);
            query.setParameter("password",password);
            userEntity = (UserEntity) query.uniqueResult();
        } catch (HibernateException e){
            logger.error(e.getMessage(),e);
            throw  e;
        }
        if (userEntity == null){
            isExits = false;
        }
        else {
            id = userEntity.getUserId();
            role =userEntity.getRole();
        }
        return new Object[]{isExits,role,id};
    }
}
