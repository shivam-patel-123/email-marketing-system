package com.ems.emailtemplate.model.template_generator;

import com.ems.emailtemplate.persistent.EmailTemplateDbMock;
import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.persistent.ITemplatePersistent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailTemplateGeneratorTest {
    private static ITemplateGeneratorFactory fetcherFactory;
    private static ITemplatePersistent emailPersistent;

    @BeforeAll
    public static void init() {
        fetcherFactory = new EmailTemplateGeneratorFactory();
        emailPersistent = new EmailTemplateDbMock();
    }

    @Test
    public void createNewTemplateSuccessTest() {
        TemplateGenerator templateGenerator = fetcherFactory.createTemplateGenerator(emailPersistent);
        Template template = new SimpleEmailTemplate("4", "Black Friday Sale Template", "ALl products 50% sales", "Description will go here", "https://www.hnm.com/black-friday");
        TemplateState actualState = templateGenerator.createNewTemplate(template, "1");
        int actualNumOfRowsInserted = (Integer) actualState.getData();

        assertEquals(HttpStatus.CREATED, actualState.getStatus());
        assertEquals(1, actualNumOfRowsInserted);
    }

    @Test
    public void createNewTemplateFailureTest() {
        TemplateGenerator templateGenerator = fetcherFactory.createTemplateGenerator(emailPersistent);
        Template template = new SimpleEmailTemplate("1", "Black Friday Sale Template", "ALl products 50% sales", "Description will go here", "https://www.hnm.com/black-friday");
        TemplateState actualState = templateGenerator.createNewTemplate(template, "1");

        assertEquals(HttpStatus.BAD_REQUEST, actualState.getStatus());
    }
}
