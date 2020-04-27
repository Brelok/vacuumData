package com.github.brelok;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetTime {


    public static String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = time.format(formatter);
        return formatDateTime;
    }

    public static String getTimeForMail(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = time.format(formatter);
        return formatDateTime;
    }


}
