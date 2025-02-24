package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.WeekDay;

import java.io.Serializable;

public class OpeningHour implements Serializable {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private WeekDay weekDay;

    public OpeningHour(int startHour, int startMinute, int endHour, int endMinute, WeekDay weekDay) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.weekDay = weekDay;
    }
}
