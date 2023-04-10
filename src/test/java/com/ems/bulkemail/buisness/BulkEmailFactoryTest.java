package com.ems.bulkemail.buisness;

import com.ems.bulkemail.persistence.EmailDetailDbMock;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.bulkemail.smtp.GmailMock;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.SimpleCampaign;
import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberlist.model.SimpleSubscriberList;
import com.ems.subscriberlist.model.SubscriberList;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BulkEmailFactoryTest {
    private static IBulkEmailFactory bulkEmailFactory;

    private static  Campaign campaign;

    private static SubscriberList subscriberList;

    private static ISendEmail emailSmtp;

    private static  IEmailDetailsPersistence emailDetailsPersistence;

    private static ISubscriberPersistence subscriberPersistence;



    @BeforeAll
    public static void init(){
        bulkEmailFactory= new BulkEmailFactory();

        campaign=new SimpleCampaign();
        campaign.setCampaignId("c-fbfae4ab-0af8-41d5-81bc-cdf916780bc5");
        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");
        template.setLandingPageLink("www.google.com");
        campaign.setEmailTemplate(template);
        subscriberList=new SimpleSubscriberList();
        emailSmtp=new GmailMock();
        emailDetailsPersistence= new EmailDetailDbMock();
        subscriberPersistence = new SubscriberDbMock();

    }

    @Test
    public  void createBulkEmailTest(){
        BulkEmail bulkEmail = bulkEmailFactory.createBulkEmail(campaign,subscriberList,emailSmtp,emailDetailsPersistence,subscriberPersistence);
        assertNotNull(bulkEmail);
    }


}
