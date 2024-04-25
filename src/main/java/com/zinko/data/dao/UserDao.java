package com.zinko.data.dao;

import com.zinko.data.dao.entity.User;

import java.util.List;

public interface UserDao {

    boolean create(User user);

    User findById(Long id);

    List<User> findAll();

    boolean update();

    boolean delete();

    User findByEmail(String email);

    List<User> findByLastName(String LastName);

    Long countAll();
}
