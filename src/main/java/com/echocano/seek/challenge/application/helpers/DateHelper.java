package com.echocano.seek.challenge.application.helpers;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * DateHelper
 * <p>
 * DateHelper class.
 * <p>
 *
 * @author echocano
 * @since 10/1/24
 */

@UtilityClass
public class DateHelper {

    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'";

    public static String getStringUTCNow() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(UTC_DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}
