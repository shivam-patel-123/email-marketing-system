package com.ems.emailanalytics.buisness;


import com.ems.bulkemail.buisness.SimpleEmailDetails;
import com.ems.bulkemail.persistence.EmailDetailDbMock;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickAnalyticsTest {

    private static ClickAnalytics clickAnalytics;
    private static IEmailDetailsPersistence emailDetailsPersistence;

    private static SimpleEmailDetails emailDetails;

    @BeforeAll
    public static void init(){
        clickAnalytics= new ClickAnalytics();
        emailDetailsPersistence= new EmailDetailDbMock();
        emailDetails=new SimpleEmailDetails();


    }
    @Test
    public void performAnalyticsFailedToGetEmailTest(){
        boolean result = clickAnalytics.performAnalytics("xyz",emailDetails ,emailDetailsPersistence);
        assertEquals(false,result);
    }
    @Test
    public void performAnalyticsSuccessTest(){
        boolean result = clickAnalytics.performAnalytics("cl-0bc3bb0a-82ee-4550-a49f-c7b24bce9389",emailDetails ,emailDetailsPersistence);
        assertEquals(true,result);
    }
}
