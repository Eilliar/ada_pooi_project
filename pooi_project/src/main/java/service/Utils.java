package service;

import java.util.Date;

public class Utils {

    public static Date createDate(int year, int month, int day) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar.getTime();
    }

    public static Date stringToDate(String dateString){
        String[] splitArray = dateString.split("-");
        int[] birthArray = new int[splitArray.length];
        for(int i=0; i < splitArray.length; i++){
            birthArray[i] = Integer.valueOf(splitArray[i]);
        }

        return createDate(birthArray[0], birthArray[1], birthArray[2]);
    }
}
