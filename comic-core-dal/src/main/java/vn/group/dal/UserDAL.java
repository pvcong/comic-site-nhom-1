package vn.group.dal;


import vn.group.data.UserEntity;

public interface UserDAL extends GenericDAL<Integer , UserEntity> {
    Object[] findUserNameAndPassword(String username, String password);
}
