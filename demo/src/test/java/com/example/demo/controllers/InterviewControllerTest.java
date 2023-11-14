package com.example.demo.controllers;


import com.example.demo.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InterviewControllerTest {

    @Mock
    private RepositoryUser repositoryUser;

    @Mock
    private CacheManager cacheManager;


    @InjectMocks
    private InterviewController interviewController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddUserInSessionCache() {
        when(repositoryUser.getByLoginPassword(any())).thenCallRealMethod();
        interviewController.addUserInSessionCache(new User("SashaNeLox00", "SashaLox"));
        verify(repositoryUser).getByLoginPassword(eq(new User("SashaNeLox00", "SashaLox")));
    }

    @Test(dataProvider = "isPresentData")
    public void shouldIsPresent(Map<String, Integer> cacheMap, Integer password, boolean expectedResult) {
        Cache cache = Mockito.mock(Cache.class);
        when(cacheManager.getCache("user")).thenReturn(cache);
        when(cache.getNativeCache()).thenReturn(cacheMap);
        boolean result = interviewController.isPresent(password);
        assertEquals(result, expectedResult);
    }

    @DataProvider(name = "isPresentData")
    private Object[][] serviceData() {
        return new Object[][]{
                {new HashMap<String, Integer>() {{
                    put("SashaLox", "SashaNeLox00".hashCode());
                }}, "SashaNeLox00".hashCode(), true
                },
                {new HashMap<String, Integer>() {{
                    put("SashaLox", "SashaNeLox11".hashCode());
                }}, "SashaNeLox00".hashCode(), false
                }

        };
    }


}
