package fr.eql.ai116.linus.wattelse.dao.impl.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {
    public static LocalDate convert(Date date) {
        if (date == null) return null;
        else return date.toLocalDate();
    }

    public static Date convert(LocalDate date) {
        if (date == null) return null;
        else return Date.valueOf(date);
    }

    public static LocalDateTime convert(Timestamp date) {
        if (date == null) return null;
        else return date.toLocalDateTime();
    }

    public static Timestamp convert(LocalDateTime date) {
        if (date == null) return null;
        else return Timestamp.valueOf(date);
    }
}
