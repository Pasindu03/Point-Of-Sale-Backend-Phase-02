package com.example.posbe.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCustomerId(){
        return "CUSID-" + UUID.randomUUID();
    }
}
