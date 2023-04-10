package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.EmailTemplate;
import com.ems.emailtemplate.model.Template;

//the class represent a simple email without any analytics
public class SimpleEmail extends Mail{

    @Override
    public void generateSubject(Template template) {
    subject=((EmailTemplate)template).getTemplateSubject();
    }

    @Override
    public void generateBody(Template template) {
        body=body+"<p>"+ ((EmailTemplate)template).getTemplateDescription()+"<p>";
    }

}
