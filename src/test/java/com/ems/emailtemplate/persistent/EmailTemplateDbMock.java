package com.ems.emailtemplate.persistent;

import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.emailtemplate.model.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class EmailTemplateDbMock implements ITemplatePersistent {
    private final List<Template> mockDB = new ArrayList<>();

    public EmailTemplateDbMock() {
        mockDB.add(new SimpleEmailTemplate("1", "Summer Sales Template", "Wearables 50% sales", "Description will go here", "https://www.zara.com/shop/summer"));
        mockDB.add(new SimpleEmailTemplate("2", "Black Firday Template", "Wearables 50% sales", "Description will go here", "https://www.allensolly.com/black-friday"));
        mockDB.add(new SimpleEmailTemplate("3", "New Collection Template", "Men and Women Clothes available", "Description will go here", "https://www.ham.com/collections"));
    }

    @Override
    public int save(Template template, String userId) {
        Template templateInDB = findTemplateById(template.getTemplateId());
        if (templateInDB == null) {
            return 1;
        }
        return -1;
    }

    private Template findTemplateById(String id) {
        ListIterator<Template> iterator = mockDB.listIterator();
        while (iterator.hasNext()) {
            Template currentTemplate = iterator.next();
            if (currentTemplate.getTemplateId().equals(id)) {
                return currentTemplate;
            }
        }
        return null;
    }

    @Override
    public List<Template> loadAllTemplates() {
        return mockDB;
    }

    @Override
    public Template loadTemplateById(String templateId) {
        return findTemplateById(templateId);
    }

    @Override
    public List<Template> loadAllTemplateByUserId(User user) {
        return Arrays.asList(mockDB.get(0), mockDB.get(1));
    }

    @Override
    public int update(String templateId, Template updatedTemplate) {
        Template template = findTemplateById(templateId);
        if (template == null) {
            return -1;
        }
        return 1;
    }

    @Override
    public int delete(String templateId) {
        Template template = findTemplateById(templateId);
        if (template == null) {
            return -1;
        }
        return 1;
    }
}
