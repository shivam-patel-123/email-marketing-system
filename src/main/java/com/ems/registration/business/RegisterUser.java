package com.ems.registration.business;

import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;
import com.ems.registration.dto.RegisterUserDto;

public class RegisterUser implements IRegisterUser{
    @Override
    public boolean registerUser(RegisterUserDto registerUserDto, IUserPersistence userDB) {
        String email = registerUserDto.email;
        String password = registerUserDto.password;

        // Validate the input credentials by the user.
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }
        // validate is user already exists or not

        // Register User
       try{
           return userDB.registerUser(new User(email, password));
       } catch(Exception exception){
           return false;
       }
    }
}
