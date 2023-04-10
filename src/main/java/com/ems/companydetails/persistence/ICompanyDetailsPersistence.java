package com.ems.companydetails.persistence;

import com.ems.companydetails.model.CompanyDetails;

import java.util.List;

public interface ICompanyDetailsPersistence {
    public List<CompanyDetails> loadcompanyDetails(CompanyDetails company) throws Exception;
    public int saveCompanyDetails(CompanyDetails saveCompany) throws Exception ;
    public CompanyDetails  getCompanyByUserId(String userId) throws Exception;
}
