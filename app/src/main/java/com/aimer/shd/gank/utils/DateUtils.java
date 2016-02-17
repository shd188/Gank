package com.aimer.shd.gank.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shd on 2016/2/16.
 */
public class DateUtils {
    public static final SimpleDateFormat GANKDATE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static String getDate(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getDate(long date) {
        return getDate(date,GANKDATE);
    }
    public static long StringToDate(String param) {
        if (TextUtils.isEmpty(param)) {
            return -1;
        } else {
            java.util.Date date = null;
            try {
                date = GANKDATE.parse(param);
                return new Date(date.getTime()).getTime();
            } catch (ParseException ex) {
                ex.printStackTrace();
                return -1;
            }
        }
    }

}
