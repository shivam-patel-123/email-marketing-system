package com.ems.bulkemail.smtp;

import com.ems.bulkemail.buisness.EmailDetails;
import com.ems.bulkemail.buisness.ISendEmail;

public class GmailMock implements ISendEmail {


    public boolean sendEmail(EmailDetails emailDetails) {
        if (emailDetails.id=="1"){
            return false;

        }

        return true;
    }
}


