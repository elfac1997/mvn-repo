package com.dao;

import com.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface UserDao {
    int addUser(User user);

    int updateUser(User user);

    int registerUser(User user);

    int deleteUserById(int id);

    User queryUserById(int id);

    List<User> queryAllUsers();

    /**
     *  通过用户名查找用户
     *  @param username
     *  @return User
     */
    public User getByUserName(String username);

    /**
     *  通过用户名查找该用户所有的角色并保存在Set集合中
     *  @param username
     *  @return Set<String>
     */
    public Set<String> getRoles(String username);

    /**
     *  通过用户名查找该用户所有的权限并保存在Set集合中
     *  @param username
     *  @return Set<String>
     */
    public Set<String> getPermissions(String username);

    /**
     * 查询用户记录总数
     * @return
     */
    int selectCount();
    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<User> findByPage(HashMap<String,Object> map);

}
