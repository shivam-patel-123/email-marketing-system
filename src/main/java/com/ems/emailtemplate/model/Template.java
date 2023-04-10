package com.ems.emailtemplate.model;

import com.ems.authentication.model.User;

import java.util.UUID;

public abstract class Template {
    private String templateId;
    private String templateName;
    private User user;

    public Template() {
        this.templateId = generateId();
        this.user = new User();
    }

    public Template(String templateName) {
        this.templateId = generateId();
        this.templateName = templateName;
        this.user = new User();
    }

    public Template(String templateId, String templateName) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.user = new User();
    }

    private String generateId() {
        return "t-" + UUID.randomUUID();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        if (templateId != null) {
            this.templateId = templateId;
        }
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        if (templateName != null) {
            this.templateName = templateName;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
