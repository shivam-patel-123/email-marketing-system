package com.ems.authentication.buisness;


import com.ems.authentication.persistence.IUserPersistence;

import javax.servlet.http.HttpSession;

public interface IAuthenticate {
    public State login(String email, String password, IUserPersistence userPersistence, IHash hashingAlgorithm);
    public HttpSession logout(HttpSession session) throws Exception;
}
