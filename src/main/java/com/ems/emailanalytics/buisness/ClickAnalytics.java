package com.ems.emailanalytics.buisness;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;

public class ClickAnalytics implements Analytics{
    @Override
    public boolean performAnalytics(String clickId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence)  {

            EmailDetails fetchedEmailDetail=emailDetails.loadEmailDetailsByClickId(emailDetailsPersistence,clickId);
            if(fetchedEmailDetail==null){
                return false;
            }
            fetchedEmailDetail.numberOfTimesClicked=fetchedEmailDetail.numberOfTimesClicked+1;
            return fetchedEmailDetail.saveEmailDetails(emailDetailsPersistence);
    }
}
