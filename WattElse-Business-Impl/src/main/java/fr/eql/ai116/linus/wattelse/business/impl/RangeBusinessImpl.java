package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.RangeBusiness;
import fr.eql.ai116.linus.wattelse.dao.RangeDao;
import fr.eql.ai116.linus.wattelse.entity.range.AccountClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.BanMotive;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.RatingType;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationAnormalEnding;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationHour;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationMinute;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.StationService;
import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;
import fr.eql.ai116.linus.wattelse.entity.range.WeekDay;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.nio.file.Watchable;
import java.util.Collections;
import java.util.List;

@Remote(RangeBusiness.class)
@Stateless
public class RangeBusinessImpl implements RangeBusiness{

    @EJB
    RangeDao rangeDao;

    @Override
    public List<Socket> fetchSockets() {
        List<Socket> sockets = rangeDao.fetchSockets();
        return sockets;
    }
    @Override
    public List<Power> fetchPower() {
        List<Power> powers = rangeDao.fetchPower();
        return powers;
    }
    @Override
    public List<TypeTarification> fetchTarification() {
        List<TypeTarification> tarifications = rangeDao.fetchTarification();
        return tarifications;
    }

    @Override
    public List<ReservationHour> fetchReservationHour() {
        List<ReservationHour> reservationHours = rangeDao.fetchReservationHour();
        return reservationHours;    }
    @Override
    public List<ReservationMinute> fetchReservationMinute() {
        List<ReservationMinute> reservationMinutes = rangeDao.fetchReservationMinute();
        return reservationMinutes;
    }
    @Override
    public List<WeekDay> fetchWeekDay() {
        List<WeekDay> weekDays = rangeDao.fetchWeekDays();
        return weekDays;
    }

    @Override
    public List<BanMotive> fetchBanMotive() {
        List<BanMotive> banMotives = rangeDao.fetchBanMotive();
        return banMotives;
    }
    @Override
    public List<StationClosingMotive> fetchStationClosingMotive() {
        List<StationClosingMotive> stationClosingMotives = rangeDao.fetchStationClosingMotive();
        return stationClosingMotives;
    }
    @Override
    public List<AccountClosingMotive> fetchAccountClosingMotive() {
        List<AccountClosingMotive> accountClosingMotives = rangeDao.fetchAccountClosingMotive();
        return accountClosingMotives;
    }

    @Override
    public List<ReservationAnormalEnding> fetchReservationAnormalEnding() {
        List<ReservationAnormalEnding> reservationAnormalEndings = rangeDao.fetchReservationAnormalEnding();
        return reservationAnormalEndings;
    }
    @Override
    public List<RatingType> fetchRatingType() {
        List<RatingType> ratingTypes = rangeDao.fetchRatingType();
        return ratingTypes;
    }
    @Override
    public List<StationService> fetchStationService() {
        List<StationService> stationServices = rangeDao.fetchStationService();
        return stationServices;
    }

}
