package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.CalendarDisponibility;
import fr.eql.ai116.linus.wattelse.entity.pojo.Calendar;
import fr.eql.ai116.linus.wattelse.entity.pojo.OpeningHour;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CalendarDao {
    Calendar getCalendar(long stationId, LocalDate start);
    CalendarDisponibility fetchDisponibility(LocalDateTime moment, long stationId);
    CalendarDisponibility findStationDisponibility(LocalDateTime moment, long stationId);


}
