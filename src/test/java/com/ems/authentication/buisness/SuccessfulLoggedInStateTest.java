package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuccessfulLoggedInStateTest {
    private static State state;

    @BeforeAll
    public static void init(){
        User user= new User();
        state=new SuccessfulLoggedInState(user);
    }

    @Test
    public void handleTest(){
        State state1= state.handle();
        assertNotNull(state1);
    }

    @Test
    public void handleSessionTest(){
        HttpSession session= new MockHttpSession();
        HttpSession session2= state.handleSession(session);
        Boolean IsLoggedIn=(boolean)session2.getAttribute("isLoggedIn");
        assertEquals(true,IsLoggedIn);
    }
    @Test
    public void handleSessionUserTest(){
        HttpSession session= new MockHttpSession();
        HttpSession session2= state.handleSession(session);
        User user=(User)session2.getAttribute("user");
        assertNotNull(user);
    }

}
