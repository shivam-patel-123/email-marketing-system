package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;

import javax.servlet.http.HttpSession;


public class Authenticate implements IAuthenticate{


    @Override
    public State login(String email, String password, IUserPersistence userPersistence,IHash hashingAlgorithm) {
        User user= new User();
        user.email=email;
        try {
                user.password = hashingAlgorithm.hash(password);
                User returnedUser = user.loadUser(userPersistence);
                if (returnedUser==null){
                    //returns unsuccessful login state if password does not match
                  return new UnSuccessfulLoginState().handle();
                }
                if (returnedUser.password.equalsIgnoreCase(user.password)){
                    //returns successful login state if password matches
                    return new SuccessfulLoggedInState(returnedUser).handle();
                }
                }
        catch (Exception e) {
                return new UnSuccessfulLoginState().handle();
        }
         return new UnSuccessfulLoginState().handle();
    }

    //the function removes the set values from session when called to log out the user
    @Override
    public HttpSession logout(HttpSession session) {
        session.removeAttribute("isLoggedIn");
        session.removeAttribute("user");
        return session;
    }

}
