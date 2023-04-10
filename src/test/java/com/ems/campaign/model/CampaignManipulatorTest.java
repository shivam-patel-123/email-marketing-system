package com.ems.campaign.model;

import com.ems.campaign.persistent.CampaignDbMock;
import com.ems.campaign.persistent.ICampaignPersistent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampaignManipulatorTest {
    private static CampaignManipulator campaignManipulator;
    private static CampaignFactory campaignFactory;

    @BeforeAll
    public static void init() {
        ICampaignPersistent campaignPersistent = new CampaignDbMock();
        campaignManipulator = new CampaignManipulator(campaignPersistent);
        campaignFactory = new CampaignFactory();
    }

    @Test
    public void updateCampaignSuccessTest() {
        Campaign updatedCampaign = null;
        try {
            updatedCampaign = new CampaignFactory().createCampaign("Black Friday Sale", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-30 11:23:33"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int numOfRowsUpdated = campaignManipulator.updateCampaign("1", updatedCampaign);

        assertEquals(1, numOfRowsUpdated);
    }

    @Test
    public void updateCampaignNotFoundTest() {
        Campaign updatedCampaign = null;
        try {
            updatedCampaign = new CampaignFactory().createCampaign("Black Friday Sale", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-30 11:23:33"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int numOfRowsUpdated = campaignManipulator.updateCampaign("11", updatedCampaign);

        assertEquals(-1, numOfRowsUpdated);
    }

    @Test
    public void deleteCampaignSuccessTest() {
        int numOfRowsDeleted = campaignManipulator.deleteCampaign("1");
        assertEquals(1, numOfRowsDeleted);
    }

    @Test
    public void deleteCampaignNotFoundTest() {
        int numOfRowsDeleted = campaignManipulator.deleteCampaign("11");
        assertEquals(-1, numOfRowsDeleted);
    }
}
