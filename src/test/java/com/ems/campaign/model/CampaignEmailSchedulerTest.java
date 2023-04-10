package com.ems.campaign.model;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampaignEmailSchedulerTest {

    @Test
    public void setValueTest() {
        Campaign campaign = new CampaignFactory().createCampaign();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-26 09:34:00");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Campaign activeCampaign = new CampaignFactory().createCampaign("Template 1", date);
        CampaignEmailScheduler scheduler = new CampaignEmailScheduler(campaign);

        scheduler.setValue("campaign", activeCampaign);
        Campaign expectedCampaign = (Campaign) scheduler.getValue("campaign");

        assertEquals("Template 1", expectedCampaign.getCampaignName());
    }

//    @Test
//    public void getValueTest() {
//        
//    }
}
