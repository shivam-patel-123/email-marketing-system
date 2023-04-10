package com.ems.emailtemplate.model.template_generator;

import com.ems.emailtemplate.model.EmailTemplate;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateFailureState;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.model.template_state.TemplateSuccessState;
import com.ems.emailtemplate.persistent.ITemplatePersistent;

import org.springframework.http.HttpStatus;

public class EmailTemplateGenerator implements TemplateGenerator {
    private final ITemplatePersistent emailPersistent;

    public EmailTemplateGenerator(ITemplatePersistent emailPersistent) {
        this.emailPersistent = emailPersistent;
    }

    @Override
    public TemplateState createNewTemplate(Template template, String userId) {
        EmailTemplate emailTemplate = (EmailTemplate) template;
        int numOfRowsInserted = emailPersistent.save(emailTemplate, userId);
        if (numOfRowsInserted > 0) {
            return new TemplateSuccessState(HttpStatus.CREATED, numOfRowsInserted);
        } else {
            return new TemplateFailureState("Error while inserting template");
        }
    }
}
