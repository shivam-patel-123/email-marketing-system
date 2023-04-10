package com.ems.authentication.buisness;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class MD5Test {

    private static MD5 md5;

    @BeforeAll
    public static void init(){
        md5=MD5.getInstance();
    }

    @Test
    public void hashNotNullTest() throws NoSuchAlgorithmException {
        String input="12345678";
        String output=md5.hash(input);
        assertNotNull(input,output);
    }
    @Test
    public void hashTest() throws NoSuchAlgorithmException {
        String input="12345678";
        String output=md5.hash(input);
        assertNotEquals(input,output);


    }

}
