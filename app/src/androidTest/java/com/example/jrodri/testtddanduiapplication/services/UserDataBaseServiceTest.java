package com.example.jrodri.testtddanduiapplication.services;

import android.support.test.runner.AndroidJUnit4;

import com.example.jrodri.testtddanduiapplication.models.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jrodri on 11/7/17.
 */
@RunWith(AndroidJUnit4.class)
public class UserDataBaseServiceTest {

    private User user;
    private UserDataBaseService userDataBaseService;


    @Before
    public void setUp(){
        userDataBaseService = new UserDataBaseService();
        user = new User("nombre","username","12345_aB",18);
    }

    @Test
    public void testSaveDataInDataBase(){
        userDataBaseService.saveEntity(user);
        assertTrue(user.exists());
    }

    @Test
    public void testDeleteFromDataBase(){
        userDataBaseService.deleteEntity(user);
        assertFalse(user.exists());
    }
}