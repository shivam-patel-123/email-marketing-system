package com.ems.companydetails.controller;

import com.ems.authentication.model.User;
import org.springframework.web.bind.annotation.*;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.companydetails.model.CompanyDetails;
import com.ems.companydetails.persistence.CompanyDetailsDB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company-details")
public class CompanyDetailsController {
    private CompanyDetails companyInformation = new CompanyDetails();

    public Connection getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @GetMapping("/getcompanys")
    public List<CompanyDetails> getCompanyDetails()
    {
        try
        {

            return companyInformation.loadCompanyDetails(new CompanyDetailsDB(getConnectionObject()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/save")
    public void createCompanyTemplate (HttpServletRequest request, HttpSession session, HttpServletResponse response)
    {
        try{
            String companyName = request.getParameter("company_name");
            String website_link = request.getParameter("website_link");
            String company_email = request.getParameter("company_email");
            String owner_name = request.getParameter("owner_name");
            String facebook_link = request.getParameter("facebook_link");
            String instagram_link = request.getParameter("instagram_link");
            String twitter_url = request.getParameter("twitter_url");

            CompanyDetails addCompanyinformation = new CompanyDetails(companyName, website_link,company_email, owner_name, facebook_link, instagram_link, twitter_url);
            addCompanyinformation.saveCompanyDetails(new CompanyDetailsDB(getConnectionObject()));
            User user = (User) session.getAttribute("user");
            session.setAttribute("companyId", addCompanyinformation.company_id);
            int responsefromUser = new CompanyDetailsDB(getConnectionObject()).connectUserWithCompany(user.userId, addCompanyinformation.company_id);
            if(responsefromUser != -1){
                response.sendRedirect("/company-details");
            } else {
                response.sendRedirect("/add-company-details");
            }
        }
        catch(Exception exception)
        {
             exception.printStackTrace();
        }
    }



}
