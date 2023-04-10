package com.ems.campaign.model;

import java.util.Date;

public class CampaignFactory implements ICampaignFactory {
    @Override
    public Campaign createCampaign(String campaignName, Date campaignStartTime) {
        return new SimpleCampaign(campaignName, campaignStartTime);
    }

    @Override
    public Campaign createCampaign() {
        return new SimpleCampaign();
    }
}
