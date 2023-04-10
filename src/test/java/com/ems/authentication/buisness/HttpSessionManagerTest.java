package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

public class HttpSessionManagerTest {

    private static HttpSessionManager sessionManager;

    @BeforeAll
    public static void init(){
        sessionManager= new HttpSessionManager();
    }

    @Test
    public void ValidateSessionSuccessTest(){
        HttpSession session1= new MockHttpSession();
        session1.setAttribute("isLoggedIn",true);
        boolean result = sessionManager.validateSession(session1);
        assertEquals(true,result);
    }
    @Test
    public void ValidateSessionFailNoObjectinSessionTest(){
        HttpSession session1= new MockHttpSession();

        boolean result = sessionManager.validateSession(session1);
        assertEquals(false,result);

    }

    @Test
    public void ValidateSessionFailTest(){
        HttpSession session1= new MockHttpSession();
        session1.setAttribute("isLoggedIn",false);
        boolean result = sessionManager.validateSession(session1);
        assertEquals(false,result);

    }

    @Test
    public void getUserFromSessionNullTest(){
        HttpSession session1= new MockHttpSession();
        session1.setAttribute("isLoggedIn",false);
        assertNull(sessionManager.getUserFromSession(session1));

    }

    @Test
    public void getUserFromSessionSuccessTest(){
        HttpSession session1= new MockHttpSession();
        User user = new User();
        session1.setAttribute("user",user);
        assertNotNull(sessionManager.getUserFromSession(session1));

    }
}
