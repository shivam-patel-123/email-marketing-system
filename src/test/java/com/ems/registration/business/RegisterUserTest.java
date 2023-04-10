package com.ems.registration.business;

import com.ems.authentication.persistence.IUserPersistence;
import com.ems.registration.dto.RegisterUserDto;
import com.ems.registration.persistence.UserMockDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterUserTest {
    private static IRegisterUser registerUser;
    private static IUserPersistence userPersistence;

    @BeforeAll
    public static void init(){
        registerUser = new RegisterUser();
        userPersistence = new UserMockDB();
    }

    @Test
    public void registerUserNullTest(){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.email = "";
        registerUserDto.password = "";
        boolean isUserRegistered = registerUser.registerUser(registerUserDto, userPersistence);
        assertFalse(isUserRegistered);
    }

    @Test
    public void registerUserWithNullEmailTest(){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.email = "";
        registerUserDto.password = "12345";
        boolean isUserRegistered = registerUser.registerUser(registerUserDto, userPersistence);
        assertFalse(isUserRegistered);
    }

    @Test
    public void registerUserWithNullPasswordTest(){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.email = "johndoe@xyz.com";
        registerUserDto.password = "";
        boolean isUserRegistered = registerUser.registerUser(registerUserDto, userPersistence);
        assertFalse(isUserRegistered);
    }

    @Test
    public void registerUserSuccessTest(){
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.email = "johndoe@xyz.com";
        registerUserDto.password = "12345";
        boolean isUserRegistered = registerUser.registerUser(registerUserDto, userPersistence);
        assertTrue(isUserRegistered);
    }

}
