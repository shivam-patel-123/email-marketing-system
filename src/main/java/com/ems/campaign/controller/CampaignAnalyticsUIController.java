package com.ems.campaign.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.bulkemail.persistence.EmailDetailsDb;
import com.ems.bulkemail.persistence.IEmailDetailsPersistence;
import com.ems.campaign.model.CampaignAnalytics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

@Controller()
public class CampaignAnalyticsUIController {
    @GetMapping("/campaigns/{id}")
    public String getCampaignAnalytics(Model model, @PathVariable String id){
        String campaignId = id;
        try{
            IEmailDetailsPersistence emailDetailsPersistence = new EmailDetailsDb(MySqlPersistenceConnection.getInstance().getConnection());
            CampaignAnalytics campaignAnalytics = new CampaignAnalytics().getCampaignAnalytics(emailDetailsPersistence, campaignId);
            model.addAttribute("campaignAnalytics", campaignAnalytics);
            return "campaignAnalytics";
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
