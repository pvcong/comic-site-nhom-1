package vn.group.dal;

import org.springframework.stereotype.Repository;
import vn.group.data.UserEntity;

import java.util.List;
import java.util.Map;

@Repository
public class UserDALImpl extends GenericDALImpl<Integer, UserEntity> implements UserDAL {

}
