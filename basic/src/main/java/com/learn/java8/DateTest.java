package com.learn.java8;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTest {
    
    /**
     * ZoneId: 时区ID，用来确定Instant和LocalDateTime互相转换的规则
        Instant: 用来表示时间线上的一个点
        Clock: 用于访问当前时刻、日期、时间，用到时区
        Duration: 用秒和纳秒表示时间的数量
     */
    
    /**
     * LocalDate: 表示没有时区的日期, LocalDate是不可变并且线程安全的
     * LocalDate代表一个IOS格式(yyyy-MM-dd)的日期
     */
    public static void printLocalDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("tomorrow:" + tomorrow);
        
        LocalDate lastMonth = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println("lastMonth:" + lastMonth);
        
        DayOfWeek weekDay = LocalDate.parse("2018-05-29").getDayOfWeek();
        System.out.println("weekDay:" + weekDay);
        
        int monthDay = LocalDate.parse("2018-05-29").getDayOfMonth();
        System.out.println("monthDay:" + monthDay);

        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println("是否闰年:" + leapYear);

        boolean before = LocalDate.parse("2017-07-20").isBefore(LocalDate.parse("2017-07-22"));
        System.out.println("isBefore:" + before);
        
        boolean after = LocalDate.parse("2017-07-20").isAfter(LocalDate.parse("2017-07-22"));
        System.out.println("isAfter:" + after);

        LocalDate firstDayOfMonth = LocalDate.parse("2017-07-20").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth:" + firstDayOfMonth);
        
        firstDayOfMonth = LocalDate.parse("2017-07-20").withDayOfMonth(1);
        System.out.println("firstDayOfMonth: " + firstDayOfMonth);
    }
    
    /**
     * LocalTime:表示没有时区的时间, LocalTime是不可变并且线程安全的
     * LocalTime:表示一个时间，输出格式：15:01:22.144
     */
    public static void printLocalTime() {
        LocalTime now = LocalTime.now();
        System.out.println("现在的时间: " + now);
        
        LocalTime nowTime = LocalTime.parse("15:02");
        System.out.println("时间是: " + nowTime);

        nowTime = LocalTime.of(15, 02);
        System.out.println("时间是: " + nowTime);
        
        LocalTime nextHour = LocalTime.parse("15:02:11.333").plus(1, ChronoUnit.HOURS);
        System.out.println("下一个小时: " + nextHour);

        int hour = LocalTime.parse("15:02").getHour();
        System.out.println("小时: " + hour);
        int minute = LocalTime.parse("15:02").getMinute();
        System.out.println("分钟: " + minute);

        boolean isBefore = LocalTime.parse("15:02").isBefore(LocalTime.parse("16:02"));
        boolean isAfter = LocalTime.parse("15:02").isAfter(LocalTime.parse("16:02"));
        System.out.println("isBefore: " + isBefore);
        System.out.println("isAfter: " + isAfter);

        System.out.println(LocalTime.MAX);
        System.out.println(LocalTime.MIN);
    }

    /**
     * LocalDateTime: 表示没有时区的日期时间, LocalDateTime是不可变并且线程安全的
     * 输出格式：2017-07-20T15:17:19.926
     * 
     */
    public static void printLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);

        LocalDateTime.of(2017, Month.JULY, 20, 15, 18);
        LocalDateTime.parse("2017-07-20T15:18:00");

        LocalDateTime tomorrow = now.plusDays(1);
        System.out.println("明天的这个时间: " + tomorrow);
        LocalDateTime minusTowHour = now.minusHours(2);
        System.out.println("两小时前: " + minusTowHour);

        Month month = now.getMonth();
        System.out.println("当前月份: " + month);
    }
    
    public static void printFormat() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("默认格式化: " + now);
        System.out.println("自定义格式化: " + now.format(dateTimeFormatter));
        LocalDateTime localDateTime = LocalDateTime.parse("2017-07-20 15:27:44", dateTimeFormatter);
        System.out.println("字符串转LocalDateTime: " + localDateTime);

        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = dateTimeFormatter.format(LocalDate.now());
        System.out.println("日期转字符串: " + dateString);
    }
    
    /**
     * Period类用于修改给定日期或获得的两个日期之间的区别
     */
    public static void printPeriod() {
        LocalDate initialDate = LocalDate.parse("2017-07-20");
        LocalDate finalDate   = initialDate.plus(Period.ofDays(5));
        System.out.println("初始化日期: " + initialDate);
        System.out.println("加日期之后: " + finalDate);
        long between = ChronoUnit.DAYS.between(initialDate, finalDate);
        System.out.println("差距天数: " + between);
    }
    
    /**
     * 遗留转换
     */
    public static void printConver() {
        // Date和Instant互相转换
        Date date = Date.from(Instant.now());
        Instant instant = date.toInstant();
        
        // Date转换为LocalDateTime
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = new Date().toInstant().atZone(zoneId).toLocalDateTime();
        System.out.println(localDateTime);
        
        // LocalDateTime转Date
        date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        // LocalDate转Date

        date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static void main(String[] args) {
        printLocalDate();
        printLocalTime();
        printLocalDateTime();
    }

}
