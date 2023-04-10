package com.ems.bulkemail.buisness;


import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.model.Subject;
import com.ems.subscriberlist.model.Subscriber;
import com.ems.subscriberlist.model.SubscriberList;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;


import java.util.ArrayList;
import java.util.Date;

import java.util.List;

public class SimpleBulkEmail extends BulkEmail{

    private final IEmailDetailsPersistence emailDetailsPersistence;
    private final  ISubscriberPersistence subscriberPersistence;
    public SimpleBulkEmail(Campaign campaign, SubscriberList subscriberList,ISendEmail emailSmtp,IEmailDetailsPersistence emailDetailsPersistence,ISubscriberPersistence subscriberPersistence ){
        this.campaign=campaign;
        this.subscriberList=subscriberList;
        this.emailSmtp=emailSmtp;
        this.emailDetailsList=new ArrayList<>();
        this.emailDetailsPersistence=emailDetailsPersistence;
        this.subscriberPersistence=subscriberPersistence;

    }

    @Override
    public void update(Subject s) {
        emailDetailsList=generateEmailDetailList();
        for(int i=0;i<emailDetailsList.size();i++){
           EmailDetails emailDetail=emailDetailsList.get(i);
           if (sendEmail(emailDetail,emailSmtp)){
               emailDetail.sentTime= new Date();
               emailDetail.createEmailDetails(emailDetailsPersistence);
           }
        }
    }
    private List<EmailDetails> generateEmailDetailList(){
        try {
            subscribers=subscriberList.getSubscriberByCampaignID(campaign.getCampaignId(),subscriberPersistence);
        } catch (Exception e) {
             e.printStackTrace();
        }
        List<EmailDetails> emailList=new ArrayList<>();
        EmailDetailBuilder emailDetailBuilder= new SimpleEmailDetailBuilder();
        for (Subscriber subscriber:subscribers){
            EmailDetails emailDetails=emailDetailBuilder.buildEmailDetail(subscriber);
            emailDetails.campaignId=campaign.getCampaignId();
            emailDetails.mail= emailDetailBuilder.buildEmail(campaign.getEmailTemplate(),new HtmlFormatter());
            emailList.add(emailDetails);
        }
        return emailList;
    }
    private boolean sendEmail(EmailDetails emailDetail,ISendEmail emailSmtp){
        return emailSmtp.sendEmail(emailDetail);
    }
}
