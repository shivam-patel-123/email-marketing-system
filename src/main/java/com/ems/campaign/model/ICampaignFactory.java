package com.ems.campaign.model;

import java.util.Date;

public interface ICampaignFactory {
    Campaign createCampaign(String campaignName, Date campaignStartTime);
    Campaign createCampaign();
}
