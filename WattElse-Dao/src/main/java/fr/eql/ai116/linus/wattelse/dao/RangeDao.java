package fr.eql.ai116.linus.wattelse.dao;

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

import java.util.List;

public interface RangeDao {
    List<Socket> fetchSockets();
    List<Power> fetchPower();
    List<TypeTarification> fetchTarification();

    List<ReservationHour> fetchReservationHour();
    List<ReservationMinute> fetchReservationMinute();
    List<WeekDay> fetchWeekDays();

    List<BanMotive> fetchBanMotive();
    List<StationClosingMotive> fetchStationClosingMotive();
    List<AccountClosingMotive> fetchAccountClosingMotive();

    List<ReservationAnormalEnding> fetchReservationAnormalEnding();
    List<RatingType> fetchRatingType();
    List<StationService> fetchStationService();
}
