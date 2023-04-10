package com.ems.registration.persistence;

import com.ems.authentication.buisness.MD5;
import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserMockDB implements IUserPersistence {

    @Override
    public User loadUser(User user) throws Exception {
        return null;
    }

    @Override
    public boolean isUserRegistered(String email) throws SQLException {
        return false;
    }

    @Override
    public boolean registerUser(User user) {

        if(user.email.equals("johndoe@xyz.com")){
            try{
                return user.password.equals(MD5.getInstance().hash("12345"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public boolean createUserSegment(String userId) {
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
