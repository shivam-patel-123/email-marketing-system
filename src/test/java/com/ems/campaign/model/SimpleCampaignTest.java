package com.ems.campaign.model;

import com.ems.campaign.persistent.CampaignDbMock;
import com.ems.campaign.persistent.ICampaignPersistent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCampaignTest {

    private static CampaignFactory campaignFactory;
    private static Campaign campaign;
    private static ICampaignPersistent campaignPersistent;

    @BeforeAll
    public static void init() {
        campaignFactory = new CampaignFactory();
        campaign = campaignFactory.createCampaign();
        campaignPersistent = new CampaignDbMock();
    }

    @Test
    public void createNewCampaignSuccessTest() {
        int numOfRowsInserted = campaign.createNewCampaign(campaignPersistent, "1", "11");
        assertEquals(1, numOfRowsInserted);
    }

    @Test
    public void createNewCampaignFailureTest() {
        int numOfRowsInserted = campaign.createNewCampaign(campaignPersistent, "2", "11");
        assertEquals(-1, numOfRowsInserted);
    }

    @Test
    public void getCampaignIdSuccessTest() {
        campaign.setCampaignId("c-637y7-896uy-09guy-8uyjh-iouj7");
        assertTrue(campaign.getCampaignId().startsWith("c-"));
    }

    @Test
    public void getCampaignIdIncorrectTest() {
        campaign.setCampaignId("637y7-896uy-09guy-8uyjh-iouj7");
        assertFalse(campaign.getCampaignId().startsWith("c-"));
    }

    @Test
    public void getCampaignNameSuccessTest() {
        campaign.setCampaignName("Campaign 1");
        assertEquals("Campaign 1", campaign.getCampaignName());
    }

    @Test
    public void getCampaignNameIncorrectTest() {
        campaign.setCampaignName("Campaign 1");
        assertNotEquals("Campaign 2", campaign.getCampaignName());
    }

    @Test
    public void getCampaignStatusSuccessTest() {
        campaign.setCampaignStatus("upcoming");
        assertEquals("upcoming", campaign.getCampaignStatus());
    }

    @Test
    public void getCampaignStatusIncorrectTest() {
        campaign.setCampaignStatus("upcoming");
        assertNotEquals("active", campaign.getCampaignStatus());
    }

    @Test
    public void getCampaignStartSuccessTest() {
        DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = dateFormatter.parse("2022-09-25 07:27:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        campaign.setCampaignStartTime(date);
        String actualDate = dateFormatter.format(campaign.getCampaignStartTime());

        assertEquals("2022-09-25 07:27:00", actualDate);
    }

    @Test
    public void getCampaignStartIncorrectTest() {
        DateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = dateFormatter.parse("2022-09-25 07:27:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        campaign.setCampaignStartTime(date);
        String actualDate = dateFormatter.format(campaign.getCampaignStartTime());

        assertNotEquals("2021-12-01 07:27:00", actualDate);
    }

    @Test
    public void getAnalyticsTest() {
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        campaign.setAnalytics(campaignAnalytics);

        assertEquals(0.0, campaign.getAnalytics().getConversionRate());
        assertEquals(0.0, campaign.getAnalytics().getClickThroughRate());
        assertEquals(0.0, campaign.getAnalytics().getUnsubscribeRate());
    }
}
