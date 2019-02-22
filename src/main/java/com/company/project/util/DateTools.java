package com.company.project.util;

public class DateTools {

    public static long timeStamp()
    {
        long timestamp = System.currentTimeMillis();
        return timestamp > 999_999_999_9L ? timestamp / 1000L : timestamp;
    }
}
