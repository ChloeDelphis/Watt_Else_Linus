package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.CalendarBusiness;
import fr.eql.ai116.linus.wattelse.dao.CalendarDao;
import fr.eql.ai116.linus.wattelse.dao.ReservationDao;
import fr.eql.ai116.linus.wattelse.entity.CalendarDisponibility;
import fr.eql.ai116.linus.wattelse.entity.pojo.Calendar;
import fr.eql.ai116.linus.wattelse.entity.pojo.CalendarDay;
import fr.eql.ai116.linus.wattelse.entity.pojo.HourSlots;
import fr.eql.ai116.linus.wattelse.entity.pojo.OpeningHour;
import fr.eql.ai116.linus.wattelse.entity.pojo.Reservation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Remote(CalendarBusiness.class)
@Stateless
public class CalendarBusinessImpl implements CalendarBusiness {

    @EJB
    private CalendarDao calendarDao;

    @Override
    public Calendar getStationCalendar(long stationId) {
        return calendarDao.getCalendar(stationId,LocalDate.now());
    }
}
