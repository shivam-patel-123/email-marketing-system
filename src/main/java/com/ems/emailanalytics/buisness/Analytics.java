package com.ems.emailanalytics.buisness;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;

public interface Analytics {
    public boolean performAnalytics(String analyticsId, EmailDetails emailDetails, IEmailDetailsPersistence emailDetailsPersistence);
}
