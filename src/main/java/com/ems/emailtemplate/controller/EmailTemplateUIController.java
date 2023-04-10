package com.ems.emailtemplate.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.emailtemplate.model.EmailTemplate;
import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_fetcher.EmailTemplateFetcherFactory;
import com.ems.emailtemplate.model.template_fetcher.ITemplateFetcher;
import com.ems.emailtemplate.model.template_fetcher.ITemplateFetcherFactory;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.persistent.EmailTemplateDb;
import com.ems.emailtemplate.persistent.ITemplatePersistent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping(value = "/")
public class EmailTemplateUIController {

    @GetMapping("/email-template")
    public String emailTemplateList(Model model, HttpSession session) throws SQLException {
        ITemplatePersistent templatePersistent = new EmailTemplateDb(MySqlPersistenceConnection.getInstance().getConnection());
        ITemplateFetcherFactory  fetcherFactory = new EmailTemplateFetcherFactory();
        ITemplateFetcher fetcher = fetcherFactory.createTemplateFetcher(templatePersistent);

        User user = (User) session.getAttribute("user");
        TemplateState state = fetcher.fetchAllTemplateByUserId(user);
        List<Template> templatesList = (List<Template>) state.getData();

        model.addAttribute("something", "dynamic value");
        model.addAttribute("emailTemplates", templatesList);
        return "emailTemplate";
    }

    @GetMapping("/create-email-template")
    public String emailTemplateForm() {
        return "emailTemplateForm";
    }

    @GetMapping("/create-email-template-error")
    public String createEmailTemplateError() {
        return "emailTemplateFormError";
    }

    @GetMapping("/update-email-template/{id}")
    public String updateEmailTemplate(Model model, @PathVariable String id) throws JsonProcessingException {
        EmailTemplateController controller = new EmailTemplateController();
        String jsonString = controller.getTemplate(id).toString();

        JsonNode node = new ObjectMapper().readTree(jsonString).get("data");
        String templateId = node.get("templateId").asText();
        String name = node.get("templateName").asText();
        String subject = node.get("templateSubject").asText();
        String description = node.get("templateDescription").asText();
        String landingPageLink = node.get("landingPageLink").asText();
        EmailTemplate template = new SimpleEmailTemplate(templateId, name, subject, description, landingPageLink);

        List<EmailTemplate> list = new ArrayList<>();
        list.add(template);

        model.addAttribute("templateList", list);
        return "emailTemplateUpdateForm";
    }
}
