package com.ems.companydetails.business;

import com.ems.companydetails.model.CompanyDetails;
import com.ems.companydetails.persistence.CompanyDetailsDbMock;
import com.ems.companydetails.persistence.ICompanyDetailsPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyDetailsTest {
    private static CompanyDetails companyDetails;
    private static ICompanyDetailsPersistence companyDetailsPersistence;

    @BeforeAll
    public static void init()
    {
        companyDetails = new CompanyDetails();
        companyDetailsPersistence = new CompanyDetailsDbMock();
    }
    @Test
    public void initTest()
    {
        CompanyDetails companyDetails1 = new CompanyDetails("Tata","https://www.tcs.com/", "Online@TSC.ca.",
                "Mr Ratan Tata", "https://www.facebook.com/tcscouriers/", "https://www.instagram.com/tcsglobal/?hl=en",
                "https://twitter.com/tcscanada?lang=en");
        assertNotNull(companyDetails1);
    }
    @Test
    public void saveCompanyDetailsSuccessTest() throws Exception {
        CompanyDetails inputCompanyDetails = new CompanyDetails();
        inputCompanyDetails.company_id = "C-deb64786-c58e-4d70-a5ae-46e4cfcf14ec";
        int response = inputCompanyDetails.saveCompanyDetails(companyDetailsPersistence);
        assertEquals(1,response);

    }
    @Test
    public void saveCompanyDetailsFailTest() throws Exception {
        CompanyDetails inputCompanyDetails = new CompanyDetails();
        inputCompanyDetails.company_id = "xyz";
        int response = inputCompanyDetails.saveCompanyDetails(companyDetailsPersistence);
        assertEquals(-1,response);

    }
    @Test
    public void getCompanyDetailsByUserIdSuccessTest() throws Exception {
        CompanyDetails getCompanyByUserId = companyDetails.getCompanyDetailsByUserId("3bef6c13-68ec-4366-8531-3e22af4d3314",companyDetailsPersistence);
        CompanyDetails expectedCompanyDetails = new CompanyDetails();
        expectedCompanyDetails.company_id = "C-deb64786-c58e-4d70-a5ae-46e4cfcf14ec";
        assertEquals(getCompanyByUserId.company_id,expectedCompanyDetails.company_id);
    }
    @Test
    public void getCompanyDetailsByUserIdFailTest() throws Exception {
        CompanyDetails companyDetailsByUserId = companyDetails.getCompanyDetailsByUserId("xyz",companyDetailsPersistence);
        assertNull(companyDetailsByUserId);
    }
    @Test
    public void getCompanyIdSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompany_id("C-00b4b0df-6174-4c44-9ce5-a99db082a8c5");
        String expectedCompanyId = companyDetails.getCompany_id();
        assertEquals(expectedCompanyId,"C-00b4b0df-6174-4c44-9ce5-a99db082a8c5");
    }
    @Test
    public void SetCompanyIdSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyid= companyDetails.getCompany_id();
        companyDetails.setCompany_id("C-00b4b0df-6174-4c44-9ce5-a99db082a8c5");
        String expectedCompanyId = companyDetails.getCompany_id();
        assertNotEquals(expectedCompanyId,companyid);
    }
    @Test
    public void getCompanyNameSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompany_name("TATA");
        String expectedCompanyName = companyDetails.getCompany_name();
        assertEquals(expectedCompanyName,"TATA");
    }
    @Test
    public void SetCompanyNameSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyName= companyDetails.getCompany_name();
        companyDetails.setCompany_name("TATA Industries");
        String expectedCompanyName = companyDetails.getCompany_name();
        assertNotEquals(expectedCompanyName,companyName);
    }
    @Test
    public void getCompanyWebsiteLinkSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setWebsite_link("https://www.tcs.com/");
        String expectedCompanyWebsite = companyDetails.getWebsite_link();
        assertEquals(expectedCompanyWebsite,"https://www.tcs.com/");
    }
    @Test
    public void SetCompanyWebsiteLinkSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyWebsite= companyDetails.getWebsite_link();
        companyDetails.setWebsite_link("https://www.tcs.com/");
        String expectedCompanyWebsite = companyDetails.getWebsite_link();
        assertNotEquals(expectedCompanyWebsite,companyWebsite);
    }
    @Test
    public void getCompanyEmailSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompany_email("Online@TSC.ca.");
        String expectedCompanyEmail = companyDetails.getCompany_email();
        assertEquals(expectedCompanyEmail,"Online@TSC.ca.");
    }
    @Test
    public void SetCompanyEmailSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyEmail= companyDetails.getCompany_email();
        companyDetails.setCompany_email("Online@TSC.ca.");
        String expectedCompanyEmail = companyDetails.getCompany_email();
        assertNotEquals(expectedCompanyEmail,companyEmail);
    }
    @Test
    public void getCompanyOwnerNameSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setOwner_name("Mr Ratan Tata");
        String expectedOwnerName = companyDetails.getOwner_name();
        assertEquals(expectedOwnerName,"Mr Ratan Tata");
    }
    @Test
    public void SetCompanyOwnerNameSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyOwnerName= companyDetails.getOwner_name();
        companyDetails.setOwner_name("Mr Ratan Tata");
        String expectedOwnerName = companyDetails.getOwner_name();
        assertNotEquals(expectedOwnerName,companyOwnerName);
    }
    @Test
    public void getCompanyFacbookLinkSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setFacebook_link("https://www.facebook.com/tcscouriers/");
        String expectedFacebookLink = companyDetails.getFacebook_link();
        assertEquals(expectedFacebookLink,"https://www.facebook.com/tcscouriers/");
    }
    @Test
    public void SetCompanyFacbookLinkSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyFacbookLink= companyDetails.getFacebook_link();
        companyDetails.setFacebook_link("https://www.facebook.com/tcscouriers/");
        String expectedFacbookLink = companyDetails.getFacebook_link();
        assertNotEquals(expectedFacbookLink,companyFacbookLink);
    }
    @Test
    public void getCompanyInstagramLinkSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setInstagram_link("https://www.instagram.com/tcsglobal/?hl=en");
        String expectedInstagramLink = companyDetails.getInstagram_link();
        assertEquals(expectedInstagramLink,"https://www.instagram.com/tcsglobal/?hl=en");
    }
    @Test
    public void SetCompanyInstagramLinkSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyInstagramLink= companyDetails.getInstagram_link();
        companyDetails.setInstagram_link("https://www.instagram.com/tcsglobal/?hl=en");
        String expectedInstagramLink = companyDetails.getInstagram_link();
        assertNotEquals(expectedInstagramLink,companyInstagramLink);
    }
    @Test
    public void getCompanyTwitterLinkSuccessTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setTwitter_url("https://twitter.com/tcscanada?lang=en");
        String expectedTwitterLink = companyDetails.getTwitter_url();
        assertEquals(expectedTwitterLink,"https://twitter.com/tcscanada?lang=en");
    }
    @Test
    public void SetCompanyTwitterLinkSuccesTest(){
        CompanyDetails companyDetails = new CompanyDetails();
        String companyTwitterLink= companyDetails.getTwitter_url();
        companyDetails.setTwitter_url("https://twitter.com/tcscanada?lang=en");
        String expectedTwitterLink = companyDetails.getTwitter_url();
        assertNotEquals(expectedTwitterLink,companyTwitterLink);
    }
    @Test
    public void loadComapnyDetialsSuccessTest() throws Exception {
        List<CompanyDetails> companyDetails1 = companyDetails.loadCompanyDetails(companyDetailsPersistence);
        assertNotNull(companyDetails1);
    }

}
