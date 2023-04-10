package com.ems.emailtemplate.model.template_manipulator;

import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateState;

public interface ITemplateManipulator {
    TemplateState updateTemplate(String templateId, Template templateToUpdate);
    TemplateState deleteTemplate(String templateId);
}
