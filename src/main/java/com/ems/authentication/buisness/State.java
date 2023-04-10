package com.ems.authentication.buisness;

import javax.servlet.http.HttpSession;

//state pattern implemented to handle the login states while authenticating
public abstract class State {
    public String message;
    public String redirectUrl;
    public abstract State handle();
    public abstract HttpSession handleSession(HttpSession session);
}
