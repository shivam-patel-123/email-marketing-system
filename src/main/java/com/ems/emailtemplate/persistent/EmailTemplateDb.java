package com.ems.emailtemplate.persistent;

import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.EmailTemplate;
import com.ems.emailtemplate.model.EmailTemplateFactory;
import com.ems.emailtemplate.model.ITemplateFactory;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.exception.TemplateNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmailTemplateDb implements ITemplatePersistent {
    private Connection connection;

    private final String EMAIL_TEMPLATE_ID = "template_id";
    private final String EMAIL_TEMPLATE_NAME = "template_name";
    private final String EMAIL_TEMPLATE_SUBJECT = "template_subject";
    private final String EMAIL_TEMPLATE_DESCRIPTION = "template_description";
    private final String LANDING_PAGE_LINK = "landing_page_link";
    private final String USER_ID = "user_id";

    public EmailTemplateDb(Connection connection) {
        this.connection = connection;
    }

    private ResultSet load(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public int save(Template template, String userId) {
        EmailTemplate emailTemplate = (EmailTemplate) template;
        try {
            Statement statement = connection.createStatement();
            String createTemplateQuery = "INSERT INTO email_template VALUES (" +
                    "\"" + emailTemplate.getTemplateId() + "\", " +
                    "\"" + emailTemplate.getTemplateName() + "\", " +
                    "\"" +emailTemplate.getTemplateSubject() + "\", " +
                    "\"" +emailTemplate.getTemplateDescription() + "\", " +
                    "\"" +emailTemplate.getLandingPageLink() + "\", " +
                    "\"" + userId + "\")";
            int numOfRowsInserted = statement.executeUpdate(createTemplateQuery);
            return numOfRowsInserted;
        } catch (SQLException e)  {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Template> loadAllTemplates() {
        List<Template> templates = new ArrayList<>();
        try {
            String selectAllTemplateQuery = "SELECT * FROM email_template";
            ResultSet result = load(selectAllTemplateQuery);
            EmailTemplate emailTemplate;
            while (result.next()) {
                String templateId = result.getString(EMAIL_TEMPLATE_ID);
                String templateName = result.getString(EMAIL_TEMPLATE_NAME);
                String templateSubject = result.getString(EMAIL_TEMPLATE_SUBJECT);
                String templateDescription = result.getString(EMAIL_TEMPLATE_DESCRIPTION);
                String landingPageLink = result.getString(LANDING_PAGE_LINK);

                ITemplateFactory templateFactory = new EmailTemplateFactory();
                emailTemplate = templateFactory.createSimpleEmailTemplate(templateName);
                emailTemplate.setTemplateId(templateId);
                emailTemplate.setTemplateName(templateName);
                emailTemplate.setTemplateSubject(templateSubject);
                emailTemplate.setTemplateDescription(templateDescription);
                emailTemplate.setLandingPageLink(landingPageLink);

                templates.add(emailTemplate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return templates;
    }

    public Template loadTemplateById(String id) {
        EmailTemplate emailTemplate = null;
        try {
            String selectTemplateQuery = "SELECT * FROM email_template WHERE template_id = \"" + id + "\"";
            ResultSet result = load(selectTemplateQuery);

            if (result.isBeforeFirst()) {
                while(result.next()) {
                    ITemplateFactory templateFactory = new EmailTemplateFactory();
                    emailTemplate = templateFactory.createSimpleEmailTemplate();
                    emailTemplate.setTemplateId(result.getString(EMAIL_TEMPLATE_ID));
                    emailTemplate.setTemplateName(result.getString(EMAIL_TEMPLATE_NAME));
                    emailTemplate.setTemplateSubject(result.getString(EMAIL_TEMPLATE_SUBJECT));
                    emailTemplate.setTemplateDescription(result.getString(EMAIL_TEMPLATE_DESCRIPTION));
                    emailTemplate.setLandingPageLink(result.getString(LANDING_PAGE_LINK));
                }
                return emailTemplate;
            } else {
                throw new TemplateNotFoundException("Template Doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Template> loadAllTemplateByUserId(User user) {
        List<Template> templates = new ArrayList<>();
        try {
            String selectAllTemplateByUserIdQuery = "SELECT * FROM email_template WHERE " + USER_ID + " = \"" + user.userId + "\"";
            ResultSet result = load(selectAllTemplateByUserIdQuery);
            EmailTemplate emailTemplate;
            while (result.next()) {
                String templateId = result.getString(EMAIL_TEMPLATE_ID);
                String templateName = result.getString(EMAIL_TEMPLATE_NAME);
                String templateSubject = result.getString(EMAIL_TEMPLATE_SUBJECT);
                String templateDescription = result.getString(EMAIL_TEMPLATE_DESCRIPTION);
                String landingPageLink = result.getString(LANDING_PAGE_LINK);

                ITemplateFactory templateFactory = new EmailTemplateFactory();
                emailTemplate = templateFactory.createSimpleEmailTemplate(templateName);
                emailTemplate.setTemplateId(templateId);
                emailTemplate.setTemplateName(templateName);
                emailTemplate.setTemplateSubject(templateSubject);
                emailTemplate.setTemplateDescription(templateDescription);
                emailTemplate.setLandingPageLink(landingPageLink);
                emailTemplate.setUser(user);
                templates.add(emailTemplate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return templates;
    }

    public int update(String templateId, Template updatedTemplate) {
        EmailTemplate updatedEmailTemplate = (EmailTemplate) updatedTemplate;
        try {
            Statement statement = connection.createStatement();
            String updateTemplateQuery = "UPDATE email_template SET " +
                    EMAIL_TEMPLATE_NAME + " = \"" + updatedEmailTemplate.getTemplateName() + "\", " +
                    EMAIL_TEMPLATE_SUBJECT + " = \"" + updatedEmailTemplate.getTemplateSubject() + "\", " +
                    EMAIL_TEMPLATE_DESCRIPTION + " = \"" + updatedEmailTemplate.getTemplateDescription() + "\", " +
                    LANDING_PAGE_LINK + " = \"" + updatedEmailTemplate.getLandingPageLink() + "\" " +
                    "WHERE " + EMAIL_TEMPLATE_ID + " = \"" + templateId + "\"";
            int numOfRowsUpdated = statement.executeUpdate(updateTemplateQuery);
            if (numOfRowsUpdated > 0) {
                return numOfRowsUpdated;
            } else {
                throw new TemplateNotFoundException("Template not found with the id " + templateId);
            }
        } catch (SQLException e)  {
            e.printStackTrace();
            return -1;
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String templateId) {
        try {
            Statement statement = connection.createStatement();
            String deleteTemplateQuery = "DELETE FROM email_template WHERE template_id = \"" + templateId + "\"";
            int numOfRowsDeleted = statement.executeUpdate(deleteTemplateQuery);
            if (numOfRowsDeleted > 0) {
                return numOfRowsDeleted;
            } else {
                throw new TemplateNotFoundException("Template not found with the id " + templateId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
