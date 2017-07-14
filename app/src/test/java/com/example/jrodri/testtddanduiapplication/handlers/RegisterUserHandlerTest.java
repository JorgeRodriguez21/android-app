package com.example.jrodri.testtddanduiapplication.handlers;
import com.example.jrodri.testtddanduiapplication.models.User;
import com.example.jrodri.testtddanduiapplication.services.UserDataBaseService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jrodri on 6/7/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterUserHandlerTest {

    @Mock
    private UserDataBaseService userDataBaseService;

    private RegisterUserHandler registerUserHandler;

    @Before
    public void setUp() {
        registerUserHandler = new RegisterUserHandler(userDataBaseService);
    }

    @Test
    public void checkIfUserIsAnAdult(){
        //Arrange
        int validAge = 18;
        int invalidAge = 16;

        //Act
        boolean isAnAdult = registerUserHandler.checkUserIsAnAdult(validAge);
        boolean isNotAnAdult = registerUserHandler.checkUserIsAnAdult(invalidAge);

        //Assert
        assertTrue(isAnAdult);
        assertFalse(isNotAnAdult);
    }

    @Test
    public void checkIfFieldIsNotEmpty(){
        //Arrange
        String empty = "";
        String nonEmpty = "data";

        //Act
       boolean isEmpty =  registerUserHandler.checkEmptyString(empty);
       boolean isNonEmpty =  registerUserHandler.checkEmptyString(nonEmpty);

        //Assert
        assertTrue(isEmpty);
        assertFalse(isNonEmpty);
    }

    @Test
    public void checkValidPasswordFormat(){
        //Arrange
        String validPassword = "12345_Ab";
        String invalidPassword1 = "1a_Ewew ";
        String invalidPassword2 = "1a";
        String invalidPassword3 = "1_";
        String invalidPassword4 = "a_";

        //Act
        boolean isValidPassWord = registerUserHandler.checkValidPassword(validPassword);
        boolean isInValidPassWord1 = registerUserHandler.checkValidPassword(invalidPassword1);
        boolean isInValidPassWord2= registerUserHandler.checkValidPassword(invalidPassword2);
        boolean isInValidPassWord3 = registerUserHandler.checkValidPassword(invalidPassword3);
        boolean isInValidPassWord4 = registerUserHandler.checkValidPassword(invalidPassword4);

        //Assert
        assertTrue(isValidPassWord);
        assertFalse(isInValidPassWord1);
        assertFalse(isInValidPassWord2);
        assertFalse(isInValidPassWord3);
        assertFalse(isInValidPassWord4);
    }

    @Test
    public void testSaveUserInDataBase(){
        //Arrange
        String name = "someName";
        String userName = "someUser";
        String password = "somePassword";
        int age = 1;
        User user = new User(name,userName,password,age);
        when(userDataBaseService.saveEntity(user)).thenReturn(true);

        //Act
        boolean isUserSaved = registerUserHandler.saveInDatabase(user);

        //Assert
        verify(userDataBaseService).saveEntity(user);
        assertTrue(isUserSaved);
    }

    @Test
    public void testSaveUserFailsInDataBase(){
        //Arrange
        String name = "someName";
        String userName = "someUser";
        String password = "somePassword";
        int age = 1;
        User user = new User(name,userName,password,age);
        when(userDataBaseService.saveEntity(user)).thenReturn(false);

        //Act
        boolean isUserSaved = registerUserHandler.saveInDatabase(user);

        //Assert
        verify(userDataBaseService).saveEntity(user);
        assertFalse(isUserSaved);
    }

    @Test
    public void testUserLogin(){
        //Arrange
        String userName = "someUser";
        String password = "somePassword";
        User user = new User(userName,password);
        when(userDataBaseService.entityExists(user)).thenReturn(true);

        //Act
        boolean isUserSaved = registerUserHandler.login(user);

        //Assert
        verify(userDataBaseService).entityExists(user);
        assertTrue(isUserSaved);
    }

    @Test
    public void testUserCannotLogin(){
        //Arrange
        String userName = "someUser";
        String password = "somePassword";
        User user = new User(userName,password);
        when(userDataBaseService.entityExists(user)).thenReturn(false);

        //Act
        boolean isUserSaved = registerUserHandler.login(user);

        //Assert
        verify(userDataBaseService).entityExists(user);
        assertFalse(isUserSaved);
    }
}