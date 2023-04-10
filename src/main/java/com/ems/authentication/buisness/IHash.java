package com.ems.authentication.buisness;

import java.security.NoSuchAlgorithmException;

public interface IHash {
    public String hash(String plainString) throws NoSuchAlgorithmException;
}
