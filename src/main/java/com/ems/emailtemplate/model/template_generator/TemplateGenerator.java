package com.ems.emailtemplate.model.template_generator;


import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateState;

public interface TemplateGenerator {
    TemplateState createNewTemplate(Template template, String userId);
}
