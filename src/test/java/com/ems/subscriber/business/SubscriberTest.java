package com.ems.subscriber.business;

import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberlist.model.Subscriber;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriberTest {
    private static Subscriber subscriberInformation;
    private static ISubscriberPersistence subscriberPersistence;

    @BeforeAll
    public static void init(){
        subscriberInformation = new Subscriber();
        subscriberPersistence = new SubscriberDbMock();
    }
    @Test
    public void initTest(){
        Subscriber subscriber = new Subscriber("jaivik.tailor2804@gmail.com","Jaivik",
                "Tailor","London","2022-08-07 12:10:10");
        assertNotNull(subscriber);
    }

    @Test
    public void saveSubsciberSuccessTest() throws Exception {
        Subscriber subscriberdetails = new Subscriber();
        subscriberdetails.sub_id ="S-0442977d-e64d-448a-8005-eac45d134496";
        int response = subscriberdetails.saveSubscriber(subscriberPersistence);
        assertEquals(1,response);
    }
    @Test
    public void saveSubscriberFailTest() throws Exception {
        Subscriber subscriberdetails = new Subscriber();
        subscriberdetails.sub_id ="xyz";
        int response = subscriberdetails.saveSubscriber(subscriberPersistence);
        assertEquals(-1,response);
    }
    @Test
    public void saveSubcriberAndUserSegmentIDSuccessTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id = "S-0442977d-e64d-448a-8005-eac45d134496";
        String UserId = "us-2ab0469f-4e1c-45ed-bad5-419e90371a09";
        int response = subscriber.saveSubcriberAndUserSegmentID(subscriberPersistence,"us-2ab0469f-4e1c-45ed-bad5-419e90371a09");
        assertEquals(1,response);
    }
    @Test
    public void saveSubcriberAndUserSegmentIDFailTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id = "xyz";
        int response = subscriber.saveSubcriberAndUserSegmentID(subscriberPersistence,"");
        assertEquals(-1,response);
    }
    @Test
    public void saveSubcriberAndUserSegmentIDfailTest() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id = "S-0442977d-e64d-448a-8005-eac45d134496";
        int response = subscriber.saveSubcriberAndUserSegmentID(subscriberPersistence,"");
        assertEquals(-1,response);
    }
    @Test
    public void getSubscribeIdSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSub_id("S-0442977d-e64d-448a-8005-eac45d134496");
        String expectedSubscriber = subscriber.getSub_id();
        assertEquals(expectedSubscriber,"S-0442977d-e64d-448a-8005-eac45d134496");
    }
    @Test
    public void SetSubsciberIdSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriberId = subscriber.getSub_id();
        subscriber.setSub_id("S-0442977d-e64d-448a-8005-eac45d134496");
        String expectedSubscriberId = subscriber.getSub_id();
        assertNotEquals(expectedSubscriberId,subscriberId);
    }
    @Test
    public void getSubscriberEmailSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSub_email("jaivik.tailor2804@gmail.com");
        String expectedSubscriber = subscriber.getSub_email();
        assertEquals(expectedSubscriber,"jaivik.tailor2804@gmail.com");
    }
    @Test
    public void SetSubsciberEmailSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriberEmail = subscriber.getSub_email();
        subscriber.setSub_email("jaivik.tailor2804@gmail.com");
        String expectedSubscriberEmail = subscriber.getSub_email();
        assertNotEquals(expectedSubscriberEmail,subscriberEmail);
    }
    @Test
    public void getSubscriberFirstNameSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSub_first_name("Jaivik");
        String expectedSubscriberFirstName = subscriber.getSub_first_name();
        assertEquals(expectedSubscriberFirstName,"Jaivik");
    }
    @Test
    public void SetSubsciberFirstNameSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriberFirstName = subscriber.getSub_first_name();
        subscriber.setSub_first_name("Jaivik");
        String expectedSubscriberFirstName = subscriber.getSub_first_name();
        assertNotEquals(expectedSubscriberFirstName,subscriberFirstName);
    }
    @Test
    public void getSubscriberLastNameSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSub_last_name("Tailor");
        String expectedSubscriberLastName = subscriber.getSub_last_name();
        assertEquals(expectedSubscriberLastName,"Tailor");
    }
    @Test
    public void SetSubsciberLastNameSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriberLastName= subscriber.getSub_last_name();
        subscriber.setSub_last_name("Tailor");
        String expectedSubscriberLastName = subscriber.getSub_last_name();
        assertNotEquals(expectedSubscriberLastName,subscriberLastName);
    }
    @Test
    public void getSubscriberLocationSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSub_location("London");
        String expectedSubscriberLocation = subscriber.getSub_location();
        assertEquals(expectedSubscriberLocation,"London");
    }
    @Test
    public void SetSubsciberLocationSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriberLocation= subscriber.getSub_location();
        subscriber.setSub_location("London");
        String expectedSubscriberLocation = subscriber.getSub_location();
        assertNotEquals(expectedSubscriberLocation,subscriberLocation);
    }
    @Test
    public void getSubscriptionDateSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSubscription_date("2022-08-07 12:10:10");
        String expectedSubscriptionDate = subscriber.getSubscription_date();
        assertEquals(expectedSubscriptionDate,"2022-08-07 12:10:10");
    }
    @Test
    public void SetSubscriptionDateSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriptionDate= subscriber.getSubscription_date();
        subscriber.setSubscription_date("2022-08-07 12:10:10");
        String expectedSubscriptionDate = subscriber.getSubscription_date();
        assertNotEquals(expectedSubscriptionDate,subscriptionDate);
    }
    @Test
    public void getSubscriptionStatusSuccessTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.setSub_status("Active");
        String expectedSubscriptionStatus = subscriber.getSub_status();
        assertEquals(expectedSubscriptionStatus,"Active");
    }
    @Test
    public void SetSubscriptionStatusSuccesTest(){
        Subscriber subscriber = new Subscriber();
        String subscriptionStatus= subscriber.getSub_status();
        subscriber.setSub_status("Active");
        String expectedSubscriptionStatus = subscriber.getSub_status();
        assertNotEquals(expectedSubscriptionStatus,subscriptionStatus);
    }
    @Test
    public void loadSubsciberSuccessTest() throws Exception {
        List<Subscriber> subscriber = subscriberInformation.loadSubscriber(subscriberPersistence);
        assertNotNull(subscriber);
    }
}
