package com.ems.subscriberlist.persistence;

import com.ems.subscriberlist.model.Subscriber;

import java.util.List;

public interface ISubscriberPersistence {
    public List<Subscriber> loadSubscriber(Subscriber subscriber) throws Exception;
    public int saveSubscriber(Subscriber saveSubscriber) throws Exception;
    public int saveSubcriberAndUserSegmentID(Subscriber saveSubscriberwithUserSegmentID, String userSegmentId) throws Exception;
    public List<Subscriber> getSubscriberByCampaignId(String campaignId) throws Exception;
    public List<Subscriber> getSubscriberByUserId(String userId) throws Exception;
    Subscriber getSubscriberBySubscriberId(String subscriberId);
}
