package com.ems.campaign.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.bulkemail.persistence.EmailDetailsDb;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.CampaignAnalytics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestController()
@RequestMapping("/api/analytics/campaign")
public class CampaignAnalysticsController {

    @GetMapping("/")
    public CampaignAnalytics getCampaignSubscribers(HttpServletRequest request){
            String campaignId = request.getParameter("campaign_id");

            try{
                IEmailDetailsPersistence emailDetailsPersistence = new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection());
                return  new CampaignAnalytics().getCampaignAnalytics(emailDetailsPersistence, campaignId);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
}
