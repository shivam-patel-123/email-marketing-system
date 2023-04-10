package com.ems.emailtemplate.model.template_fetcher;

import com.ems.authentication.model.User;
import com.ems.emailtemplate.persistent.EmailTemplateDbMock;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.persistent.ITemplatePersistent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EmailTemplateFetcherTest {
    private static ITemplateFetcherFactory fetcherFactory;
    private static ITemplatePersistent emailPersistent;

    @BeforeAll
    public static void init() {
        fetcherFactory = new EmailTemplateFetcherFactory();
        emailPersistent = new EmailTemplateDbMock();
    }

    @Test
    public void fetchAllTemplatesSuccessTest() {
        ITemplateFetcher emailTemplateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState actualState = emailTemplateFetcher.fetchAllTemplates();
        List<Template> actualData = (List<Template>) actualState.getData();

        assertEquals(HttpStatus.OK, actualState.getStatus());
        assertEquals(3, actualData.size());
    }

    @Test
    public void fetchAllTemplateFailureTest() {
        ITemplateFetcher emailTemplateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState actualState = emailTemplateFetcher.fetchAllTemplates();
        assertNotEquals(HttpStatus.BAD_REQUEST, actualState.getStatus());
    }

    @Test
    public void fetchTemplateSuccessTest() {
        ITemplateFetcher emailTemplateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState actualState = emailTemplateFetcher.fetchTemplate("1");
        Template actualData = (Template) actualState.getData();

        assertEquals(HttpStatus.OK, actualState.getStatus());
        assertEquals("1", actualData.getTemplateId());
    }

    @Test
    public void fetchTemplateNotFoundTest() {
        ITemplateFetcher emailTemplateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState actualState = emailTemplateFetcher.fetchTemplate("12");
//        Template actualData = (Template) actualState.getData();

        assertEquals(HttpStatus.NOT_FOUND, actualState.getStatus());
    }

    @Test
    public void fetchAllTemplateByUserIdTest() {
        ITemplateFetcher emailTemplateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState state = emailTemplateFetcher.fetchAllTemplateByUserId(new User("shivampatel@gmail.com", "password"));

        List<Template> templates = (List<Template>) state.getData();

        assertEquals(2, templates.size());
    }
}
