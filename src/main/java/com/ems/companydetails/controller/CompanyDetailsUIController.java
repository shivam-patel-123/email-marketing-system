package com.ems.companydetails.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.companydetails.model.CompanyDetails;
import com.ems.companydetails.persistence.CompanyDetailsDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class CompanyDetailsUIController {

    public Connection getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @GetMapping("/add-company-details")
    public String getCompanyaDetails() {
        return "companyDetailsForm";
    }

    @GetMapping("/company-details")
    public String companyDetails(Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        CompanyDetails companyDetails = getCompanyDetailsByUserId(user.userId);
        model.addAttribute("companyDetails", companyDetails);
        return "companyDetails";
    }

    public CompanyDetails getCompanyDetailsByUserId(String userId) throws Exception {

        CompanyDetailsDB dataBaseObj = new CompanyDetailsDB(getConnectionObject());
        CompanyDetails companyDetailsByUserId = new CompanyDetails();
       return companyDetailsByUserId.getCompanyDetailsByUserId(userId,dataBaseObj);
    }

}
