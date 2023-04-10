package com.ems.emailtemplate.model;

public class EmailTemplateFactory implements ITemplateFactory {

    @Override
    public EmailTemplate createSimpleEmailTemplate(String templateName) {
        return new SimpleEmailTemplate(templateName);
    }

    @Override
    public EmailTemplate createSimpleEmailTemplate() {
        return new SimpleEmailTemplate();
    }

    @Override
    public EmailTemplate createSimpleEmailTemplate(String id, String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        return new SimpleEmailTemplate(id, templateName, templateSubject, templateDescription, landingPageLink);
    }
}
