package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUser();

    /**
     * 当有多个参数的时候使用@Param("paramName")注解标识，不然会报错
     * 例如：
     * List<User> queryByMultipleCondition(@Param("id"),@Param("time")
     */
}
