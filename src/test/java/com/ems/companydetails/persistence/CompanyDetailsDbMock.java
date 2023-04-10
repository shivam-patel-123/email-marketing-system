package com.ems.companydetails.persistence;

import com.ems.companydetails.model.CompanyDetails;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsDbMock implements ICompanyDetailsPersistence{


    @Override
    public int saveCompanyDetails(CompanyDetails saveCompany) throws Exception {
        if(saveCompany.company_id.equals("C-deb64786-c58e-4d70-a5ae-46e4cfcf14ec"))
        {
         return 1;
        }
        else if(saveCompany.company_id.equals("xyz")){
            return -1;
        }
        return -1;
    }

    @Override
    public CompanyDetails getCompanyByUserId(String userId) throws Exception {
        if(userId.equals("3bef6c13-68ec-4366-8531-3e22af4d3314")){
            CompanyDetails companyDetails = new CompanyDetails();
            companyDetails.company_id ="C-deb64786-c58e-4d70-a5ae-46e4cfcf14ec";
            companyDetails.company_name ="Facebook";
            companyDetails.website_link ="Facebook@facebook.com";
            companyDetails.company_email ="Facebook@facebook.com";
            companyDetails.owner_name = "Facebook@facebook.com";
            companyDetails.facebook_link= "Facebook@facebook.com";
            companyDetails.instagram_link= "Facebook@facebook.com";
            companyDetails.twitter_url= "Facebook@facebook.com";
            return companyDetails;
        }
        else if(userId.equals("xyz")){
            return null;
        }
        return null;
    }
    @Override
    public List<CompanyDetails> loadcompanyDetails(CompanyDetails company) throws Exception {
        CompanyDetails companyDetails = new CompanyDetails();
        List<CompanyDetails> totalSubsciber= new ArrayList<>();
        if(companyDetails != null)
        {
            return totalSubsciber;

        } else if (companyDetails == null) {
            return null;
        }
        return null;
    }
}
