package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.entity.dto.FullReservationDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;
import fr.eql.ai116.linus.wattelse.entity.pojo.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationBusiness {
    void registerNewReservation(String token, long idVehicule, long idStation, LocalDate dateReservation, long idHeureDebutReservation, long idMinuteDebutReservation, long idHeureFinReservation, long idMinuteFinReservation);
    void updateReservation(Reservation reservation);
    void startCharging(long reservationId);
    void endCharging(long reservationId);
    List<FullReservationDto> fetchUserFullReservations(long userId);
    Long ratingReservation(Rating rating);
}
