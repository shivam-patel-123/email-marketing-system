package com.ems.authentication.persistence;

import com.ems.authentication.model.User;

import java.sql.SQLException;

public interface IUserPersistence {
    User loadUser(User user) throws Exception;
    boolean isUserRegistered(String email) throws SQLException;
    boolean registerUser(User user);
    boolean createUserSegment(String userId);
    User getUserByEmail(String email);
}
