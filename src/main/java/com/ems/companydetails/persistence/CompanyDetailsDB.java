package com.ems.companydetails.persistence;

import com.ems.companydetails.Exception.DatabaseNotFound;
import com.ems.companydetails.model.CompanyDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsDB implements ICompanyDetailsPersistence {
    public Connection connection;

    public CompanyDetailsDB(Connection conn) {
        this.connection = conn;
    }

    @Override
    public List<CompanyDetails> loadcompanyDetails(CompanyDetails company) throws Exception {
        if (connection == null)
        {
            throw new DatabaseNotFound();
        }
        String sql = "select * from company_details";
        List<CompanyDetails> resultCompany = new ArrayList<>();

        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CompanyDetails companydetails = new CompanyDetails();
                companydetails.company_id = rs.getString("company_id");
                companydetails.company_name = rs.getString("company_name");
                companydetails.website_link = rs.getString("website_link");
                companydetails.company_email = rs.getString("company_email");
                companydetails.owner_name = rs.getString("owner_name");
                companydetails.facebook_link = rs.getString("facebook_link");
                companydetails.instagram_link = rs.getString("instagram_link");
                companydetails.twitter_url = rs.getString("twitter_link");
                resultCompany.add(companydetails);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resultCompany;
    }

    @Override
    public int saveCompanyDetails(CompanyDetails saveCompany) throws Exception {
        try
        {
            Statement stmt = connection.createStatement();
            String saveCompanyQuery = "INSERT INTO company_details VALUES (" +
                    "\"" + saveCompany.getCompany_id() + "\", " +
                    "\"" + saveCompany.getCompany_name() + "\", " +
                    "\"" + saveCompany.getWebsite_link() + "\", " +
                    "\"" + saveCompany.getCompany_email() + "\", " +
                    "\"" + saveCompany.getOwner_name() + "\", " +
                    "\"" + saveCompany.getFacebook_link() + "\", " +
                    "\"" + saveCompany.getInstagram_link() + "\", " +
                    "\"" + saveCompany.getTwitter_url() + "\")";
            return stmt.executeUpdate(saveCompanyQuery);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
        // connectUserWithCompany method connect User
    public int connectUserWithCompany(String userId, String companyId) {
        try
        {
            Statement stmt = connection.createStatement();
            String saveCompanyQuery = "INSERT INTO company_has_users VALUES (" +
                    "\"" + companyId + "\", " +
                    "\"" + userId + "\")";
            return stmt.executeUpdate(saveCompanyQuery);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public CompanyDetails getCompanyByUserId(String userId) {
        try {
            Statement stmt = connection.createStatement();
            String saveCompanyQuery =
                    "select cd.* from company_has_users as cu \n" +
                    "inner join company_details as cd \n" +
                    "on cu.company_id = cd.company_id\n" +
                    "where user_id = \""+ userId +"\"";
            ResultSet rs = stmt.executeQuery(saveCompanyQuery);
            CompanyDetails companyDetails = new CompanyDetails();
            while (rs.next())
            {
                companyDetails.company_id = rs.getString("company_id");
                companyDetails.company_name = rs.getString("company_name");
                companyDetails.website_link = rs.getString("website_link");
                companyDetails.company_email = rs.getString("company_email");
                companyDetails.owner_name = rs.getString("owner_name");
                companyDetails.facebook_link = rs.getString("facebook_link");
                companyDetails.instagram_link = rs.getString("instagram_link");
                companyDetails.twitter_url = rs.getString("twitter_link");
            }
            return companyDetails;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
