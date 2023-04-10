package com.ems.emailtemplate.model.template_manipulator;


import com.ems.emailtemplate.persistent.ITemplatePersistent;

public class EmailTemplateManipulatorFactory implements ITemplateManipulatorFactory {

    @Override
    public ITemplateManipulator createTemplateManipulator(ITemplatePersistent templatePersistent) {
        return new EmailTemplateManipulator(templatePersistent);
    }
}
