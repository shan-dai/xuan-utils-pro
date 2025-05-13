package xuan.utils;

import java.util.*;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;

import java.time.LocalDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具
 *
 * @author 善待
 * @version 2.0.0
 */
public class DateUtil {

    /**
     * 日期格式（格式：yyyy年MM月dd日HH时mm分ss秒）
     */
    public static final String DATE_TIME_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";

//*******************************************************************************************************************************

    /**
     * 格式化Solar公历日期
     *
     * @param solar 公历日期
     * @return 公历日期
     */
    public static String getSolarStr(Solar solar) {

        return getDateStr(solar.getYear(), solar.getMonth(), solar.getDay(), solar.getHour(), solar.getMinute(), solar.getSecond());

    }

    /**
     * 格式化Lunar农历日期
     *
     * @param lunar 农历日期
     * @return 农历日期
     */
    public static String getLunarStr(Lunar lunar) {

        String lunarStr;

        // 判断时辰
        int hour = lunar.getHour();
        if (hour >= 23 || hour < 1) {
            // 判断早晚子时
            if (hour >= 23) {
                lunarStr = lunar + "(晚)子时";
            } else {
                lunarStr = lunar + "(早)子时";
            }
        } else {
            final String[] HOUR_ZHI = {"子", "丑", "丑", "寅", "寅", "卯", "卯", "辰", "辰", "巳", "巳", "午", "午", "未", "未", "申", "申", "酉", "酉", "戌", "戌", "亥", "亥", "子"};
            lunarStr = lunar + HOUR_ZHI[hour] + "时";
        }

        return lunarStr;

    }

    /**
     * 格式化Lunar农历日期2
     *
     * @param lunar 农历日期
     * @return 农历日期
     */
    public static String getLunarStr2(Lunar lunar) {

        return getDateStr(lunar.getYear(), lunar.getMonth(), lunar.getDay(), lunar.getHour(), lunar.getMinute(), lunar.getSecond());

    }

    /**
     * 获取Date型日期
     *
     * @param dateStr String型日期
     * @return Date型日期
     */
    public static Date stringToDate(String dateStr) {

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * 在日期上增加（+）或减少（-）年数、月数、日数
     *
     * @param date   日期
     * @param iYear  要增加或减少的年数
     * @param iMonth 要增加或减少的月数
     * @param iDay   要增加或减少的日数
     */
    public static Date updateDate(Date date, int iYear, int iMonth, int iDay) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.YEAR, iYear); // 年数
        cd.add(Calendar.MONTH, iMonth); // 月数
        cd.add(Calendar.DAY_OF_MONTH, iDay); // 日数
        return cd.getTime();

    }

    /**
     * 在日期上增（+）加或减少（-）小时数、分钟数、秒数
     *
     * @param date    日期
     * @param iHour   要增加或减少的小时数
     * @param iMinute 要增加或减少的分钟数
     * @param iSecond 要增加或减少的秒数
     */
    public static Date updateDate2(Date date, int iHour, int iMinute, int iSecond) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.HOUR_OF_DAY, iHour); // 小时
        cd.add(Calendar.MINUTE, iMinute); // 分钟
        cd.add(Calendar.SECOND, iSecond); // 秒
        return cd.getTime();

    }

    /**
     * 获取日期的[年月日时分秒]
     *
     * @param date 日期
     * @return 年月日时分秒
     */
    public static Map<String, Integer> getDateMap(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        Map<String, Integer> map = new HashMap<>();
        map.put("year", c.get(Calendar.YEAR)); // 年
        map.put("month", c.get(Calendar.MONTH) + 1); // 月
        map.put("day", c.get(Calendar.DATE)); // 日
        map.put("hour", c.get(Calendar.HOUR_OF_DAY)); // 时
        map.put("minute", c.get(Calendar.MINUTE)); // 分
        map.put("second", c.get(Calendar.SECOND)); // 秒

        return map;

    }

    /**
     * 计算两个日期的时间间隔
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 时间间隔
     */
    public static Map<String, Long> dateInterval(String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime sDate = LocalDateTime.parse(startDate, formatter); // 开始日期
        LocalDateTime eDate = LocalDateTime.parse(endDate, formatter); // 结束日期

        Long days = ChronoUnit.DAYS.between(sDate, eDate);
        Long hours = ChronoUnit.HOURS.between(sDate, eDate) % 24;
        Long minutes = ChronoUnit.MINUTES.between(sDate, eDate) % 60;
        Long seconds = ChronoUnit.SECONDS.between(sDate, eDate) % 60;

        Map<String, Long> map = new HashMap<>();
        map.put("days", days); // 相差天数
        map.put("hours", hours); // 相差小时数
        map.put("minutes", minutes); // 相差分钟数
        map.put("seconds", seconds); // 相差秒数

        return map;

    }

    /**
     * 获取日期字符串
     *
     * @param year   公历年
     * @param month  公历月
     * @param day    公历日
     * @param time   公历时
     * @param minute 公历分
     * @param second 公历秒
     * @return 日期字符串
     */
    public static String getDateStr(int year, int month, int day, int time, int minute, int second) {

        Date date = new Date(year - 1900, month - 1, day, time, minute, second);
        String datestr;
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        datestr = df.format(date);
        return datestr;

    }

    /**
     * 获取指定日期字符串
     *
     * @param date       Date型日期
     * @param dateFormat 日期格式
     * @return String型日期
     */
    public static String getDateStr(Date date, String dateFormat) {

        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        dateStr = sdf.format(date);
        return dateStr;

    }


}
