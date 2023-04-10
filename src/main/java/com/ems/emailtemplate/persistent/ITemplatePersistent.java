package com.ems.emailtemplate.persistent;

import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.Template;

import java.util.List;

public interface ITemplatePersistent {
    int save(Template template, String userId);
    List<Template> loadAllTemplates();
    Template loadTemplateById(String templateId);
    List<Template> loadAllTemplateByUserId(User user);
    int update(String templateId, Template updatedTemplate);
    int delete(String templateId);
}
