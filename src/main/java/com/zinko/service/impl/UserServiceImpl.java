package com.zinko.service.impl;

import com.zinko.data.dao.UserDao;
import com.zinko.data.dao.entity.User;
import com.zinko.data.dao.entity.enums.Role;
import com.zinko.data.dao.impl.UserDaoImpl;
import com.zinko.service.UserService;
import com.zinko.service.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    Logger log = LogManager.getLogger(UserDaoImpl.class);
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserDto createUserDtoFromUser(User user) {
        log.debug("service method createUserFromUser call");
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            return userDto;
        } else return null;
    }

    @Override
    public User createUser(Long id, String firstName, String lsatName, String email, String password, String role) {
        log.debug("service method createUser call");
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
        log.debug("service method findAll call");
        List<User> listUser = userDao.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        for (User user :
                listUser) {
            listUserDto.add(createUserDtoFromUser(user));
        }
        return listUserDto;
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("service method findById call");
        User user = userDao.findById(id);
        return createUserDtoFromUser(user);
    }

    @Override
    public boolean create(User user) {
        log.debug("service method create call");
        return userDao.create(user);
    }

    @Override
    public boolean update(User user) {
        log.debug("service method update call");
        User userBefore = userDao.findById(user.getId());
        if (user.getFirstName().equals(""))
            user.setFirstName(userBefore.getFirstName());
        if (user.getLastName().equals(""))
            user.setLastName(userBefore.getLastName());
        if (user.getEmail().equals(""))
            user.setEmail(userBefore.getEmail());
        if (user.getPassword().equals(""))
            user.setPassword(userBefore.getPassword());
        if (user.getRole().equals(""))
            user.setRole(userBefore.getRole());
        return userDao.update(user);
    }

    public boolean delete(Long id) {
        log.debug("service method delete call");
        return userDao.delete(userDao.findById(id));
    }

    @Override
    public UserDto login(String email, String password) {
        log.debug("service method login call");
        User user = userDao.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password))
                return createUserDtoFromUser(user);
            else return null;
        } else return null;
    }
}
