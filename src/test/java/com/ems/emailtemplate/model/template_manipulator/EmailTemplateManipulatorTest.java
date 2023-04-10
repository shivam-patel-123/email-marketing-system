package com.ems.emailtemplate.model.template_manipulator;

import com.ems.emailtemplate.persistent.EmailTemplateDbMock;
import com.ems.emailtemplate.model.SimpleEmailTemplate;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_state.TemplateState;
import com.ems.emailtemplate.persistent.ITemplatePersistent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EmailTemplateManipulatorTest {
    private static ITemplateManipulatorFactory templateManipulatorFactory;
    private static ITemplatePersistent emailPersistent;

    @BeforeAll
    public static void init() {
        templateManipulatorFactory = new EmailTemplateManipulatorFactory();
        emailPersistent = new EmailTemplateDbMock();
    }

    @Test
    public void updateTemplateSuccessTest() {
        ITemplateManipulator emailTemplateManipulator = templateManipulatorFactory.createTemplateManipulator(emailPersistent);
        Template updatedEmailTemplate = new SimpleEmailTemplate("1", "Summer Sales Template", "Wearables 50% sales", "Description will go here", "https://www.zara.com/shop/summer");
        TemplateState actualState = emailTemplateManipulator.updateTemplate("1", updatedEmailTemplate);

        assertEquals(HttpStatus.OK, actualState.getStatus());
        assertEquals(1, actualState.getData());
    }

    @Test
    public void updateTemplateNotFoundTest() {
        ITemplateManipulator emailTemplateManipulator = templateManipulatorFactory.createTemplateManipulator(emailPersistent);
        Template updatedEmailTemplate = new SimpleEmailTemplate("1", "Summer Sales Template", "Wearables 50% sales", "Description will go here", "https://www.zara.com/shop/summer");
        TemplateState actualState = emailTemplateManipulator.updateTemplate("12", updatedEmailTemplate);

        assertEquals(HttpStatus.NOT_FOUND, actualState.getStatus());
    }

    @Test
    public void deleteTemplateSuccessTest() {
        ITemplateManipulator emailTemplateManipulator = templateManipulatorFactory.createTemplateManipulator(emailPersistent);
        TemplateState actualState = emailTemplateManipulator.deleteTemplate("1");

        assertEquals(HttpStatus.OK, actualState.getStatus());
        assertEquals(1, actualState.getData());
    }

    @Test
    public void deleteTemplateNotFoundTest() {
        ITemplateManipulator emailTemplateManipulator = templateManipulatorFactory.createTemplateManipulator(emailPersistent);
        TemplateState actualState = emailTemplateManipulator.deleteTemplate("11");

        assertEquals(HttpStatus.BAD_REQUEST, actualState.getStatus());
    }
}
