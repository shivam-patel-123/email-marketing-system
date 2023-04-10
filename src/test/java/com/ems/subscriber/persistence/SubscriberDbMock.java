package com.ems.subscriber.persistence;

import com.ems.subscriberlist.model.Subscriber;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;

import java.util.ArrayList;
import java.util.List;

public class SubscriberDbMock implements ISubscriberPersistence {


    @Override
    public int saveSubscriber(Subscriber saveSubscriber) throws Exception {
        if(saveSubscriber.sub_id.equals("S-0442977d-e64d-448a-8005-eac45d134496")){
            return 1;
        }
        else if(saveSubscriber.sub_id.equals("xyz"))
        {
            return -1;
        }
        return -1;
    }

    @Override
    public int saveSubcriberAndUserSegmentID(Subscriber saveSubscriberwithUserSegmentID, String UserSegmentId) throws Exception {
        if( saveSubscriberwithUserSegmentID.sub_id.equals("S-0442977d-e64d-448a-8005-eac45d134496") &&
        UserSegmentId.equals("us-2ab0469f-4e1c-45ed-bad5-419e90371a09"))
        {
            return 1;
        }
        else if(saveSubscriberwithUserSegmentID.sub_id.equals("xyz") ||
        UserSegmentId.equals("")){
            return -1;
        }
        else if (saveSubscriberwithUserSegmentID.sub_id.equals("S-0442977d-e64d-448a-8005-eac45d134496") &&
        UserSegmentId.equals(""))
        {
            return -1;
        }
        return -1;

    }

    @Override
    public List<Subscriber> getSubscriberByCampaignId(String campaignId) throws Exception {
        List<Subscriber> totalSubsciber= new ArrayList<>();
        if(campaignId.equals("c-fbfae4ab-0af8-41d5-81bc-cdf916780bc5")){
            Subscriber firstsubscriber = new Subscriber();
            firstsubscriber.sub_id = "S-da19dc04-7409-4fb3-beb4-93b2508b8d37";
            firstsubscriber.sub_email = "jaivik.tailor2804@gmail.com";
            firstsubscriber.sub_first_name ="Jaivik";
            firstsubscriber.sub_last_name = "Tailor";
            Subscriber secondsubscriber = new Subscriber();
            secondsubscriber.sub_id = "S-0d48f727-ea39-4805-a791-9824224a8360";
            secondsubscriber.sub_email ="jp6126@gmail.com";
            secondsubscriber.sub_first_name = "Lav 2";
            secondsubscriber.sub_last_name ="patel";
            totalSubsciber.add(firstsubscriber);
            totalSubsciber.add(secondsubscriber);
            
            return totalSubsciber;
            
        } else if (campaignId.equals("xyz")) {
            return null;
        }
        return null;
    }

    @Override
    public List<Subscriber> getSubscriberByUserId(String userId) throws Exception {
        List<Subscriber> totalSubsciber= new ArrayList<>();
        if(userId.equals("369b91e4-800c-4bd0-b094-13a364fee990"))
        {
            Subscriber firstsubscriber = new Subscriber();
            firstsubscriber.sub_id = "S-da19dc04-7409-4fb3-beb4-93b2508b8d37";
            firstsubscriber.sub_email = "jaivik.tailor2804@gmail.com";
            firstsubscriber.sub_first_name ="Jaivik";
            firstsubscriber.sub_last_name = "Tailor";
            Subscriber secondsubscriber = new Subscriber();
            secondsubscriber.sub_id = "S-0d48f727-ea39-4805-a791-9824224a8360";
            secondsubscriber.sub_email ="jp6126@gmail.com";
            secondsubscriber.sub_first_name = "Lav 2";
            secondsubscriber.sub_last_name ="patel";
            totalSubsciber.add(firstsubscriber);
            totalSubsciber.add(secondsubscriber);

            return totalSubsciber;
        }
        else if (userId.equals("xyz")) {
            return null;
        }
        return null;

    }

    @Override
    public Subscriber getSubscriberBySubscriberId(String subscriberId) {
        return null;
    }

    @Override
    public List<Subscriber> loadSubscriber(Subscriber subscriber) throws Exception {
        Subscriber subscriber1 = new Subscriber();
        List<Subscriber> totalSubsciber= new ArrayList<>();
        if(subscriber1 != null)
        {
            return totalSubsciber;

        } else if (subscriber1 == null) {
            return null;
        }
        return null;
    }
}
