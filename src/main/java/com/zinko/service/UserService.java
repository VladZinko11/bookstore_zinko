package com.zinko.service;

import com.zinko.data.dao.entity.User;
import com.zinko.service.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    boolean create(User user);

    boolean update(User user);

    boolean delete(Long id);

    UserDto login(String email, String password);

    User createUser(Long id, String firstName, String lsatName, String email, String password, String role);

    UserDto createUserDtoFromUser(User user);


}
