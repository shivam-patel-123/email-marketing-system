package com.ems.campaign.model;

import com.ems.campaign.persistent.ICampaignPersistent;

public class CampaignManipulator {
    private ICampaignPersistent campaignPersistent;

    public CampaignManipulator(ICampaignPersistent campaignPersistent) {
        this.campaignPersistent = campaignPersistent;
    }

    public int updateCampaign(String campaignId, Campaign campaign) {
        return campaignPersistent.update(campaignId, campaign);
    }

    public int deleteCampaign(String campaignId) {
        return campaignPersistent.delete(campaignId);
    }
}
