package com.Panlantic.Secondproject.utils;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

public class Validate {

    private String token;

    public static void  validateToken (String token) throws AccessDeniedException {
        if (!Objects.equals(token, "fk3v30s2@4$13")) {
            throw new AccessDeniedException("Access Denied");
        }else{
            System.out.println("Access granted");
        }
    }
}
