package com.ems.bulkemail.buisness;

import com.ems.bulkemail.persistence.IEmailDetailsPersistence;

import java.util.UUID;

public class SimpleEmailDetails extends EmailDetails {


    public void generateId(){
        this.id= "m-"+UUID.randomUUID().toString();
    }
    public EmailDetails loadEmailDetailsByPixelId(IEmailDetailsPersistence emailDetailsPersistence,String pixelId){
        return emailDetailsPersistence.loadEmailDetailsByPixelId(pixelId);
    }

    public EmailDetails loadEmailDetailsByClickId(IEmailDetailsPersistence emailDetailsPersistence, String clickId){
        return emailDetailsPersistence.loadEmailDetailsByClickId(clickId);
    }
    public boolean saveEmailDetails(IEmailDetailsPersistence emailDetailsPersistence){
        return emailDetailsPersistence.saveEmailDetails(this);
    }
    public boolean createEmailDetails(IEmailDetailsPersistence emailDetailsPersistence){
        return emailDetailsPersistence.createEmailDetails(this);
    }



}
