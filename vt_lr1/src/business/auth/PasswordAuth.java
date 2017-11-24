package business.auth;

import persistance.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordAuth {
    public boolean isUserValid(User user, String password) throws NoSuchAlgorithmException {
        return user.password.equals(encodePassword(password));
    }

    public String encodePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
        byte[] arr = messageDigest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(arr);
    }
}
