package vn.group.dal;

import org.springframework.stereotype.Repository;
import vn.group.data.UserEntity;

@Repository
public class UserDALImpl extends GenericDALImpl<Integer, UserEntity> implements UserDAL {
}
