package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.SimpleEmailTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClickDecoratorTest {
    private static ClickRateDecorator clickRateDecorator;

    @BeforeAll
    public static void init(){
        Mail mail=new SimpleEmail();
        clickRateDecorator=new ClickRateDecorator(mail);
    }
    @Test
    public void generateBodyTest(){

        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");
        template.setTemplateDescription("hello");
        template.setLandingPageLink("www.google.com");
        clickRateDecorator.generateBody(template);
        assertNotNull(clickRateDecorator.clickId);
        assertNotNull(clickRateDecorator.body);
    }
}
