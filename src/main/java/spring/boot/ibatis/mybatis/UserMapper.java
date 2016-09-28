package spring.boot.ibatis.mybatis;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import spring.boot.ibatis.User;

import java.util.List;

/**
 * Created by chokst on 12/2/14.
 */
public interface UserMapper {
    List<User> getAllUsers();

    @Select("SELECT * FROM USERINFO WHERE ID = #{id}")
    @ResultMap("userResultMap")
    User getUser(int id);
}
