package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import com.ems.authentication.persistence.EmailDbMock;
import com.ems.authentication.persistence.IUserPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticateTest {

    private static IAuthenticate authenticate;

    private static IUserPersistence userPersistence;

    private static IHash hashingAlgorithm;

    @BeforeAll
    public static void init(){
        authenticate= new Authenticate();
        userPersistence= new EmailDbMock();
        hashingAlgorithm = MD5.getInstance();

    }
    @Test
    public void LoginNullTest(){

       State state= authenticate.login("abc@gmail.com","12345678",userPersistence,hashingAlgorithm);

       assertTrue(state instanceof UnSuccessfulLoginState);


    }
    @Test
    public void LoginFailTest(){

        State state= authenticate.login("xyz@gmail.com","12345678",userPersistence,hashingAlgorithm);

        assertTrue(state instanceof UnSuccessfulLoginState);


    }
    @Test
    public void LoginFailIncorrectPasswordTest(){

        State state= authenticate.login("correctUser@gmail.com","12345",userPersistence,hashingAlgorithm);

        assertTrue(state instanceof UnSuccessfulLoginState);


    }
    @Test
    public void LoginFailSuccessTest(){

        State state= authenticate.login("correctUser@gmail.com","12345678",userPersistence,hashingAlgorithm);

        assertTrue(state instanceof SuccessfulLoggedInState);


    }

    @Test
    public void logoutSuccessTest() throws Exception {

        State state= authenticate.login("correctUser@gmail.com","12345678",userPersistence,hashingAlgorithm);
        HttpSession session= new MockHttpSession();
        session.setAttribute("isLoggedIn",true);
        HttpSession session2= authenticate.logout(session);
        assertNull(session2.getAttribute("isLoggedIn"));
        assertNull(session2.getAttribute("user"));


    }
}
