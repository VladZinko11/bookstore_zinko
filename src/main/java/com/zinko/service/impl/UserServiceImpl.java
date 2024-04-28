package com.zinko.service.impl;

import com.zinko.data.dao.UserDao;
import com.zinko.data.dao.entity.User;
import com.zinko.data.dao.entity.enums.Role;
import com.zinko.data.dao.impl.UserDaoImpl;
import com.zinko.service.UserService;
import com.zinko.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserDto createUserDtoFromUser(User user) {
        if(user!=null) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            return userDto;
        } else return null;
    }
    @Override
    public User createUser(Long id, String firstName, String lsatName, String email, String password, String role) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lsatName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));
        return user;
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
        User userBefore = userDao.findById(user.getId());
        if(user.getFirstName().equals(""))
            user.setFirstName(userBefore.getFirstName());
        if(user.getLastName().equals(""))
            user.setLastName(userBefore.getLastName());
        if(user.getEmail().equals(""))
            user.setEmail(userBefore.getEmail());
        if(user.getPassword().equals(""))
            user.setPassword(userBefore.getPassword());
        if(user.getRole().equals(""))
            user.setRole(userBefore.getRole());
        return userDao.update(user);
    }

    public boolean delete(Long id) {
        return userDao.delete(userDao.findById(id));
    }

    @Override
    public UserDto login(String email, String password) {
        User user = userDao.findByEmail(email);
        if(user.getPassword().equals(password))
        return createUserDtoFromUser(user);
        else return null;
    }
}
