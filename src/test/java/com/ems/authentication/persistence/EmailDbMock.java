package com.ems.authentication.persistence;

import com.ems.authentication.exception.DatabaseNotFound;
import com.ems.authentication.model.Role;
import com.ems.authentication.model.User;

import java.sql.SQLException;

public class EmailDbMock implements IUserPersistence{
    @Override
    public User loadUser(User user) throws Exception {
        if (user.email=="abc@gmail.com"){
            throw new DatabaseNotFound();
        }
        else if (user.email=="xyz@gmail.com"){
            return null;
        }
        else if (user.email=="correctUser@gmail.com"){
            User user1 = new User();
            user1.userId="085e3aa4-68b0-4061-9f68-bd7d242df370";
            user1.role=new Role();
            user1.role.name="customer";
            user1.password="25d55ad283aa400af464c76d713c07ad";
            return user1;

        }

        return null;
    }

    @Override
    public boolean isUserRegistered(String email) throws SQLException {
        return false;
    }

    @Override
    public boolean registerUser(User user) {
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
