package com.mr.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期时间工具类 (基于 JDK 8+ java.time API)
 */
public class DateUtils {

    // 常用日期时间格式
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取当前日期时间字符串 (默认格式: yyyy-MM-dd HH:mm:ss)
     */
    public static String now() {
        return format(LocalDateTime.now(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化 LocalDateTime
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化 LocalDate
     */
    public static String format(LocalDate date, String pattern) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串解析为 LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) return null; // 需引入 StringUtils 或自行判空
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串解析为 LocalDate
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) return null;
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 增加或减少天数
     *
     * @param days 正数表示增加，负数表示减少
     */
    public static LocalDate plusDays(LocalDate date, long days) {
        if (date == null) return null;
        return date.plus(days, ChronoUnit.DAYS);
    }

    /**
     * 增加或减少月份
     */
    public static LocalDate plusMonths(LocalDate date, long months) {
        if (date == null) return null;
        return date.plus(months, ChronoUnit.MONTHS);
    }

    /**
     * 获取当天的开始时间 (00:00:00)
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        if (date == null) return null;
        return date.atStartOfDay();
    }

    /**
     * 获取当天的结束时间 (23:59:59)
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        if (date == null) return null;
        return date.atTime(LocalTime.MAX);
    }

    /**
     * 获取当前时间的毫秒级时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * LocalDateTime 转换为毫秒时间戳
     */
    public static long toEpochMilli(LocalDateTime dateTime) {
        if (dateTime == null) return 0L;
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 毫秒时间戳转换为 LocalDateTime
     */
    public static LocalDateTime fromEpochMilli(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 计算两个日期之间的天数差
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return 0;
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // ==================== 兼容旧版 Date 的转换方法 ====================

    /**
     * Date 转 LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime 转 Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}