package com.direct.utils;

/**
 * @author ：bfury
 * @date ：22/7/2021 4:27 下午
 */
import org.apache.log4j.Logger;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {

    private static final Logger LOG = Logger.getLogger(DateTimeUtil.class);


    // 获取当月或指定时间的的第一天

    /**
     * 获取指定时间月的第一天 如果date 为null 则返回当前月的第一天
     *
     * @param date
     * @return Date
     */
    public static Date firstDayOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        if (date == null) {
            date = cal.getTime();
        }
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 通过时间date 获取此时间的前几天或者后几天的时间date 如果date为null 则返回当前时间的前几天或者后几天date
     *
     * @param date  指定时间date
     * @param count 过去退几天（负数）， 未来前进几天（正数）
     * @return date
     */
    public static Date getDateByDate(Date date, int count) throws Exception {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        if (date == null) {
            date = cal.getTime();
        }
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, count);
        return cal.getTime();
    }

    /**
     * 根据long时间 获取date 如果long为0则返回当前时间date
     *
     * @param longTime
     * @return
     */
    public static Date getDateBylong(long longTime) throws Exception {
        Date date = new Date();
        if (longTime == 0) {
            return date;
        } else {
            date.setTime(longTime * 1000L);
            return date;
        }
    }

    /**
     * 获取时间date 如果str为null或者"" 则返回当前时间date
     *
     * @param str    时间字符串
     * @param format 时间字符串格式（如str为：2018_05_29 12:12:00; 则format即为：yyyy_MM_dd
     *               HH:mm:ss）
     * @return date
     */
    public static Date getDateByString(String str, String format) throws Exception {
        Date date = null;
        if (str == null || "".equals(str)) {
            return new Date();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                LOG.error("【时间转换错误】" + e);
            }
        }
        return date;
    }

    /**
     * 通过时间date 获取指定时间（或当前时间 即date为null） 所在周的星期几 date
     *
     * @param date  指定时间date
     * @param count 所在周的星期几（1~7）周一为 1
     * @return date
     */
    public static Date getDayOfWeek(Date date, int count) throws Exception {
        if (date == null) {
            date = new Date();
        }
        Calendar ca = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        ca.setTime(date);
        switch (count) {
            case 1:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;
            case 2:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                break;
            case 3:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                break;
            case 4:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                break;
            case 5:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                break;
            case 6:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            case 7:
                ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            default:
                break;
        }
        return ca.getTime();
    }

    /**
     * 获取某一天的结束时间long 如果date为null 则返回当天的结束时间 long
     *
     * @param date
     * @return
     */
    public static long getEndOfDay(Date date) throws Exception {
        if (date == null) {
            date = new Date();
        }
        String newTime = getStringByDate(date, "yyyy_MM_dd");
        date = getDateByString(newTime, "yyyy_MM_dd");
        return date.getTime() / 1000L + 86399L;
    }

    /**
     * 根据时间字符串获取某一天的结束时间long 如果str为null或"" 则返回当天的结束时间 long
     *
     * @param str    时间字符串
     * @param format 时间字符串的格式（如str为：2018_05_29 12:12:00; 则format即为：yyyy_MM_dd
     *               HH:mm:ss）
     * @return
     */
    public static long getEndOfDay(String str, String format) throws Exception {
        Date date = null;
        if (str == null || "".equals(str)) {
            date = new Date();
        } else {
            date = getDateByString(str, format);
        }
        String newTime = getStringByDate(date, "yyyy_MM_dd");
        date = getDateByString(newTime, "yyyy_MM_dd");
        return date.getTime() / 1000L + 86399L;
    }

    /**
     * 根据时间date 获取long 如果date为null则返回当前时间long
     *
     * @param date
     * @return
     */
    public static long getLongByDate(Date date) throws Exception {
        long temp = 0;
        if (date == null) {
            date = new Date();
            temp = date.getTime() / 1000L;
        } else {
            temp = date.getTime() / 1000L;
        }
        return temp;
    }

    /**
     * 字符串转换成long类型 如果str为null或"" 即获取当前时间
     *
     * @param str    时间字符串
     * @param format 需要将时间字符串str转换成为的格式（如str为：2018_05_29 12:12:00;
     *               则format即为：yyyy_MM_dd HH:mm:ss）
     * @return
     */
    public static long getLongByString(String str, String format) throws Exception {
        long longdate = 0L;
        if (str == null || "".equals(str)) {
            longdate = new Date().getTime() / 1000L;
        } else {
            // SimpleDateFormat sdf = new SimpleDateFormat(format);
            longdate = getDateByString(str, format).getTime() / 1000L;
        }
        return longdate;
    }

    /**
     * 获取某一天的开始时间long 如果date为null 则返回当天的开始时间 long
     *
     * @param date
     * @return
     */
    public static long getStartOfDay(Date date) throws Exception {
        if (date == null) {
            date = new Date();
        }
        String newTime = getStringByDate(date, "yyyy_MM_dd");
        date = getDateByString(newTime, "yyyy_MM_dd");
        return date.getTime() / 1000L;
    }

    /**
     * 根据时间字符串获取某一天的开始时间long 如果str为null或"" 则返回当天的开始时间 long
     *
     * @param str    时间字符串
     * @param format 时间字符串str对应的的格式（如str为：2018_05_29 12:12:00; 则format即为：yyyy_MM_dd
     *               HH:mm:ss）
     * @return
     */
    public static long getStartOfDay(String str, String format) throws Exception {
        Date date = null;
        if (str == null || "".equals(str)) {
            date = new Date();
        } else {
            date = getDateByString(str, format);
        }
        String newTime = getStringByDate(date, "yyyy_MM_dd");
        date = getDateByString(newTime, "yyyy_MM_dd");
        return date.getTime() / 1000L;
    }

    /**
     * 时间转换 如果date为null 即获取当前时间
     *
     * @param date
     * @param format 需要将date转换成为的格式（如yyyy_MM_dd HH:mm:ss）
     * @return
     */
    public static String getStringByDate(Date date, String format) throws Exception {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = "";
        dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 根据long时间 获取想要转成的格式字符串 如果long为0则返回当前时间字符串
     *
     * @param longTime
     * @param format   需要将longTime转换成为的格式（如yyyy_MM_dd HH:mm:ss）
     * @return
     */
    public static String getStringBylong(long longTime, String format) throws Exception {
        Date date = new Date();
        if (longTime != 0) {
            date.setTime(longTime * 1000L);
        }
        return getStringByDate(date, format);
    }

    /**
     * 获取指定时间所在月的最后一天 如果date 为null 则返回当前月的最后一天
     *
     * @param date
     * @return Date
     */
    public static Date lastDayOfMonth(Date date) throws Exception {
        Calendar cal = null;
        if (date == null) {
            date = new Date();
        }
        cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 获取指定日期 所对应的一年中第几周(如果dateStr为 null 或者 "" 则返回当前时间所在的周)
     *
     * @param str    时间字符串（如：2018-05-29 12:12:00）
     * @param format 与str对应的格式（如str为：2018-05-29 12:12:00; format即为：yyyy-MM-dd
     *               HH:mm:ss）
     * @return
     * @throws Exception
     */
    public static int getWeekOfYear(String str, String format) throws Exception {
        Date date = null;
        if (str == null || "".equals(str)) {
            date = new Date();
        } else {
            date = getDateByString(str, format);
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));;
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取指定日期是星期几 如果date为null 则获取当前时间星期几
     *
     * @param date
     * @return 当前日期是星期几 周日为7
     */
    public static int getWeekOfDate(Date date) throws Exception {
        if (date == null) {
            date = new Date();
        }
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static void main(String[] args) {
        try {
            // System.out.println(getWeekOfDate(getDateByString("20180528",
            // "yyyyMMdd")));
            System.out.println(getWeekOfYear("", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}