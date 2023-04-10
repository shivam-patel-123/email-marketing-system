package com.ems.bulkemail.persistence;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.buisness.Mail;
import com.ems.bulkemail.buisness.SimpleEmail;
import com.ems.bulkemail.buisness.SimpleEmailDetails;
import com.ems.subscriberlist.model.Subscriber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailDetailDbMock implements IEmailDetailsPersistence{


    @Override
    public boolean saveEmailDetails(EmailDetails emailDetails) {
        if (emailDetails.id.equals("abc")){
            return true;
        }
        else if(emailDetails.id.equals("xyz")){
            return false;
        }
        else if(emailDetails.id.equals("m-10b0b470-3928-4a31-8d4e-21609061c344")){

            return true;
        }
        return false;
    }

    @Override
    public boolean createEmailDetails(EmailDetails emailDetails) {
        if (emailDetails.id.equals("abc")){
            return true;
        }
        else if(emailDetails.id.equals("xyz")){
            return false;
        }
        else if(emailDetails.id.equals("m-10b0b470-3928-4a31-8d4e-21609061c344")){
            return true;
        }
        return false;
    }

    @Override
    public EmailDetails loadEmailDetailsByPixelId(String pixelId) {
        if (pixelId.equals("p-26b52502-93bc-48de-9383-655e97009014")){
            EmailDetails emailDetails1=new SimpleEmailDetails();
            emailDetails1.id="m-10b0b470-3928-4a31-8d4e-21609061c344";
            emailDetails1.sentTime=new Date();
            emailDetails1.openedTime=new Date();
            Subscriber subscriber= new Subscriber();
            subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
            emailDetails1.subscriber=subscriber;
            emailDetails1.campaignId="c-bad6fd90-7cee-4f0d-80b2-e4fb91e5b97c";
            emailDetails1.numberOfTimesClicked=0;
            emailDetails1.numberOfTimesOpened=2;
            Mail mail1= new SimpleEmail();
            mail1 .clickId="cl-0bc3bb0a-82ee-4550-a49f-c7b24bce9389";
            mail1.pixelId="p-26b52502-93bc-48de-9383-655e97009014";
            emailDetails1.mail=mail1;
            return emailDetails1;
        }
        else if(pixelId.equals("xyz")){
            return null;
        }
        return null;
    }

    @Override
    public EmailDetails loadEmailDetailsByClickId(String clickId) {
        if (clickId.equals("cl-0bc3bb0a-82ee-4550-a49f-c7b24bce9389")){
            EmailDetails emailDetails1=new SimpleEmailDetails();
            emailDetails1.id="m-10b0b470-3928-4a31-8d4e-21609061c344";
            emailDetails1.sentTime=new Date();
            emailDetails1.openedTime=new Date();
            Subscriber subscriber= new Subscriber();
            subscriber.sub_id="S-0442977d-e64d-448a-8005-eac45d134496";
            emailDetails1.subscriber=subscriber;
            emailDetails1.campaignId="c-bad6fd90-7cee-4f0d-80b2-e4fb91e5b97c";
            emailDetails1.numberOfTimesClicked=0;
            emailDetails1.numberOfTimesOpened=2;
            Mail mail1= new SimpleEmail();
            mail1 .clickId="cl-0bc3bb0a-82ee-4550-a49f-c7b24bce9389";
            mail1.pixelId="p-26b52502-93bc-48de-9383-655e97009014";
            emailDetails1.mail=mail1;
            return emailDetails1;
        }
        else if(clickId.equals("xyz")){
            return null;
        }
        return null;
    }

    @Override
    public List<EmailDetails> getAllEmailDetailsOfCampaign(String campaignId) {
        if(campaignId.equals("")){
            return null;
        }

        if(campaignId.equals("fcd211e1-7fa1-45f7-8aca-0b9cecbf8987")){
            List<EmailDetails> emailDetailsList = new ArrayList<>();
            emailDetailsList.add(new SimpleEmailDetails());
            emailDetailsList.add(new SimpleEmailDetails());
            emailDetailsList.add(new SimpleEmailDetails());

            return emailDetailsList;
        }

        return null;
    }
}
