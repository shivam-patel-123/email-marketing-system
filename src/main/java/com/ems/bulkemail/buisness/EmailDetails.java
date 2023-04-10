package com.ems.bulkemail.buisness;

import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.subscriberlist.model.Subscriber;

import java.util.Date;

public abstract class EmailDetails {


    public Mail mail;
    public Subscriber subscriber;
    public Date sentTime;
    public Date openedTime;
    public String campaignId;
    public String id;
    public int numberOfTimesOpened;
    public int numberOfTimesClicked;

    public abstract void generateId();

    public abstract EmailDetails loadEmailDetailsByPixelId(IEmailDetailsPersistence emailDetailsPersistence, String pixelId);

    public abstract EmailDetails loadEmailDetailsByClickId(IEmailDetailsPersistence emailDetailsPersistence, String clickId);

    public abstract boolean saveEmailDetails(IEmailDetailsPersistence emailDetailsPersistence);

    public abstract boolean createEmailDetails(IEmailDetailsPersistence emailDetailsPersistence);
}
