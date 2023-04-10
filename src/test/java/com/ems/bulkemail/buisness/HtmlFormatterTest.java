package com.ems.bulkemail.buisness;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HtmlFormatterTest {
    private static IFormatMail emailFormatter;

    @BeforeAll
    public static void init(){
        emailFormatter= new HtmlFormatter();
    }

    @Test
    public void formatMailTest(){
        String component="test";
        String formattedMail=emailFormatter.formatMail(component);
        assertNotNull(formattedMail);

    }
}
