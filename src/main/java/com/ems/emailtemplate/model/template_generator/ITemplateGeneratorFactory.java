package com.ems.emailtemplate.model.template_generator;

import com.ems.emailtemplate.persistent.ITemplatePersistent;

public interface ITemplateGeneratorFactory {
    TemplateGenerator createTemplateGenerator(ITemplatePersistent emailPersistent);
}
