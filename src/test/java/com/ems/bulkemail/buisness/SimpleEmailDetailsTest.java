package com.ems.bulkemail.buisness;

import com.ems.bulkemail.persistence.EmailDetailDbMock;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SimpleEmailDetailsTest {
    private static EmailDetails emailDetails;

    private static IEmailDetailsPersistence emailDetailsPersistence;
    @BeforeAll
    public static void init() {
        emailDetails = new SimpleEmailDetails();
        emailDetailsPersistence = new EmailDetailDbMock();

    }
    @Test
    public void loadEmailDetailsByPixelIdSuccessTest(){
        EmailDetails emailDetails1=emailDetails.loadEmailDetailsByPixelId(emailDetailsPersistence,"xyz");
        assertNull(emailDetails1);
    }

    @Test
    public void loadEmailDetailsByPixelIdFailureTest(){
        EmailDetails emailDetails1=emailDetails.loadEmailDetailsByPixelId(emailDetailsPersistence,"p-26b52502-93bc-48de-9383-655e97009014");
        EmailDetails expectedEmailDetails=new SimpleEmailDetails();
        expectedEmailDetails.id="m-10b0b470-3928-4a31-8d4e-21609061c344";
        assertEquals(emailDetails1.id,expectedEmailDetails.id);
    }

    @Test
    public void loadEmailDetailsByClickIdFailsTest(){
        EmailDetails emailDetails1=emailDetails.loadEmailDetailsByClickId(emailDetailsPersistence,"xyz");
        assertNull(emailDetails1);
    }

    @Test
    public void loadEmailDetailsByClickIdSuccessTest(){
        EmailDetails emailDetails1=emailDetails.loadEmailDetailsByClickId(emailDetailsPersistence,"cl-0bc3bb0a-82ee-4550-a49f-c7b24bce9389");
        EmailDetails expectedEmailDetails=new SimpleEmailDetails();
        expectedEmailDetails.id="m-10b0b470-3928-4a31-8d4e-21609061c344";
        assertEquals(emailDetails1.id,expectedEmailDetails.id);
    }

    @Test
    public void saveEmailDetailsSuccessTest(){
        EmailDetails inputEmailDetails=new SimpleEmailDetails();
        inputEmailDetails.id="m-10b0b470-3928-4a31-8d4e-21609061c344";
        boolean result=inputEmailDetails.saveEmailDetails(emailDetailsPersistence);

        assertEquals(true,result);
    }
    @Test
    public void saveEmailDetailsFailTest(){
        EmailDetails inputEmailDetails=new SimpleEmailDetails();
        inputEmailDetails.id="xyz";
        boolean result=inputEmailDetails.saveEmailDetails(emailDetailsPersistence);

        assertEquals(false,result);
    }

    @Test
    public void createEmailDetailsSuccessTest(){
        EmailDetails inputEmailDetails=new SimpleEmailDetails();
        inputEmailDetails.id="m-10b0b470-3928-4a31-8d4e-21609061c344";
        boolean result=inputEmailDetails.createEmailDetails(emailDetailsPersistence);

        assertEquals(true,result);
    }
    @Test
    public void createEmailDetailsFailTest(){
        EmailDetails inputEmailDetails=new SimpleEmailDetails();
        inputEmailDetails.id="xyz";
        boolean result=inputEmailDetails.createEmailDetails(emailDetailsPersistence);

        assertEquals(false,result);
    }

}
