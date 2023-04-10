package com.ems.campaign.model;

import com.ems.campaign.persistent.CampaignDbMock;
import com.ems.campaign.persistent.ICampaignPersistent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CampaignFetcherTest {
    private static CampaignFetcher campaignFetcher;

    @BeforeAll
    public static void init() {
        ICampaignPersistent campaignPersistent = new CampaignDbMock();
        campaignFetcher = new CampaignFetcher(campaignPersistent);
    }

    @Test
    public void fetchAllCampaignTest() {
        List<Campaign> actualData = campaignFetcher.fetchAllCampaigns();
        assertEquals(3, actualData.size());
        assertEquals("Black Friday Campaign", actualData.get(0).getCampaignName());
        assertEquals("Summer Sale Campaign", actualData.get(1).getCampaignName());
        assertEquals("Spring Boot Course Campaign", actualData.get(2).getCampaignName());
    }

    @Test
    public void fetchCampaignValidCampaignIdTest() {
        Campaign campaign = campaignFetcher.fetchCampaign("1");
        assertEquals("1", campaign.getCampaignId());
    }

    @Test
    public void fetchCampaignCampaignNotFoundTest() {
        Campaign campaign = campaignFetcher.fetchCampaign("4");
        assertNull(campaign);
    }

    @Test
    public void fetchAllCampaignByUserId() {
        List<Campaign> campaigns = campaignFetcher.fetchAllCampaignByUserId("11");
        assertEquals(1, campaigns.size());
    }
}
