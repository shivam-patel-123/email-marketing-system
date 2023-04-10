package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.subscriberlist.model.Subscriber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleEmailDetailBuilderTest {
    private static EmailDetailBuilder emailDetailBuilder;

    @BeforeAll
    public static void init(){
        emailDetailBuilder= new SimpleEmailDetailBuilder();
    }
    @Test
    public void BuildEmailDetailNotNullTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
        EmailDetails emailDetails= emailDetailBuilder.buildEmailDetail(subscriber);
        assertNotNull(emailDetails);

    }
    @Test
    public void BuildEmailDetailObjectTest(){
        Subscriber subscriber = new Subscriber();
        subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
        EmailDetails emailDetails= emailDetailBuilder.buildEmailDetail(subscriber);
        assertEquals(emailDetails.subscriber.sub_id,subscriber.sub_id);

    }

    @Test
    public void BuildMailTestSubject(){
        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");
        template.setLandingPageLink("www.google.com");
        Mail mail= emailDetailBuilder.buildEmail(template,new HtmlFormatter());
        assertEquals(mail.subject,template.getTemplateSubject());
    }

    @Test
    public void BuildMailDecoratorAnalyticsTest(){
        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");
        template.setLandingPageLink("www.google.com");
        Mail mail= emailDetailBuilder.buildEmail(template,new HtmlFormatter());
        assertNotNull(mail.clickId);
        assertNotNull(mail.pixelId);

    }
}
