package com.ems.emailtemplate.model;

import com.ems.authentication.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleEmailTemplateTest {
    private static ITemplateFactory templateGeneratorFactory;
    private static Template template;
    private static EmailTemplate emailTemplate;

    @BeforeAll
    public static void init() {
        templateGeneratorFactory = new EmailTemplateFactory();
        template = templateGeneratorFactory.createSimpleEmailTemplate("1", "Summer Sales Template", "Wearables 50% sales", "Description will go here", "https://www.zara.com/shop/summer");
        emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate("1", "Black Friday Sales Template", "30% off on t-shirts", "Amazing Description", "https://www.hnm.com/black-friday");
    }

    @Test
    public void getTemplateIdSuccessTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate("Template 1");
        String templateId = template.getTemplateId();

        assertTrue(templateId.startsWith("t-"));
    }

    @Test
    public void getTemplateIdIncorrectTest() {
        template.setTemplateId("33rre-uyfj3-8585h-095hf-947fhj");
        String templateId = template.getTemplateId();

        assertFalse(templateId.startsWith("t-"));
    }

    @Test
    public void getTemplateNameSuccessTest() {
        String templateName = template.getTemplateName();

        assertEquals("Summer Sales Template", templateName);
    }

    @Test
    public void getTemplateNameIncorrectTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate("Incorrect Template");
        String templateName = template.getTemplateName();

        assertNotEquals("Template 1", templateName);
    }

    @Test
    public void setTemplateNameSuccessTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate();
        template.setTemplateName("Black Friday Sale Template");

        assertEquals("Black Friday Sale Template", template.getTemplateName());
    }

    @Test
    public void setTemplateNameIncorrectTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate();
        template.setTemplateName("Correct Template Name");

        assertNotEquals("Incorrect Template Name", template.getTemplateName());
    }

    @Test
    public void setTemplateIdCorrectTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate();
        template.setTemplateId("33333-rrrrr-99999-ooooo");

        assertEquals("33333-rrrrr-99999-ooooo", template.getTemplateId());
    }

    @Test
    public void setTemplateIdIncorrectTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate();
        template.setTemplateId("33333-rrrrr-99999-ooooo");

        assertNotEquals("11111-ee3e3-u6u6u-6c6c6", template.getTemplateId());
    }

    @Test
    public void getUserSuccessTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate();
        template.setUser(new User("shivampatel@gmail.com", "very-secret"));
        User actualUser = template.getUser();

        assertEquals("shivampatel@gmail.com", actualUser.email);
    }

    @Test
    public void getUserIncorrectTest() {
        Template template = templateGeneratorFactory.createSimpleEmailTemplate();
        template.setUser(new User("shivampatel123@gmail.com", "very-secret"));
        User actualUser = template.getUser();

        assertNotEquals("incorrect.email@gmail.com", actualUser.email);
    }

    @Test
    public void getTemplateSubjectSuccessTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate("Summer Sale");
        emailTemplate.setTemplateSubject("30% off on t-shirts");

        assertEquals("30% off on t-shirts", emailTemplate.getTemplateSubject());
    }

    @Test
    public void getTemplateSubjectIncorrectSubject() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate("Summer Sale");
        emailTemplate.setTemplateSubject("30% off on t-shirts");

        assertNotEquals("Invalid Subject", emailTemplate.getTemplateSubject());
    }

    @Test
    public void getTemplateDescriptionSuccessTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateDescription("Description will go here");

        assertEquals("Description will go here", emailTemplate.getTemplateDescription());
    }

    @Test
    public void getTemplateDescriptionIncorrectTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateDescription("Incorrect Description");

        assertNotEquals("New Correct Description", emailTemplate.getTemplateDescription());
    }

    @Test
    public void getTemplateLandingPageLinkSuccessTest() {
        EmailTemplate emailTempalte = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTempalte.setLandingPageLink("https://www.zara.com/ca");

        assertEquals("https://www.zara.com/ca", emailTempalte.getLandingPageLink());
    }

    @Test
    public void getTemplateLandingPageLinkIncorrectTest() {
        EmailTemplate emailTempalte = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTempalte.setLandingPageLink("https://www.yourdomain.com/ca");

        assertNotEquals("https://www.zara.com/ca", emailTempalte.getLandingPageLink());
    }

    @Test
    public void setTemplateSubjectSuccessTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateSubject("Amazing Subject");

        assertEquals("Amazing Subject", emailTemplate.getTemplateSubject());
    }


    @Test
    public void setTemplateSubjectIncorrectTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateSubject("Amazing Subject");

        assertNotEquals("Incorrect Subject", emailTemplate.getTemplateSubject());
    }

    @Test
    public void setTemplateDescriptionSuccessTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateDescription("Amazing Description");

        assertEquals("Amazing Description", emailTemplate.getTemplateDescription());
    }

    @Test
    public void setTemplateDescriptionInocrrectTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateDescription("Amazing Description");

        assertNotEquals("Incorrect Description", emailTemplate.getTemplateDescription());
    }

    @Test
    public void setTemplateLandingPageLinkSuccessTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateDescription("https://hnm.com/black-friday");

        assertEquals("https://hnm.com/black-friday", emailTemplate.getTemplateDescription());
    }

    @Test
    public void setTemplateLandingPageIncorrectTest() {
        EmailTemplate emailTemplate = templateGeneratorFactory.createSimpleEmailTemplate();
        emailTemplate.setTemplateDescription("https://www.hnm.com/black-friday");

        assertNotEquals("https://www.incorrect.com/sale", emailTemplate.getTemplateDescription());
    }
}
