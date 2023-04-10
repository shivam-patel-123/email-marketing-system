package com.ems.authentication.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.authentication.buisness.Authenticate;
import com.ems.authentication.buisness.IAuthenticate;
import com.ems.authentication.buisness.State;
import com.ems.authentication.buisness.MD5;

import com.ems.authentication.persistence.UserDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/authentication")
public class AuthenticationController {
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){
      HttpSession session1=request.getSession();
       Object isRedirect= session1.getAttribute("loginUnsuccessful");
        ModelAndView mv = new ModelAndView("login");
       if (isRedirect==null){
           return mv;
       }
       session.removeAttribute("loginUnsuccessful");
       mv.addObject("message","login Unsuccessful please try again");
       return mv;

    }
    @RequestMapping(value = "/login")

    public void login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){
        ModelAndView mv = new ModelAndView();
        try {
            Map<String,String[]> parameters=request.getParameterMap();
            String email=parameters.get("email")[0];
            String password=parameters.get("password")[0];
            Connection conn= null;
            conn = MySqlPersistenceConnection.getInstance().getConnection();
            IAuthenticate authenticate=new Authenticate();
            State loginState=authenticate.login(email,password,new UserDB(conn),MD5.getInstance());
            mv.addObject("message",loginState.message);
            HttpSession session1 =request.getSession();
            loginState.handleSession(session1);
            response.sendRedirect(loginState.redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @RequestMapping(value = "/logout")

    public void logout(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
        try {
            IAuthenticate authenticate = new Authenticate();
            authenticate.logout(request.getSession());
            response.sendRedirect("/");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
