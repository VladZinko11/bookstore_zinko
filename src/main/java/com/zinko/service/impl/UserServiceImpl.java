package com.zinko.service.impl;

import com.zinko.data.dao.UserDao;
import com.zinko.data.dao.entity.User;
import com.zinko.data.dao.impl.UserDaoImpl;
import com.zinko.service.UserService;
import com.zinko.service.dto.UserDto;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    public UserDto createUserDtoFromUser(User user) {
        log.debug("service method createUserDtoFromUser call");
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());
            userDto.setPassword(user.getPassword());
            return userDto;
        } else return null;
    }

    public User createUserFromUserDto(UserDto userDto) {
        log.debug("service method createUser call");
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public List<UserDto> findAll() {
        log.debug("service method findAll call");
        return userDao.findAll().stream().map(this::createUserDtoFromUser).toList();
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("service method findById call");
        User user = userDao.findById(id);
        if (user != null)
            return createUserDtoFromUser(user);
        else throw new RuntimeException("User with id: " + id + " not exist");
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("service method create call");
        User user = userDao.create(createUserFromUserDto(userDto));
        if (user != null) return createUserDtoFromUser(user);
        else throw new RuntimeException("User with email: " + user.getEmail() + " is exist");
    }

    @Override
    public UserDto update(UserDto user) {
        log.debug("service method update call");
        User userBefore = createUserFromUserDto(findById(user.getId()));
        if (user.getFirstName() == null)
            user.setFirstName(userBefore.getFirstName());
        if (user.getLastName() == null)
            user.setLastName(userBefore.getLastName());
        if (user.getEmail() == null)
            user.setEmail(userBefore.getEmail());
        if (user.getPassword() == null)
            user.setPassword(userBefore.getPassword());
        if (user.getRole() == null)
            user.setRole(userBefore.getRole());
        User newUser = userDao.update(createUserFromUserDto(user));
        return createUserDtoFromUser(newUser);
    }

    public void delete(Long id) {
        log.debug("service method delete call");
        userDao.delete(userDao.findById(id));
    }

    @Override
    public UserDto login(String email, String password) {
        log.debug("service method login call");
        User user = userDao.findByEmail(email);
        if (user == null) throw new RuntimeException("Not found user with email: " + email);
        if (!user.getPassword().equals(password)) throw new RuntimeException("Wrong password");
        else return createUserDtoFromUser(user);
    }
}
