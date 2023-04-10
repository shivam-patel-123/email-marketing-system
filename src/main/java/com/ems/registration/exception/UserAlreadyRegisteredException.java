package com.ems.registration.exception;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException() {
        super("User already registered");
    }

}
