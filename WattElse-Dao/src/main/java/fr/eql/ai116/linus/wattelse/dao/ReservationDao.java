package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.dto.FullReservationDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {
    void registerNewReservation(Reservation reservation, long idHeureDebutReservation, long idMinuteDebutReservation, long idHeureFinReservation, long idMinuteFinReservation);
    void updateReservation(Reservation reservation);

    Reservation getReservationsById(long reservationId);
    List<Reservation> getReservationsByStation(long stationId);
    List<Reservation> getReservationsByVehicle(long vehicleId);
    List<Reservation> getReservationsByOwner(long userId);
    List<Reservation> getReservationsByDriver(long userId);
    List<FullReservationDto> getFullReservationsByVehicle(long vehicleId);
}
