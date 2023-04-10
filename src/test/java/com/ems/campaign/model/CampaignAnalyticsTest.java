package com.ems.campaign.model;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.buisness.SimpleEmailDetails;
import com.ems.bulkemail.persistence.EmailDetailDbMock;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CampaignAnalyticsTest {

    private static CampaignAnalytics campaignAnalytics;

    private static IEmailDetailsPersistence emailDetailsPersistence;
    @BeforeAll
    public static void init(){
        campaignAnalytics = new CampaignAnalytics();
        emailDetailsPersistence = new EmailDetailDbMock();
    }

    @Test
    public void getConversionRateSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        campaignAnalytics.setConversionRate(10.0);
        double conversionRate = campaignAnalytics.getConversionRate();
        assertEquals(conversionRate, 10.0);
    }

    @Test
    public void setConversionRateSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        double conversionRate = campaignAnalytics.getConversionRate();
        campaignAnalytics.setConversionRate(10.0);
        double newConversionRate = campaignAnalytics.getConversionRate();
        assertNotEquals(conversionRate, newConversionRate);
    }

    @Test
    public void getUnsubscribeRateSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        campaignAnalytics.setUnsubscribeRate(10.0);
        double conversionRate = campaignAnalytics.getUnsubscribeRate();
        assertEquals(conversionRate, 10.0);
    }

    @Test
    public void setUnsubscribeRateSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        double conversionRate = campaignAnalytics.getUnsubscribeRate();
        campaignAnalytics.setUnsubscribeRate(10.0);
        double newConversionRate = campaignAnalytics.getUnsubscribeRate();
        assertNotEquals(conversionRate, newConversionRate);
    }

    @Test
    public void getClickThroughRateSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        campaignAnalytics.setClickThroughRate(10.0);
        double conversionRate = campaignAnalytics.getClickThroughRate();
        assertEquals(conversionRate, 10.0);
    }

    @Test
    public void setClickThroughRateSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        double conversionRate = campaignAnalytics.getClickThroughRate();
        campaignAnalytics.setClickThroughRate(10.0);
        double newConversionRate = campaignAnalytics.getClickThroughRate();
        assertNotEquals(conversionRate, newConversionRate);
    }

    @Test
    public void getSubscribersCountSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        campaignAnalytics.setSubscribersCount(10);
        assertEquals(10, campaignAnalytics.getSubscribersCount());
    }

    @Test
    public void getEmailDetailsListSuccessTest() {
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        List<EmailDetails> emailDetailsList = new ArrayList<>();
        emailDetailsList.add(new SimpleEmailDetails());
        emailDetailsList.add(new SimpleEmailDetails());
        campaignAnalytics.setEmailDetailsList(emailDetailsList);
        assertTrue(campaignAnalytics.getEmailDetailsList().size() > 0);
    }

    @Test
    public void setEmailDetailsListSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        List<EmailDetails> oldEmailDetailsList = campaignAnalytics.getEmailDetailsList();
        List<EmailDetails> newEmailDetailsList = new ArrayList<>();
        EmailDetails emailDetail = new SimpleEmailDetails();
        newEmailDetailsList.add(emailDetail);
        campaignAnalytics.setEmailDetailsList(newEmailDetailsList);

        assertNotEquals(oldEmailDetailsList, campaignAnalytics.getEmailDetailsList());
    }

    @Test
    public void setSubscribersCountSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        int subscribersCount = campaignAnalytics.getSubscribersCount();
        campaignAnalytics.setSubscribersCount(10);
        int newSubscribersCount = campaignAnalytics.getSubscribersCount();
        assertNotEquals(subscribersCount, newSubscribersCount);
    }

    @Test
    public void getEmailClicksSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        campaignAnalytics.setEmailClicks(10);
        assertEquals(10, campaignAnalytics.getEmailClicks());
    }

    @Test
    public void setEmailClicksSuccessTest(){
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        int emailClicks = campaignAnalytics.getEmailClicks();
        campaignAnalytics.setEmailClicks(10);
        int newEmailClicks = campaignAnalytics.getEmailClicks();
        assertNotEquals(emailClicks, newEmailClicks);
    }

}
