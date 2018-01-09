package com.kute.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by kute on 2018/1/8.
 * <p>
 * AbstractPasswordEncoder, BCryptPasswordEncoder, NoOpPasswordEncoder, Pbkdf2PasswordEncoder, SCryptPasswordEncoder, StandardPasswordEncoder
 */
public class PasswordEncoderUtil {

    private static final String secret = "secret";

    public static PasswordEncoder newBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static PasswordEncoder newStandardPasswordEncoder() {
        return new StandardPasswordEncoder(secret);
    }

}
