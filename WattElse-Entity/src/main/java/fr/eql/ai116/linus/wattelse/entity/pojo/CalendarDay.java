package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.CalendarDisponibility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalendarDay implements Serializable {
    private List<HourSlots> openingHours;
    private List<HourSlots> reservations;

    public CalendarDay() {
        this.openingHours = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public List<HourSlots> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<HourSlots> openingHours) {
        this.openingHours = openingHours;
    }

    public List<HourSlots> getReservations() {
        return reservations;
    }

    public void setReservations(List<HourSlots> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "CalendarDay{" +
                "openingHours=" + openingHours +
                ", reservations=" + reservations +
                '}';
    }
}
