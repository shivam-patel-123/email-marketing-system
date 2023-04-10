package com.ems.campaign.model;

import java.util.Timer;
import java.util.TimerTask;

public class CampaignEmailScheduler extends Subject {
    private Campaign campaign;

    public CampaignEmailScheduler(Campaign campaign) {
        this.campaign = campaign;
    }

    public void scheduleEmailSender() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setValue("campaignId", campaign.getCampaignId());
                notifyObservers();
            }
        };
        timer.schedule(timerTask, campaign.getCampaignStartTime());
    }
}
