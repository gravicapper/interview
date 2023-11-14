package com.example.demo.controllers;

import com.example.demo.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class RepositoryUser {
    @Cacheable("user")
    public Map<String, Integer> getByLoginPassword(User user) {
        return user.fullInfoUser();
    }
}
