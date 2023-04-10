package com.ems.bulkemail.buisness;

import com.ems.bulkemail.persistence.EmailDetailDbMock;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.bulkemail.smtp.GmailMock;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.CampaignEmailScheduler;
import com.ems.campaign.model.SimpleCampaign;
import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.subscriber.persistence.SubscriberDbMock;
import com.ems.subscriberlist.model.SimpleSubscriberList;
import com.ems.subscriberlist.model.SubscriberList;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SimpleBulkEmailTest {

    private static SimpleBulkEmail simpleBulkEmail;

    private static Campaign campaign;

   @BeforeAll
    public static void init(){
       campaign=new SimpleCampaign();
       campaign.setCampaignId("c-fbfae4ab-0af8-41d5-81bc-cdf916780bc5");
       SimpleEmailTemplate template = new SimpleEmailTemplate();
       template.setTemplateId("123");
       template.setTemplateName("test Template");
       template.setTemplateSubject("test subject");
       template.setLandingPageLink("www.google.com");
       campaign.setEmailTemplate(template);
        SubscriberList subscriberList=new SimpleSubscriberList();
        ISendEmail emailSmtp=new GmailMock();
        IEmailDetailsPersistence emailDetailsPersistence= new EmailDetailDbMock();
        ISubscriberPersistence subscriberPersistence = new SubscriberDbMock();
        simpleBulkEmail=new SimpleBulkEmail(campaign,subscriberList,emailSmtp,emailDetailsPersistence,subscriberPersistence);
    }
    @Test
    public void UpdateTest(){
       simpleBulkEmail.update(new CampaignEmailScheduler(campaign));

    }

}
