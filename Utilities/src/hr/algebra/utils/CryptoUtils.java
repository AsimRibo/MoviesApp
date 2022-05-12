/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author asim2
 */
public class CryptoUtils {
    private static final String SHA256 = "SHA-256";
    private static final char ZERO = '0';
    
    public static String sha256(String text) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance(SHA256);
        byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                sb.append(ZERO);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    
    
}
