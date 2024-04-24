package com.zinko.data.dao.entity;

import com.zinko.data.dao.entity.enums.Role;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private char[] password;
    private Role role;
}
