package com.ems.registration.business;


import com.ems.authentication.persistence.IUserPersistence;
import com.ems.registration.dto.RegisterUserDto;

public interface IRegisterUser {
    public boolean registerUser(RegisterUserDto registerUserDto, IUserPersistence userDB);
}
