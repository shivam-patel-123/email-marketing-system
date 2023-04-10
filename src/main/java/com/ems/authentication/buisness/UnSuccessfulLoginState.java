package com.ems.authentication.buisness;

import javax.servlet.http.HttpSession;

public class UnSuccessfulLoginState extends State {

    UnSuccessfulLoginState(){
        super.message="login Unsuccessful please try again";
        super.redirectUrl="/authentication/index";
    }

    @Override
    public State handle() {
        return this;
    }

    @Override
    public HttpSession handleSession(HttpSession session) {
            session.removeAttribute("isLoggedIn");
            session.removeAttribute("user");
            session.setAttribute("loginUnsuccessful",true);
        return session;
    }
}
