package com.ems.emailtemplate.model.template_fetcher;


import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.template_state.TemplateState;

public interface ITemplateFetcher {
    TemplateState fetchAllTemplates();
    TemplateState fetchTemplate(String templateId);
    TemplateState fetchAllTemplateByUserId(User user);
}
