package com.example.demo.entity;


import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Data
public class User {
    private final String password;
    private final String userName;

    public User(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    public Map<String, Integer> fullInfoUser() {
        return Collections.singletonMap(userName, password.hashCode());
    }


}
