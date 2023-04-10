package com.ems.subscriber.business;

import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberlist.model.SimpleSubscriberList;
import com.ems.subscriberlist.model.Subscriber;
import com.ems.subscriberlist.model.SubscriberList;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SubscriberListTest {
    private static SubscriberList subscriberInformation;
    private static ISubscriberPersistence subscriberPersistence;

    @BeforeAll
    public static void init(){
        subscriberInformation = new SimpleSubscriberList();
        subscriberPersistence = new SubscriberDbMock();
    }

    @Test
    public void getSubscriberByCampaignIdSuccessTest() throws Exception {
        List<Subscriber> subscriberByCampaignId = subscriberInformation.getSubscriberByCampaignID("c-fbfae4ab-0af8-41d5-81bc-cdf916780bc5",subscriberPersistence);
        assertEquals(2,subscriberByCampaignId.size());
    }

    @Test
    public void getSubsciberByCampaignIdFailTest() throws Exception {
        List<Subscriber> subscribersByCompaignId = subscriberInformation.getSubscriberByCampaignID("xyz",subscriberPersistence);
        assertNull(subscribersByCompaignId);
    }

    @Test
    public void getSubscriberByUserId() throws Exception {
        List<Subscriber> subscriberByCampaignId = subscriberInformation.getSubscriberByUserID("369b91e4-800c-4bd0-b094-13a364fee990",subscriberPersistence);
        assertEquals(2,subscriberByCampaignId.size());
    }

    @Test
    public void getSubsciberByUserIdFailTest() throws Exception {
        List<Subscriber> subscribersByCompaignId = subscriberInformation.getSubscriberByUserID("xyz",subscriberPersistence);
        assertNull(subscribersByCompaignId);
    }


}
