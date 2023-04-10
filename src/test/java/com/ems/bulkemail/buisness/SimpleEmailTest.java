package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.SimpleEmailTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleEmailTest {
    private static SimpleEmail simpleEmail;

    @BeforeAll
    public static void init(){
        simpleEmail= new SimpleEmail();
    }
    @Test
    public void generateSubjectTest(){
        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");

        simpleEmail.generateSubject(template);
        assertEquals(template.getTemplateSubject(),simpleEmail.subject);

    }

    @Test
    public void generateBodyTest(){
        SimpleEmailTemplate template = new SimpleEmailTemplate();
        template.setTemplateId("123");
        template.setTemplateName("test Template");
        template.setTemplateSubject("test subject");
        template.setTemplateDescription("hello");

        simpleEmail.generateBody(template);
        String expectedOutput="<p>"+template.getTemplateDescription()+"<p>";
        assertEquals(expectedOutput,simpleEmail.body);

    }

}
