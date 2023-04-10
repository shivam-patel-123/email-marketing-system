package com.ems.emailtemplate.model.template_manipulator;

import com.ems.emailtemplate.persistent.ITemplatePersistent;

public interface ITemplateManipulatorFactory {
    ITemplateManipulator createTemplateManipulator(ITemplatePersistent templatePersistent);
}
