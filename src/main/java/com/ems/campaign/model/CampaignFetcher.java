package com.ems.campaign.model;

import com.ems.campaign.persistent.ICampaignPersistent;

import java.util.List;

public class CampaignFetcher {
    private ICampaignPersistent campaignPersistent;

    public CampaignFetcher(ICampaignPersistent campaignPersistent) {
        this.campaignPersistent = campaignPersistent;
    }

    public List<Campaign> fetchAllCampaigns() {
        return campaignPersistent.loadAllCampaign();
    }

    public Campaign fetchCampaign(String campaignId) {
        return campaignPersistent.loadCampaign(campaignId);
    }

    public List<Campaign> fetchAllCampaignByUserId(String userId) {
        return campaignPersistent.loadCampaignByUserId(userId);
    }
}
