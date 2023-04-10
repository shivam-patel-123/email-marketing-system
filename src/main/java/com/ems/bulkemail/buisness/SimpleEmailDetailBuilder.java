package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.Template;
import com.ems.subscriberlist.model.Subscriber;



//builder pattern used to initiate SimpleEmailDetail object as it is a complex process
public class SimpleEmailDetailBuilder extends EmailDetailBuilder{
    @Override
    public EmailDetails buildEmailDetail(Subscriber subscriber) {
        EmailDetails emailDetails=new SimpleEmailDetails();
        emailDetails.generateId();
        emailDetails.subscriber=subscriber;
        return emailDetails;
    }
    @Override
    public Mail buildEmail(Template template, IFormatMail emailFormatter) {
        Mail mail = new SimpleEmail();
        //decorated simple email with clickRateDecorator
        Mail emailWithClickRateAnalytics=new ClickRateDecorator(mail);
        //decorated ClickRateDecorator with pixelDecorator
        Mail emailWithPixelAnalytics=new PixelDecorator(emailWithClickRateAnalytics);
        emailWithPixelAnalytics.generateSubject(template);
        emailWithPixelAnalytics.generateBody(template);
        emailWithPixelAnalytics.clickId=emailWithClickRateAnalytics.clickId;
        //formatting the body of the email with html
        emailWithPixelAnalytics.body=emailFormatter.formatMail(emailWithPixelAnalytics.body);
        return emailWithPixelAnalytics;
    }
}
