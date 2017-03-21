package com.android.loglib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bernie.shi on 2017/3/20.
 */

public class DataHelper {
    public static String getDataOfDay(String logName, String suffix) {
        SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String time = logName + dateFormatDay.format(new Date()) + suffix;
        return time;
    }

    public static String getDataOfSSS() {
        SimpleDateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-SSS", Locale.US);
        String time = dateFormatDay.format(System.currentTimeMillis());
        return time;
    }
}
