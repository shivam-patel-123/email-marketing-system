package com.ems.emailanalytics.buisness;

import com.ems.bulkemail.buisness.SimpleEmailDetails;
import com.ems.bulkemail.persistence.EmailDetailDbMock;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixelAnalyticsTest {

    private static PixelAnalytics pixelAnalytics;
    private static IEmailDetailsPersistence emailDetailsPersistence;

    private static SimpleEmailDetails emailDetails;

    @BeforeAll
    public static void init(){
        pixelAnalytics= new PixelAnalytics();
        emailDetailsPersistence= new EmailDetailDbMock();
        emailDetails=new SimpleEmailDetails();


    }
    @Test
    public void performAnalyticsFailedToGetEmailTest(){
        boolean result = pixelAnalytics.performAnalytics("xyz",emailDetails ,emailDetailsPersistence);
        assertEquals(false,result);
    }
    @Test
    public void performAnalyticsSuccessTest(){
        boolean result = pixelAnalytics.performAnalytics("p-26b52502-93bc-48de-9383-655e97009014",emailDetails ,emailDetailsPersistence);
        assertEquals(true,result);
    }
}
