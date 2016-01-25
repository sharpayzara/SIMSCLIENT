package com.mxst.car.simsclient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author   Joy
 * Date:  2016/1/25.
 * version:  V1.0
 * Description:
 */
public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis timeInMillis
     * @param dateFormat   dateFormat
     * @return String
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis time
     * @return String
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }

    /**
     * get current time in milliseconds
     *
     * @return long
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return  String
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @param dateFormat    dateFormat
     * @return  String
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {

        return getTime(getCurrentTimeInLong(), dateFormat);
    }
}
