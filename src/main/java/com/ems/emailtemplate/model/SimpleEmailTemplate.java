package com.ems.emailtemplate.model;

public class SimpleEmailTemplate extends EmailTemplate {

    public SimpleEmailTemplate() {
        super();
    }

    public SimpleEmailTemplate(String templateName) {
        super(templateName);
    }

    public SimpleEmailTemplate(String templateId, String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        super(templateId, templateName, templateSubject, templateDescription, landingPageLink);
    }
}
