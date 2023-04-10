package com.ems.authentication.model;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.ems.authentication.buisness.MD5;
import com.ems.authentication.persistence.IUserPersistence;

public class User {
    public String userId;
    public String email;
    public String password;
    public Role role;

    public User() {
        this.role = new Role();
    }

    public User(String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.password = generatePasswordHash(password);
        this.role = new Role();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public String generatePasswordHash(String password) {
        try {
            return MD5.getInstance().hash(password);

        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
            return "DEFAULT_PASSWORD";
        }
    }

    public User loadUser(IUserPersistence persistence) throws Exception {
        return  persistence.loadUser(this);
    }
}
