package com.ems.emailtemplate.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.EmailTemplate;
import com.ems.emailtemplate.model.EmailTemplateFactory;
import com.ems.emailtemplate.model.template_fetcher.EmailTemplateFetcherFactory;
import com.ems.emailtemplate.model.template_fetcher.ITemplateFetcher;
import com.ems.emailtemplate.model.template_fetcher.ITemplateFetcherFactory;
import com.ems.emailtemplate.model.template_generator.EmailTemplateGeneratorFactory;
import com.ems.emailtemplate.model.template_generator.ITemplateGeneratorFactory;
import com.ems.emailtemplate.model.template_generator.TemplateGenerator;
import com.ems.emailtemplate.model.template_manipulator.EmailTemplateManipulatorFactory;
import com.ems.emailtemplate.model.template_manipulator.ITemplateManipulator;
import com.ems.emailtemplate.model.template_manipulator.ITemplateManipulatorFactory;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.persistent.EmailTemplateDb;
import com.ems.emailtemplate.persistent.ITemplatePersistent;
import com.ems.responsegenerator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class EmailTemplateController {

    private EmailTemplateFactory emailTemplateFactory;
    private ITemplatePersistent emailPersistent;

    public EmailTemplateController() {
        emailTemplateFactory = new EmailTemplateFactory();
        emailPersistent = new EmailTemplateDb(getConnectionObject());
    }

    private Connection getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @RequestMapping(value = "/email-template", method = RequestMethod.POST)
    public void createEmailTemplate(HttpServletRequest request, HttpServletResponse httpServletResponse, HttpSession session) throws IOException {
        ITemplateGeneratorFactory generatorFactory = new EmailTemplateGeneratorFactory();
        User user = (User) session.getAttribute("user");

        List<String> fieldNamesList = new ArrayList<>(Arrays.asList("name", "subject", "description", "landingPageLink"));
        Map<String, String> usersDataMap = extractDataFromJson(fieldNamesList, request);

        EmailTemplate emailTemplate = emailTemplateFactory.createSimpleEmailTemplate(usersDataMap.get("name"));
        emailTemplate.setTemplateSubject(usersDataMap.get("subject"));
        emailTemplate.setTemplateDescription(usersDataMap.get("description"));
        emailTemplate.setLandingPageLink(usersDataMap.get("landingPageLink"));

        TemplateGenerator generator = generatorFactory.createTemplateGenerator(emailPersistent);
        TemplateState state = generator.createNewTemplate(emailTemplate, user.userId);
        ResponseGenerator<JsonNode> response = state.getResponse();
        response.setDataLabel("rows_inserted");

        if ((Integer) response.getData() > 0) {
            httpServletResponse.sendRedirect("/email-template");
        } else {
            httpServletResponse.sendRedirect("/create-email-template-error");
        }
    }

    @GetMapping("/email-template")
    public JsonNode getAllEmailTemplate() {
        ITemplateFetcherFactory fetcherFactory = new EmailTemplateFetcherFactory();
        ITemplateFetcher templateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState state = templateFetcher.fetchAllTemplates();
        ResponseGenerator<JsonNode> response = state.getResponse();
        return response.sendResponse();
    }

    @GetMapping("/email-template/{id}")
    public JsonNode getTemplate(@PathVariable String id) {
        ITemplateFetcherFactory fetcherFactory = new EmailTemplateFetcherFactory();
        ITemplateFetcher templateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState state = templateFetcher.fetchTemplate(id);
        ResponseGenerator<JsonNode> response = state.getResponse();
        return response.sendResponse();
    }

    @PutMapping("/email-template/{id}")
    public void updateEmailTemplate(@PathVariable String id, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        ITemplateManipulatorFactory manipulatorFactory = new EmailTemplateManipulatorFactory();
        ITemplateManipulator templateManipulator = manipulatorFactory.createTemplateManipulator(emailPersistent);

        String templateName = request.getParameter("name");
        String templateSubject = request.getParameter("subject");
        String templateDescription = request.getParameter("description");
        String landingPageLink = request.getParameter("landingPageLink");

        EmailTemplate templateToUpdate = emailTemplateFactory.createSimpleEmailTemplate(templateName);
        templateToUpdate.setTemplateSubject(templateSubject);
        templateToUpdate.setTemplateDescription(templateDescription);
        templateToUpdate.setLandingPageLink(landingPageLink);

        TemplateState state = templateManipulator.updateTemplate(id, templateToUpdate);
        int data = (Integer) state.getData();
        if (data > 0) {
            httpServletResponse.sendRedirect("/email-template");
        }
    }

    @DeleteMapping("/email-template/{id}")
    public JsonNode deleteEmailTemplate(@PathVariable String id) {
        ITemplateManipulatorFactory manipulatorFactory = new EmailTemplateManipulatorFactory();
        ITemplateManipulator templateManipulator = manipulatorFactory.createTemplateManipulator(emailPersistent);

        TemplateState state = templateManipulator.deleteTemplate(id);
        ResponseGenerator<JsonNode> responseGenerator = state.getResponse();
        responseGenerator.setDataLabel("rows_deleted");
        return responseGenerator.sendResponse();
    }

    private Map<String, String> extractDataFromJson(List<String> fieldNames, HttpServletRequest body) {
        Map<String, String> dataMap = new HashMap<>();
        ListIterator<String> iterator = fieldNames.listIterator();
        while (iterator.hasNext()) {
            String columnName = iterator.next();
            dataMap.put(columnName, body.getParameter(columnName));
        }
        return dataMap;
    }
}
