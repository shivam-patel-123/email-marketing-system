package com.ems.emailanalytics.buisness;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;

import java.util.Date;

public class PixelAnalytics implements Analytics {

    @Override
    public boolean performAnalytics(String pixelId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence) {

        EmailDetails fetchedEmailDetail;
        fetchedEmailDetail=emailDetails.loadEmailDetailsByPixelId(emailDetailsPersistence,pixelId);
        if (fetchedEmailDetail==null){
            return false;
        }
        fetchedEmailDetail.openedTime=new Date();
        fetchedEmailDetail.numberOfTimesOpened=fetchedEmailDetail.numberOfTimesOpened+1;
        return fetchedEmailDetail.saveEmailDetails(emailDetailsPersistence);

    }
}
