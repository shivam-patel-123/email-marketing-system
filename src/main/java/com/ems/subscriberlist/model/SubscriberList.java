package com.ems.subscriberlist.model;

import com.ems.subscriberlist.persistence.ISubscriberPersistence;

import java.util.List;

public abstract class SubscriberList {
    public  abstract List<Subscriber> getSubscriberByCampaignID(String campaignId, ISubscriberPersistence subscriber) throws Exception;
    public  abstract List<Subscriber>  getSubscriberByUserID(String userId,ISubscriberPersistence subscriber)throws Exception;
}
