package com.ems.authentication.model;

import com.ems.authentication.exception.DatabaseNotFound;
import com.ems.authentication.persistence.EmailDbMock;
import com.ems.authentication.persistence.IUserPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private static User user;
    private static IUserPersistence userPersistence;

    @BeforeAll
    public static void init(){
        user=new User();
        userPersistence=new EmailDbMock();
    }
    @Test
    public void loadUserExceptionTest(){
        user = new User();
        user.email="abc@gmail.com";
        try {
            User fetchedUser=user.loadUser(userPersistence);

            assertFalse(true);
        } catch (Exception e) {
            assertTrue(e instanceof DatabaseNotFound);
        }
    }

    @Test
    public void loadUserNullTest() throws Exception {
        user = new User();
        user.email="xyz@gmail.com";

        User fetchedUser=user.loadUser(userPersistence);
        assertNull(fetchedUser);

    }

    @Test
    public void loadUserSuccessTest() throws Exception {
        user = new User();
        user.email="correctUser@gmail.com";

        User fetchedUser=user.loadUser(userPersistence);
        assertNotNull(fetchedUser);
        assertEquals("085e3aa4-68b0-4061-9f68-bd7d242df370",fetchedUser.userId);

    }

}
