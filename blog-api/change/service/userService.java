package com.shimh.service;

import com.shimh.entity.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User getUserByAccount(String account);

    User getUserById(Integer id);

    Integer saveUser(User user);

    Integer updateUser(User user);

    void deleteUserById(Integer id);
}