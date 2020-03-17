package com.chuyue.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final char MINUTE = 'm';
    public static final char HOUR = 'h';
    public static final char DAY = 'd';
    public static final char WEEK = 'w';
    public static final char MONTH = 'M';
    private static SimpleDateFormat format1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat format3 = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");
    private static SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat format5 = new SimpleDateFormat(
            "yyyyMMddHHmm");
    private static Calendar ca = Calendar.getInstance();
    private static SimpleDateFormat format6 = new SimpleDateFormat("dd:HH:mm");
    private static SimpleDateFormat format7 = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    /**
     * 是否零点
     *
     * @param time
     * @param zeroStr
     * @return
     */
    public static boolean isZero(long time, String zeroStr) {
        ca.setTimeInMillis(time);
        String dateString = format6.format(ca.getTime());
        if (zeroStr.equals(dateString)) {
            return true;
        } else {
            return false;
        }

    }

    public static Date nowDate() {
        return ca.getTime();
    }

    /**
     * 获得当前月的字符串
     *
     * @param fotmat
     * @return
     */
    public static String getCurrentMonth(String fotmat) {
        String s = new SimpleDateFormat(fotmat).format(new Date());
        return s;
    }

    /**
     * 获得当前日期的字符串
     *
     * @param format 指定返回日期的字符串格式
     * @return
     */
    public static String currentDateString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间格式为yyyyMMddHHmmss
     *
     * @return String
     */
    public static String currentDateString() {
        return format7.format(new Date());
    }

    public static String getDateFormat(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        ca.setTimeInMillis(System.currentTimeMillis());
        String dateString = sdf.format(ca.getTime());
        return dateString == null ? "" : dateString;
    }

    public static String getFileDateName(long time) {
        ca.setTimeInMillis(time);
        String dateString = format5.format(ca.getTime());
        return dateString == null ? "" : dateString;
    }

    public static Date parseDate(String dateString) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        try {
            return format1.parse(dateString);
        } catch (ParseException e) {
            return null;
        } catch (NumberFormatException ex) {
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static Date parseDate1(String dateString) {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        try {
            return format3.parse(dateString);
        } catch (ParseException e) {
            System.out.println(dateString);
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(long dateTimes) {
        ca.setTimeInMillis(dateTimes);
        String dateString;
        try {
            dateString = format1.format(ca.getTime());
        } catch (Exception e) {
            dateString = "";
        }
        return dateString;
    }

    public static String convertDateToString1(Date date) {
        if (date == null) {
            return "";
        }
        String dateString = format3.format(date);
        return dateString == null ? "" : dateString;
    }

    public static String convertDateToString(Date date) {
        if (date == null) {
            return "";
        }
        String dateString = format1.format(date);
        return dateString == null ? "" : dateString;
    }

    public static long convertStringToDate(String dateStr)
            throws ParseException {
        ca.setTime(format4.parse(dateStr));
        return ca.getTimeInMillis();
    }

    //将日期字符串yyyy-MM-dd HH:mm:ss转换成格式为yyyyMMddHHmmss的Long类型
    public static Long convertStringToLong(String dateStr) {
        return Long.valueOf(dateStr.replace(" ", "").replace("-", "").replace(":", ""));
    }

    //将日期字符串由格式yyyyMMddHHmmss转换成格式为yyyy-MM-dd HH:mm:ss
    public static String convertStringToString(String dateStr) {
        String dateString = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8) + " " +
                dateStr.substring(8, 10) + ":" + dateStr.substring(10, 12) + ":" + dateStr.substring(12, 14);
        return dateString;
    }

    public static String formatDate_Hour(Date date) {
        if (date == null || !(date instanceof Date))
            return "";
        SimpleDateFormat format = new SimpleDateFormat("HH");
        return format.format(date);
    }

    public static String formatDate_HMS(Date date) {
        if (date == null || !(date instanceof Date)) {
            return "";
        }
        return format1.format(date);
    }

    public static Date getBeforeSomeHours(int hour) {
        ca.add(Calendar.HOUR_OF_DAY, -hour);
        return ca.getTime();
    }

    @SuppressWarnings("deprecation")
    public static Date getBeforeSomeMinutes(Date date, int minute) {
        int minutes = date.getMinutes();
        date.setMinutes(minutes + minute);
        return date;
    }

    public static Date getBeforeSomeMinutes(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }

    public static Date getBeforeSomeHoursToHour(int hour) {
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH),
                ca.get(Calendar.DAY_OF_MONTH), ca.get(Calendar.HOUR_OF_DAY), 0,
                0);
        ca.add(Calendar.HOUR_OF_DAY, -hour);
        return ca.getTime();
    }

    public static Date getBeforeSomeDays(int day) {
        ca.add(Calendar.DAY_OF_MONTH, -day);
        return ca.getTime();
    }

    public static Date getBeforeSomeWeeks(int week) {
        ca.add(Calendar.WEEK_OF_YEAR, -week);
        return ca.getTime();
    }

    public static Date getBeforeSomeMonths(int month) {
        ca.add(Calendar.MONTH, -month);
        return ca.getTime();
    }

    public static Date getBeforeSomeYears(int year) {
        ca.add(Calendar.YEAR, -year);
        return ca.getTime();
    }

    public static Date splitExpression(String param) {
        if (param == null && "".equals(param)) {
            return new Date();
        }
        if (param.trim().equals("#now")) {
            return new Date();
        }
        if (param.matches("#now\\s*-\\s*\\d+[mhdwM]")) {
            param = param.substring("#now".length(), param.length()).trim();

            param = param.substring(1, param.length()).trim();
            switch (param.charAt(param.length() - 1)) {
                case MINUTE:
                    int minute = Integer.parseInt(param.substring(0,
                            param.length() - 1));
                    return getBeforeSomeMinutes(minute);
                case HOUR:
                    int hour = Integer.parseInt(param.substring(0,
                            param.length() - 1));
                    return getBeforeSomeHours(hour);
                case DAY:
                    int day = Integer.parseInt(param.substring(0,
                            param.length() - 1));
                    return getBeforeSomeDays(day);
                case WEEK:
                    int week = Integer.parseInt(param.substring(0,
                            param.length() - 1));
                    return getBeforeSomeWeeks(week);
                case MONTH:
                    int month = Integer.parseInt(param.substring(0,
                            param.length() - 1));
                    return getBeforeSomeMonths(month);
                default:
                    break;
            }

        } else {
            try {
                throw new Exception("Express is not invalid!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Date();
    }

    public static boolean compareDate(Date date1,Date date2) {

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        return time1 < time2;
    }

    public static void main(String[] args) {
        String s = formatDate_HMS(new Date());
        System.out.println(s);
    }


}
