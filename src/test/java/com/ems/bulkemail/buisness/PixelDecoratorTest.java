package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.SimpleEmailTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PixelDecoratorTest {

    private static PixelDecorator pixelDecorator;

    @BeforeAll
    public static void init(){
        Mail mail=new SimpleEmail();
        pixelDecorator=new PixelDecorator(mail);
    }


    @Test
    public void generateBodyTest(){
        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");
        template.setTemplateDescription("hello");
        pixelDecorator.generateBody(template);
        assertNotNull(pixelDecorator.pixelId);
        assertNotNull(pixelDecorator.body);
    }
}
