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
}
