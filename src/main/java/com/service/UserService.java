package com.service;

import com.pojo.PageBean;
import com.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    int addUser(User user);

    int updateUser(User user);

    int registerUser(User user);

    int deleteUserById(int id);

    User queryUserById(int id);

    List<User> queryAllUsers();

    User getByUserName(String username);

    Set<String> getRoles(String username);

    Set<String> getPermissions(String username);

    int selectCount();

    PageBean<User> findByPage(int currentPage);
}
