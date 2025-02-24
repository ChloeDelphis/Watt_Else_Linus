package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.entity.pojo.Calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CalendarBusiness {
    Calendar getStationCalendar(long stationId);
}
