package com.ems.bulkemail.buisness;

import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.Campaign;
import com.ems.subscriberlist.model.SubscriberList;
import com.ems.subscriberlist.persistence.ISubscriberPersistence;

public interface IBulkEmailFactory {
    public BulkEmail createBulkEmail(Campaign campaign, SubscriberList subscriberList, ISendEmail emailSmtp, IEmailDetailsPersistence emailDetailsPersistence, ISubscriberPersistence subscriberPersistence);
}
