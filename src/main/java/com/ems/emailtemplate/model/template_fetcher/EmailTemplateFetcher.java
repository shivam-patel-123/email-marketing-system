package com.ems.emailtemplate.model.template_fetcher;

import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateFailureState;
import com.ems.emailtemplate.model.template_state.TemplateNotFoundState;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.model.template_state.TemplateSuccessState;
import com.ems.emailtemplate.persistent.ITemplatePersistent;

import java.util.List;

public class EmailTemplateFetcher implements ITemplateFetcher {
    ITemplatePersistent emailPersistent;

    public EmailTemplateFetcher(ITemplatePersistent emailPersistent) {
        this.emailPersistent = emailPersistent;
    }

    @Override
    public TemplateState fetchAllTemplates() {
        List<Template> templates = emailPersistent.loadAllTemplates();
        if (templates == null) {
            return new TemplateFailureState("Fail to fetch templates.");
        } else {
            return new TemplateSuccessState(templates);
        }
    }

    @Override
    public TemplateState fetchTemplate(String templateId) {
        Template emailTemplate = emailPersistent.loadTemplateById(templateId);
        if (emailTemplate == null) {
            return new TemplateNotFoundState("Template Requested doesn't exist. Please check the template id twice.");
        } else {
            return new TemplateSuccessState(emailTemplate);
        }
    }

    @Override
    public TemplateState fetchAllTemplateByUserId(User user) {
        List<Template> templates = emailPersistent.loadAllTemplateByUserId(user);
        if (templates == null) {
            return new TemplateFailureState("Fail to fetch template with the given user");
        } else {
            return new TemplateSuccessState(templates);
        }
    }
}
