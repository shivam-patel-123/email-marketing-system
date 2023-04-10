package com.ems.bulkemail.buisness;

import com.ems.emailtemplate.model.Template;
import com.ems.subscriberlist.model.Subscriber;

public abstract class EmailDetailBuilder {
    public abstract EmailDetails buildEmailDetail(Subscriber subscriber);
    public abstract Mail buildEmail(Template template,IFormatMail emailformatter);

}
