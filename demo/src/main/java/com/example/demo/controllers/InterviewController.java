package com.example.demo.controllers;


import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;
import java.util.Objects;

@Controller
public class InterviewController {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/add/{id}")
    public void addUserInSessionCache(@RequestBody User user) {
        repositoryUser.getByLoginPassword(user);
    }


    @PostMapping("/add/{id}")
    @SuppressWarnings("unchecked")
    public boolean isPresent(@RequestParam Integer password) {

        Cache cache = this.cacheManager.getCache("user");
        if(cache == null) {
            return false;
        }
        return ((Map<String, Integer>) cache.getNativeCache()).values().stream().mapToInt(Integer::valueOf).anyMatch(x -> x == password);
    }


}
