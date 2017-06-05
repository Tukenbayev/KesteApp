package kz.keste.security;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private static int passwordLength = 6;

    public static String getRandomPassword(){
        StringBuilder sb = new StringBuilder( passwordLength );
        for( int i = 0; i < passwordLength; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
