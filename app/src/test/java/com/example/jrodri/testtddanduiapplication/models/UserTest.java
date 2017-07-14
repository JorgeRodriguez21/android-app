package com.example.jrodri.testtddanduiapplication.models;

import com.raizlabs.android.dbflow.structure.BaseModel;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by jrodri on 7/7/17.
 */
public class UserTest {

    @Test
    public void testConstructorWithoutParams(){
        //Arrange

        //Act
        User user = new User();

        //Assert
        assertNull(user.getName());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertEquals(0, user.getAge());
    }

    @Test
    public void testUserExtendsBaseModelAndImplementsSerializable(){

        User user = new User();
        assertTrue(user instanceof BaseModel);
        assertTrue(user instanceof Serializable);
    }

    @Test
    public void testConstructorWithParameters(){
        //Arrange
        String name = anyString();
        String userName = anyString();
        String password = anyString();
        int age = anyInt();

        //Act
        User user = new User(name,userName,password,age);

        //Assert
        assertEquals(name, user.getName());
        assertEquals(userName, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(age, user.getAge());
    }

    @Test
    public void testConstructorWith2ParametersForLogin(){
        //Arrange
        String userName = anyString();
        String password = anyString();

        //Act
        User user = new User(userName,password);

        //Assert
        assertNull(user.getName());
        assertEquals(userName, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(0, user.getAge());
    }
}