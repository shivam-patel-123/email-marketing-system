package com.ems.authentication.buisness;

import com.ems.authentication.model.User;

import javax.servlet.http.HttpSession;

public interface ISessionManager {
    public boolean validateSession(HttpSession session);
    public User getUserFromSession(HttpSession session);
}
