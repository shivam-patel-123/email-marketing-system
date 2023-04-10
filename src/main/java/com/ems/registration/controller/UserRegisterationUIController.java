package com.ems.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class UserRegisterationUIController {
    @GetMapping("/register")
    public String registrationForm(){
        return "signUpForm.html";
    }
}
