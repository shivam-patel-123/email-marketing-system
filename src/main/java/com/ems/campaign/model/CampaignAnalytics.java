package com.ems.campaign.model;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.subscriberlist.model.Subscriber;

import java.util.*;

public class CampaignAnalytics {
    private int subscribersCount;
    private List<EmailDetails> emailDetailsList;
    private int emailClicks;
    private Double conversionRate;
    private Double unsubscribeRate;
    private Double clickThroughRate;

    public CampaignAnalytics() {
        conversionRate = 0.0;
        unsubscribeRate = 0.0;
        clickThroughRate = 0.0;
    }

    public CampaignAnalytics(Double conversionRate, Double unsubscribeRate, Double clickThroughRate) {
        this.conversionRate = conversionRate;
        this.unsubscribeRate = unsubscribeRate;
        this.clickThroughRate = clickThroughRate;
    }

    @Override
    public String toString() {
        return "CampaignAnalytics{" +
                "subscribersCount=" + subscribersCount +
                ", emailClicks=" + emailClicks +
                ", conversionRate=" + conversionRate +
                ", unsubscribeRate=" + unsubscribeRate +
                ", clickThroughRate=" + clickThroughRate +
                '}';
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Double getUnsubscribeRate() {
        return unsubscribeRate;
    }

    public void setUnsubscribeRate(Double unsubscribeRate) {
        this.unsubscribeRate = unsubscribeRate;
    }

    public Double getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(Double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public int getSubscribersCount() { return subscribersCount; }

    public List<EmailDetails> getEmailDetailsList() {
        return emailDetailsList;
    }

    public void setEmailDetailsList(List<EmailDetails> emailDetailsList) {
        this.emailDetailsList = emailDetailsList;
    }
    public void setSubscribersCount(int subscribersCount) { this.subscribersCount = subscribersCount; }

    public int getEmailClicks() { return emailClicks; }

    public void setEmailClicks(int emailClicks) { this.emailClicks = emailClicks; }

    public CampaignAnalytics getCampaignAnalytics(IEmailDetailsPersistence emailDetailsPersistence , String campaignId){
            CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
            List<EmailDetails> emailDetailsList = emailDetailsPersistence.getAllEmailDetailsOfCampaign(campaignId);
            List<Subscriber> subscriberList = getSubscribersListFromEmailDetails(emailDetailsList);

            if(subscriberList.size() > 0){
                campaignAnalytics.setSubscribersCount(subscriberList.size());
                campaignAnalytics.setEmailDetailsList(emailDetailsList);
                campaignAnalytics.setEmailClicks(getEmailOpenCountFromEmailDetails(emailDetailsList));
                campaignAnalytics.setConversionRate(calculateCampaignConversionRate(emailDetailsList));
                campaignAnalytics.setClickThroughRate(calculateEmailClickThroughRate(emailDetailsList));
                campaignAnalytics.setUnsubscribeRate(calculateUnsubscribeRate(emailDetailsList));
            }
            return campaignAnalytics;
    }

    private List<Subscriber> getSubscribersListFromEmailDetails(List<EmailDetails> emailDetailsList){
        List<Subscriber> subscriberList = new ArrayList<>();
        for(EmailDetails emailDetail: emailDetailsList){
            subscriberList.add(emailDetail.subscriber);
        }
        return subscriberList;
    }

    private double calculateCampaignConversionRate(List<EmailDetails> emailDetailsList){
        int emailOpenCount = getEmailOpenCountFromEmailDetails(emailDetailsList);
        int totalSubscribers = emailDetailsList.size();

        return ((double) emailOpenCount / totalSubscribers) * 100;
    }

    private int getEmailOpenCountFromEmailDetails(List<EmailDetails> emailDetailsList){
        int count = 0;
        for(EmailDetails emailDetail : emailDetailsList){
            if(emailDetail.numberOfTimesOpened > 0){
                count++;
            }
        }
        return count;
    }

    private double calculateEmailClickThroughRate(List<EmailDetails> emailDetailsList){
        int subscriberCount = emailDetailsList.size();
        int linkClickCount = 0;
        for(EmailDetails emailDetail : emailDetailsList){
            if(emailDetail.numberOfTimesClicked > 0){
                linkClickCount++;
            }
        }
        return ((double) linkClickCount / subscriberCount) * 100;
    }

    private double calculateUnsubscribeRate(List<EmailDetails> emailDetailsList){
        int subscriberCount = emailDetailsList.size();
        int unsubscribersCount = 0;

        for(EmailDetails emailDetail : emailDetailsList){
            if(!emailDetail.subscriber.sub_status.equals("Active")){
                unsubscribersCount++;
            }
        }
        return ((double)unsubscribersCount / subscriberCount) * 100;
    }
}
