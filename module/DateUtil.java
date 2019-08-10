package com.whzhdc.proj.module;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 获得时间戳
     *
     * @param dateString
     * @return
     */
    public static long getTs(String dateString) {
        if (StringUtils.isEmpty(dateString)) return 0;
        return DateUtil.from(dateString).getTime();
    }

    /**
     * 获得时间戳
     *
     * @param dateString
     * @return
     */
    public static long getNextDayTs(String dateString) {
        if (StringUtils.isEmpty(dateString)) return 0;
        return DateUtil.nextDay(DateUtil.from(dateString)).getTime();
    }

    /**
     * 获得任意非空的日期
     *
     * @param dates 日期数组
     * @return 日期
     */
    public static Date anyValid(Date... dates) {
        if (dates == null || dates.length == 0) return null;
        for (int i = 0; i < dates.length; i++) {
            if (dates[i] != null) return dates[i];
        }
        return null;
    }

    /**
     * 将字符串转换为日期，格式 yyyyMMdd 或者 yyyy-MM-dd
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date from(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        String transValue = value
                .replace("年", "")
                .replace("月", "")
                .replace("日", "")
                .replace("-", "")
                .replace(":", "")
                .trim()
                .replace(" ", "");
        String dfStr = "yyyyMMdd";
        switch (transValue.length()) {
            case 4:
                dfStr = "yyyy";
                break;
            case 6:
                dfStr = "yyyyMM";
                break;
            case 8:
                dfStr = "yyyyMMdd";
                break;
            case 10:
                dfStr = "yyyyMMddHH";
                break;
            case 12:
                dfStr = "yyyyMMddHHmm";
                break;
            case 14:
                dfStr = "yyyyMMddHHmmss";
                break;
            case 16:
                dfStr = "yyyyMMddHHmmss.S";
                break;
            case 17:
                dfStr = "yyyyMMddHHmmss.SS";
                break;
            case 18:
                dfStr = "yyyyMMddHHmmss.SSS";
                break;
        }

        SimpleDateFormat df = new SimpleDateFormat(dfStr);
        try {
            Date date = df.parse(transValue);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串转换为日期，转换无效返回默认值
     *
     * @param value 日期字符串
     * @param date  日期
     * @return 日期
     */
    public static Date fromWithDefault(String value, Date date) {
        Date at = from(value);
        return at == null ? date : at;
    }

    /**
     * 给定时间增加秒钟
     *
     * @param at      给定时间
     * @param seconds 增加的秒钟数
     * @return 新的时间
     */
    public static Date addSeconds(Date at, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(at);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }


    /**
     * 给定时间增加分钟
     *
     * @param at      给定时间
     * @param minutes 增加的分钟数
     * @return 新的时间
     */
    public static Date addMinutes(Date at, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(at);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 给定时间增加小时
     *
     * @param at    给定时间
     * @param hours 增加的小时
     * @return 新的时间
     */
    public static Date addHours(Date at, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(at);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 给定时间增加天数
     *
     * @param at   给定时间
     * @param days 增加的天数
     * @return 新的时间
     */
    public static Date addDays(Date at, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(at);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 给定时间增加月份数
     *
     * @param at     给定时间
     * @param months 增加的天数
     * @return 新的时间
     */
    public static Date addMonths(Date at, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(at);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 给定时间增加天数
     *
     * @param at    给定时间
     * @param years 增加的年数
     * @return 新的时间
     */
    public static Date addYears(Date at, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(at);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 判断时间在某个范围内
     *
     * @param at    给定时间
     * @param begin 起始时间
     * @param end   结束时间
     * @return 真假
     */
    public static boolean between(Date at, Date begin, Date end) {
        return at.getTime() >= begin.getTime() && at.getTime() <= end.getTime();
    }

    /**
     * 判断两个时间区间是否交叉
     *
     * @param r1BeginAt 区间1起始时间
     * @param r1EndAt   区间1结束时间
     * @param r2BeginAt 区间2起始时间
     * @param r2EndAt   区间2结束时间
     * @return 是否交叉
     */
    public static boolean cross(Date r1BeginAt, Date r1EndAt, Date r2BeginAt, Date r2EndAt) {
        if (r2BeginAt.getTime() >= r1EndAt.getTime()) return false;
        if (r1BeginAt.getTime() >= r2EndAt.getTime()) return false;
        return true;
    }

    /**
     * 判断时间是否等于某个时间
     *
     * @param at      给定时间
     * @param another 参考时间
     * @return 真假
     */
    public static boolean equals(Date at, Date another) {
        return at.getTime() == another.getTime();
    }

    /**
     * 判断时间是否不等于某个时间
     *
     * @param at      给定时间
     * @param another 参考时间
     * @return 真假
     */
    public static boolean notEquals(Date at, Date another) {
        return at.getTime() != another.getTime();
    }

    /**
     * 判断时间是否小于某个时间
     *
     * @param at      给定时间
     * @param another 参考时间
     * @return 真假
     */
    public static boolean lessThan(Date at, Date another) {
        return at.before(another);
    }

    /**
     * 判断时间是否大于某个时间
     *
     * @param at      给定时间
     * @param another 参考时间
     * @return 真假
     */
    public static boolean greatThan(Date at, Date another) {
        return at.after(another);
    }

    /**
     * 当前日期
     *
     * @return 时间
     */
    public static Date today() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }

    /**
     * 给出当日起始时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date dayBegin(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(value);
        at.set(Calendar.HOUR_OF_DAY, 0);
        at.set(Calendar.MINUTE, 0);
        at.set(Calendar.SECOND, 0);
        at.set(Calendar.MILLISECOND, 0);
        return at.getTime();
    }

    /**
     * 给出当日结束时间（不考虑特殊日期）
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date dayEnd(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(dayBegin(value));

        at.set(Calendar.HOUR_OF_DAY, 0);
        at.set(Calendar.MINUTE, 0);
        at.set(Calendar.SECOND, 0);
        at.set(Calendar.MILLISECOND, 0);
        at.add(Calendar.DAY_OF_MONTH, 1);
        at.add(Calendar.SECOND, -1);
        return at.getTime();
    }


    /**
     * 给出当周初时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date weekBegin(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(value);

        int d = at.get(Calendar.DAY_OF_WEEK);
        int wd = d == 1 ? 7 : d - 1;
        return addDays(dayBegin(value), 1 - wd);
    }


    /**
     * 给出当周初时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date nextWeekBegin(Date value) {
        return addDays(weekBegin(value), 7);
    }

    /**
     * 给出当周末时间（不考虑特殊日期）
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date weekEnd(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(value);

        int d = at.get(Calendar.DAY_OF_WEEK);
        int wd = d == 1 ? 7 : d - 1;
        return addDays(dayEnd(value), 7 - wd);
    }


    /**
     * 给出当月初时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date monthBegin(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(value);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, at.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, at.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 给出当月末时间（不考虑特殊日期）
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date monthEnd(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(monthBegin(value));

        at.add(Calendar.MONTH, 1);
        at.add(Calendar.SECOND, -1);
        return at.getTime();
    }

    /**
     * 给出当年起始时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date yearBegin(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(value);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, at.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 给出当年结束时间（不考虑特殊日期）
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date yearEnd(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(yearBegin(value));

        at.add(Calendar.YEAR, 1);
        at.add(Calendar.SECOND, -1);
        return at.getTime();
    }

    /**
     * 给出明天时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date nextDay(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(value);

        at.add(Calendar.DAY_OF_MONTH, 1);
        return at.getTime();
    }

    /**
     * 给出下月初时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date nextMonthBegin(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(monthBegin(value));

        at.set(Calendar.DAY_OF_MONTH, 1);
        at.add(Calendar.MONTH, 1);
        return at.getTime();
    }

    /**
     * 给出明年初时间
     *
     * @param value 给定时间
     * @return 日期
     */
    public static Date nextYearBegin(Date value) {
        Calendar at = Calendar.getInstance();
        at.setTime(monthBegin(value));

        at.set(Calendar.DAY_OF_MONTH, 1);
        at.set(Calendar.MONTH, 1);
        at.add(Calendar.YEAR, 1);
        return at.getTime();
    }

    /**
     * 根据年月生成日期
     *
     * @param year
     * @param month
     * @return
     */
    public static Date from(int year, int month) {
        Calendar at = Calendar.getInstance();
        at.set(Calendar.DAY_OF_MONTH, 1);
        at.set(Calendar.MONTH, month);
        at.set(Calendar.YEAR, year);
        return at.getTime();
    }


    /**
     * 将日期转换为字符串
     *
     * @param value 给定时间
     * @return 字符串
     */
    public static String asNum(Date value, int ele) {
        return as(value, ele, "", "", "");
    }

    /**
     * 将日期转换为字符串
     *
     * @param value 给定时间
     * @return 字符串
     */
    public static String asStr(Date value) {
        return as(value, 6, "-", " ", ":");
    }

    /**
     * 将日期转换为字符串
     *
     * @param value 给定时间
     * @param ele   元素位数代码
     * @return 字符串
     */
    public static String asStr(Date value, int ele) {
        return as(value, ele, "-", " ", ":");
    }

    /**
     * 将日期转换为字符串
     *
     * @param value 给定时间
     * @return 字符串
     */
    public static String asStr(Date value, String tpl) {
        return as(value, tpl, "-", " ", ":");
    }

    /**
     * 将日期转换为字符串
     *
     * @param value     日期
     * @param ele       显示元素个数代码
     * @param daySplit  日期间隔符
     * @param dtSplit   日期时间间隔符
     * @param timeSplit 时间间隔符
     * @return 字符串
     */
    private static String as(Date value, int ele, String daySplit, String dtSplit, String timeSplit) {
        String dfTpl = "yyyy{0}MM{0}dd";
        switch (ele) {
            case 1:
                dfTpl = "yyyy";
                break;
            case 2:
                dfTpl = "yyyy{0}MM";
                break;
            case 3:
                dfTpl = "yyyy{0}MM{0}dd";
                break;
            case 4:
                dfTpl = "yyyy{0}MM{0}dd{1}HH";
                break;
            case 5:
                dfTpl = "yyyy{0}MM{0}dd{1}HH{2}mm";
                break;
            case 6:
                dfTpl = "yyyy{0}MM{0}dd{1}HH{2}mm{2}ss";
                break;
            case 7:
                dfTpl = "yyyy{0}MM{0}dd{1}HH{2}mm.SSS";
                break;
            case -1:
                dfTpl = "ss";
                break;
            case -2:
                dfTpl = "mm{2}ss";
                break;
            case -3:
                dfTpl = "HH{2}mm{2}ss";
                break;
        }
        return as(value, dfTpl, daySplit, dtSplit, timeSplit);
    }

    public static String asNumToSeconds(Date value) {
        return as(value, 6, "", "", "");
    }

    /**
     * 将日期转换为字符串
     *
     * @param value     日期
     * @param dfTpl     输入格式
     * @param daySplit  日期间隔符
     * @param dtSplit   日期时间间隔符
     * @param timeSplit 时间间隔符
     * @return 字符串
     */
    public static String as(Date value, String dfTpl, String daySplit, String dtSplit, String timeSplit) {
        if (value == null) return null;
        Object[] args = {daySplit, dtSplit, timeSplit};
        MessageFormat fmt = new MessageFormat(dfTpl);
        String dfStr = fmt.format(args);

        SimpleDateFormat df = new SimpleDateFormat(dfStr);
        return df.format(value);
    }

    /**
     * 获得过去5天的日期
     *
     * @return
     */
    public static List<String> getLastFiveDays() {
        return getDays(5, false, "MM月dd日");
    }

    /**
     * 将字符串转换为最近的日期
     *
     * @param value
     * @return
     */
    public static Date asRecentDate(String value) {

        String[] parts = value.split("[月|日]");
        if (parts.length != 2) return null;

        Date today = today();
        int year = getYear(today);
        int month = Integer.valueOf(parts[0]);
        int day = Integer.valueOf(parts[1]);
        if (month == 12 && getMonth(today) == 1) year -= 1;
        return asDate(year, month, day);
    }

    /**
     * 获得最近的日期
     *
     * @param days   天数
     * @param isAsc  升降序
     * @param format 日期格式
     * @return 列表
     */
    public static List<String> getDays(int days, boolean isAsc, String format) {
        List<String> result = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        int pos = isAsc ? 1 : -1;
        Date today = today();
        for (int i = 0; i < days; i++) {
            Date at = addDays(today, i * pos);
            result.add(df.format(at));
        }
        return result;
    }

    /**
     * 获得日期的年份
     *
     * @param at 日期
     * @return 年度
     */
    public static int getYear(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得日期的月份
     *
     * @param at 日期
     * @return 月份
     */
    public static int getMonth(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得日期的月份
     *
     * @param at 日期
     * @return 月份
     */
    public static int getDay(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得日期的小时
     *
     * @param at 日期
     * @return 月份
     */
    public static int getHour(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得星期几
     *
     * @param at 日期
     * @return 周一到周日：1-7
     */
    public static int getWeekday(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        int d = c.get(Calendar.DAY_OF_WEEK);
        return d == 1 ? 7 : d - 1;
    }

    /**
     * 获得第几周
     *
     * @param at 日期
     * @return 周一到周日：1-7
     */
    public static int getWeek(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        int w = c.get(Calendar.WEEK_OF_YEAR);
        return w;
    }

    /**
     * 获得第几周
     *
     * @param at 日期
     * @return
     */
    public static int getWeekFromMonday(Date at) {
        Calendar c = Calendar.getInstance();
        c.setTime(at);
        int d = c.get(Calendar.DAY_OF_WEEK);
        int w = c.get(Calendar.WEEK_OF_YEAR);
        return w - (d == 1 ? 1 : 0);
    }

    /**
     * 获得日期
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 日期
     */
    public static Date asDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 获取日期锁，超过指定天数上锁
     *
     * @param at
     * @param days
     * @return
     */
    public static boolean getDaysLock(Date at, int days) {
        Date deadline = addDays(at, days);
        return new Date().after(deadline);
    }

}
