package com.ems.campaign.persistent;

import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignFactory;
import com.ems.campaign.model.ICampaignFactory;
import com.ems.emailtemplate.model.EmailTemplateFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class CampaignDbMock implements ICampaignPersistent {
    List<Campaign> dbMock = new ArrayList<>();
    ICampaignFactory campaignFactory = new CampaignFactory();

    public CampaignDbMock() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Campaign c1 = campaignFactory.createCampaign("Black Friday Campaign", dateFormatter.parse("2022-12-01 12:50:00"));
            c1.setCampaignId("1");
            c1.setUserSegmentId("11");
            c1.setEmailTemplate(new EmailTemplateFactory().createSimpleEmailTemplate("1", "Template Name", "Subject", "Description", "https://www.google.com"));
            Campaign c2 = campaignFactory.createCampaign("Summer Sale Campaign", dateFormatter.parse("2022-12-02 11:30:34"));
            c2.setCampaignId("2");
            c2.setUserSegmentId("22");
            c2.setEmailTemplate(new EmailTemplateFactory().createSimpleEmailTemplate("2", "Tempalte Name", "Subject", "Description", "https://www.google.com"));
            Campaign c3 = campaignFactory.createCampaign("Spring Boot Course Campaign", dateFormatter.parse("2022-09-26 06:34:00"));
            c3.setCampaignId("3");
            c3.setUserSegmentId("33");
            c3.setEmailTemplate(new EmailTemplateFactory().createSimpleEmailTemplate("3", "Tempalte Name", "Subject", "Description", "https://www.google.com"));
            dbMock.add(c1);
            dbMock.add(c2);
            dbMock.add(c3);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Campaign campaign, String templateId, String userSegmentId) {
        for (Campaign c : dbMock) {
            if (c.getUserSegmentId().equals(userSegmentId) && c.getEmailTemplate().getTemplateId().equals(templateId)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public List<Campaign> loadAllCampaign() {
        return dbMock;
    }

    @Override
    public Campaign loadCampaign(String campaignId) {
        ListIterator<Campaign> iterator = dbMock.listIterator();
        while (iterator.hasNext()) {
            Campaign currentCampaign = iterator.next();
            if (currentCampaign.getCampaignId().equals(campaignId)) {
                return currentCampaign;
            }
        }
        return null;
    }

    @Override
    public List<Campaign> loadCampaignByUserId(String userId) {
        ListIterator<Campaign> iterator = dbMock.listIterator();
        while (iterator.hasNext()) {
            Campaign currentCampaign = iterator.next();
            if (currentCampaign.getUserSegmentId().equals(userId)) {
                return Arrays.asList(currentCampaign);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public int update(String campaignId, Campaign campaignToUpdate) {
        Campaign campaign = loadCampaign(campaignId);
        if (campaign != null) {
            return 1;
        }
        return -1;
    }

    @Override
    public int delete(String campaignId) {
        Campaign campaign = loadCampaign(campaignId);
        if (campaign != null) {
            return 1;
        }
        return -1;
    }

}
