package com.ems.emailtemplate.model.template_generator;

import com.ems.emailtemplate.persistent.ITemplatePersistent;

public class EmailTemplateGeneratorFactory implements ITemplateGeneratorFactory {
    @Override
    public TemplateGenerator createTemplateGenerator(ITemplatePersistent emailPersistent) {
        return new EmailTemplateGenerator(emailPersistent);
    }
}
