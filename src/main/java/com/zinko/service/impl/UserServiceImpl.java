package com.zinko.service.impl;

import com.zinko.data.dao.UserDao;
import com.zinko.data.dao.entity.User;
import com.zinko.data.dao.impl.UserDaoImpl;
import com.zinko.service.UserService;
import com.zinko.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    private static UserDto createUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirsName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> listUser = userDao.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        for (User user:
             listUser) {
            listUserDto.add(createUserDtoFromUser(user));
        }
        return listUserDto;
    }

    @Override
    public UserDto findById(Long id) {
        User user = userDao.findById(id);
        return createUserDtoFromUser(user);
    }

    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public UserDto login(String email, String password) {
        User user = userDao.findByEmail(email);
        if(user.getPassword().equals(password))
        return createUserDtoFromUser(user);
        else return null;
    }
}
