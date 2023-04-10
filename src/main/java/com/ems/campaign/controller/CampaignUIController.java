package com.ems.campaign.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.persistent.CampaignDb;
import com.ems.campaign.persistent.ICampaignPersistent;
import com.ems.emailtemplate.model.Template;
import com.ems.emailtemplate.model.template_fetcher.EmailTemplateFetcher;
import com.ems.emailtemplate.model.template_fetcher.ITemplateFetcher;
import com.ems.emailtemplate.persistent.EmailTemplateDb;
import com.ems.emailtemplate.persistent.ITemplatePersistent;
import com.ems.usersegment.model.UserSegment;
import com.ems.usersegment.persistence.IUserSegmentPersistent;
import com.ems.usersegment.persistence.UserSegmentDB;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component()
@RequestMapping(value = "/")
public class CampaignUIController {


    private Connection getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @GetMapping("/campaigns")
    public String showCampaignByUserId(Model model, HttpSession session) {
        User user = getLoggedInUser(session);
        String userId = user.userId;
        ICampaignPersistent campaignPersistent = new CampaignDb(getConnectionObject());

        List<Campaign> campaigns = campaignPersistent.loadCampaignByUserId(userId);

        model.addAttribute("campaigns", campaigns);
        return "campaignList";
    }

    @GetMapping("/create-campaign")
    public String createCampaignForm(Model model, HttpSession session) {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ITemplatePersistent templatePersistent = new EmailTemplateDb(connection);
        ITemplateFetcher templateFetcher = new EmailTemplateFetcher(templatePersistent);

        User user = getLoggedInUser(session);
        List<Template> templates = (List<Template>) templateFetcher.fetchAllTemplateByUserId(user).getData();
        IUserSegmentPersistent segmentPersistent = new UserSegmentDB(getConnectionObject());
        UserSegment userSegment = segmentPersistent.getUserSegmentByUserId(user.userId);
        model.addAttribute("templates", templates);
        model.addAttribute("userSegment", userSegment);
        return "campaignForm";
    }
}
