package service;

import java.util.Date;

public class Utils {

    public static Date createDate(int year, int month, int day) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar.getTime();
    }
}
