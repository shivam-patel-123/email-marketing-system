package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.Template;

public abstract class Mail {
    public String pixelId;
    public String clickId;
    public String subject;
    public String body;

    protected Mail(){
        body="";
    }
    public abstract void generateSubject(Template template);
    public abstract void generateBody(Template template);

}
