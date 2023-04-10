package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.Template;

import java.util.UUID;

public class PixelDecorator extends SimpleEmailDecorator{
    public PixelDecorator(Mail wrappedMail) {
        super(wrappedMail);
    }

    //adds wrapped mails body with  pixel and pixel  analytics to the mail
    @Override
    public void generateBody(Template template) {
        wrappedMail.generateBody(template);
        String generatedPixel=generatePixel();
        this.body=wrappedMail.body+generatedPixel;
        this.clickId=wrappedMail.clickId;
    }
    private String generatePixel(){
        this.pixelId="p-"+ UUID.randomUUID();
        return "<img src='http://localhost:8080/analytics/pixel?pixelid="+this.pixelId+"'  width='1' height='1'>";
    }
}
