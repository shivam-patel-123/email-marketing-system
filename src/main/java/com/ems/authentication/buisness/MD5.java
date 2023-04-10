package com.ems.authentication.buisness;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 implements IHash {
    private static MD5 instance;
    private MD5(){

    }
    public static MD5 getInstance(){
        if (instance==null){
            instance=new MD5();
        }
        return instance;
    }
    @Override
    public String hash(String plainString) throws NoSuchAlgorithmException {
        //code referred  from https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash-in-java
        //modifications are made to convert hex into string
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(plainString.getBytes());
        byte[] digest= md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();

    }
}
