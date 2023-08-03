package quizpackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String hashPassword(String password){
        try{
            MessageDigest shaHash = MessageDigest.getInstance("SHA");
            shaHash.update(password.getBytes());
            return hexToString(shaHash.digest());
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    private static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;
            if (val<16) buff.append('0');
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    public static boolean isPassword(String password, String hash){
        return hashPassword(password).equals(hash);
    }
}
