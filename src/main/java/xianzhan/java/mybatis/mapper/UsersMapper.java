package xianzhan.java.mybatis.mapper;

import xianzhan.java.mybatis.entity.Users;

/**
 * @author xianzhan
 */
public interface UsersMapper {

    Users selectUser(Long id);
}
