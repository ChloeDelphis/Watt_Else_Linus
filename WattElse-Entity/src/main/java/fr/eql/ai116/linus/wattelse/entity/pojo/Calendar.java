package fr.eql.ai116.linus.wattelse.entity.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class Calendar implements Serializable {

    CalendarDay[] j;

    public Calendar() {
        this.j = new CalendarDay[7];
        for (int i = 0; i < j.length; i++) {
            j[i] = new CalendarDay();
        }
    }

    public CalendarDay[] getJ() {
        return j;
    }

    public void setJ(CalendarDay[] j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "days=" + Arrays.toString(j) +
                '}';
    }
}
