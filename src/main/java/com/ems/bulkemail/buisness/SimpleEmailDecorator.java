package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.Template;

public abstract class SimpleEmailDecorator extends Mail {
    protected  Mail wrappedMail;
    SimpleEmailDecorator(Mail wrappedMail){
         this.wrappedMail=wrappedMail;
    }
    public  void generateSubject(Template template){
        wrappedMail.generateSubject(template);
        this.subject = wrappedMail.subject;
    }
    public  void generateBody(Template template){
        wrappedMail.generateBody(template);
    }


}
