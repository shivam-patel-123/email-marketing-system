package com.ems.subscriberlist.model;

import com.ems.subscriberlist.persistence.ISubscriberPersistence;

import java.util.List;
import java.util.UUID;

public class Subscriber {
    public String sub_id;
    public String sub_email;
    public String sub_first_name;
    public String sub_last_name;
    public String sub_location;
    public String subscription_date;
    public String sub_status;
    public Subscriber()
    {

    }
    public Subscriber(String sub_email, String sub_first_name, String sub_last_name, String sub_location, String subscription_date ){
        setSub_id(generateId());
        setSub_email(sub_email);
        setSub_first_name(sub_first_name);
        setSub_last_name(sub_last_name);
        setSub_location(sub_location);
        setSubscription_date(subscription_date);
        setSub_status(sub_status);
    }

    private String generateId() {

        return "S-" + UUID.randomUUID();
    }
    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }
    public String getSub_email() {
        return sub_email;
    }
    public void setSub_email(String sub_email) {
        this.sub_email = sub_email;
    }
    public String getSub_first_name() {
        return sub_first_name;
    }
    public void setSub_first_name(String sub_first_name) {
        this.sub_first_name = sub_first_name;
    }
    public String getSub_last_name() {
        return sub_last_name;
    }
    public void setSub_last_name(String sub_last_name) {
        this.sub_last_name = sub_last_name;
    }
    public String getSub_location() {
        return sub_location;
    }
    public void setSub_location(String sub_location) {
        this.sub_location = sub_location;
    }
    public String getSubscription_date() {
        return subscription_date;
    }
    public void setSubscription_date(String subscription_date) {
        this.subscription_date = subscription_date;
    }
    public String getSub_status() {
        return sub_status;
    }
    public void setSub_status(String sub_status) {
        this.sub_status = "Active";
    }
    public List<Subscriber> loadSubscriber (ISubscriberPersistence persistence) throws Exception {
        return persistence.loadSubscriber(this);
    }
    public int saveSubscriber (ISubscriberPersistence persistence) throws Exception {
        return persistence.saveSubscriber(this);
    }
    public int saveSubcriberAndUserSegmentID(ISubscriberPersistence persistence,String UserSegmentId) throws Exception{
        return persistence.saveSubcriberAndUserSegmentID(this, UserSegmentId);
    }

    public Subscriber getSubscriberBySubscriberId(ISubscriberPersistence persistence, String subscriberId){
        return persistence.getSubscriberBySubscriberId(subscriberId);
    }
}
