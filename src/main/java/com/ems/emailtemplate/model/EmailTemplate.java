package com.ems.emailtemplate.model;

public abstract class EmailTemplate extends Template {
    private String templateSubject;
    private String templateDescription;
    private String landingPageLink;

    public EmailTemplate() {
        super();
    }

    public EmailTemplate(String templateName){
        super(templateName);
    }

    public EmailTemplate(String templateId, String templateName, String templateSubject, String templateDescription, String landingPageLink) {
        super(templateId, templateName);
        this.templateSubject = templateSubject;
        this.templateDescription = templateDescription;
        this.landingPageLink = landingPageLink;
    }

    public String getTemplateSubject() {
        return templateSubject;
    }

    public void setTemplateSubject(String templateSubject) {
        if (templateSubject != null) {
            this.templateSubject = templateSubject;
        }
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        if (templateDescription != null) {
            this.templateDescription = templateDescription;
        }
    }

    public String getLandingPageLink() {
        return landingPageLink;
    }

    public void setLandingPageLink(String landingPageLink) {
        if (landingPageLink != null) {
            this.landingPageLink = landingPageLink;
        }
    }
}
