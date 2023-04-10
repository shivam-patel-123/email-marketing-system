package com.ems.campaign.model;

import com.ems.campaign.persistent.ICampaignPersistent;
import com.ems.emailtemplate.model.EmailTemplate;

import java.util.Date;
import java.util.UUID;

public abstract class Campaign {
    private String campaignId;
    private String campaignName;
    private String campaignStatus;
    private Date campaignStartTime;
    private CampaignAnalytics analytics;
    private String userSegmentId;
    private EmailTemplate emailTemplate;

    public Campaign() {
        this.campaignId = generateId();
        this.analytics = new CampaignAnalytics();
    }

    public Campaign(String campaignName, Date campaignStartTime) {
        this.campaignId = generateId();
        this.campaignName = campaignName;
        this.campaignStatus = "upcoming";
        this.campaignStartTime = campaignStartTime;
        this.analytics = new CampaignAnalytics();
    }

    public int createNewCampaign(ICampaignPersistent campaignPersistent, String templateId, String userSegmentId) {
        return campaignPersistent.save(this, templateId, userSegmentId);
    }

    private String generateId() {
        return "c-" + UUID.randomUUID();
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public Date getCampaignStartTime() {
        return campaignStartTime;
    }

    public void setCampaignStartTime(Date campaignStartTime) {
        this.campaignStartTime = campaignStartTime;
    }

    public CampaignAnalytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(CampaignAnalytics analytics) {
        this.analytics = analytics;
    }

    public String getUserSegmentId() {
        return userSegmentId;
    }

    public void setUserSegmentId(String userSegmentId) {
        this.userSegmentId = userSegmentId;
    }

    public EmailTemplate getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(EmailTemplate emailTemplate) {
        this.emailTemplate = emailTemplate;
    }
}
