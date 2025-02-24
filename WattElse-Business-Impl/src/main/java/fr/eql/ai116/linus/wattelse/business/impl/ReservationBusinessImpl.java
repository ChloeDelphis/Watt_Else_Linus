package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.ReservationBusiness;
import fr.eql.ai116.linus.wattelse.dao.EvaluationDao;
import fr.eql.ai116.linus.wattelse.dao.FacturationDao;
import fr.eql.ai116.linus.wattelse.dao.ReservationDao;
import fr.eql.ai116.linus.wattelse.dao.StationDao;
import fr.eql.ai116.linus.wattelse.dao.TarificationDao;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.dao.VehicleDao;
import fr.eql.ai116.linus.wattelse.entity.dto.FullReservationDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Facturation;
import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;
import fr.eql.ai116.linus.wattelse.entity.pojo.Reservation;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;
import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Remote(ReservationBusiness.class)
@Stateless
public class ReservationBusinessImpl implements ReservationBusiness {

    @EJB
    private UserDao userDao;

    @EJB
    private ReservationDao reservationDao;

    @EJB
    private FacturationDao facturationDao;

    @EJB
    private VehicleDao vehicleDao;

    @EJB
    private StationDao stationDao;

    @EJB
    private TarificationDao tarificationDao;

    @EJB
    private EvaluationDao evaluationDao;


    @Override
    public void registerNewReservation(String token, long idVehicule, long idStation, LocalDate dateReservation, long idHeureDebutReservation, long idMinuteDebutReservation, long idHeureFinReservation, long idMinuteFinReservation) {

        /// TODO:
        /// Ajouter le token dans les paramètres
        /// vérifier que le propriétaire de la voiture est bien le propriétaire du token envoyé
        /// vérifier que les horaires de réservation soit bien contenu dans les horaires de la borne

        User driver = vehicleDao.getVehicleUser(idVehicule);
        User owner = userDao.getUserByStation(idStation);

        Facturation accurateFacturation = getAccurateFacturation(owner.getIdUtilisateur());

        Reservation reservation = new Reservation(
                -1L,
                idVehicule,
                idStation,
                dateReservation,
                null,
                null,
                null,
                null,
                accurateFacturation.getIdMonthlyFacturation(),
                LocalDate.now(),
                1L,
                0F,
                0D,
                null,
                null,
                null,
                null,
                null,
                null
        );
        reservationDao.registerNewReservation(reservation, idHeureDebutReservation, idMinuteDebutReservation, idHeureFinReservation, idMinuteFinReservation);
    }

    @Override
    public void updateReservation(Reservation reservation){
        reservationDao.updateReservation(reservation);
    }

    @Override
    public void startCharging(long reservationId) {
        Reservation reservation = reservationDao.getReservationsById(reservationId);
        reservation.setDateDebutRecharge(LocalDateTime.now());
        System.out.println(reservation);
        reservationDao.updateReservation(reservation);
    }

    @Override
    public void endCharging(long reservationId) {
        Reservation reservation = reservationDao.getReservationsById(reservationId);
        reservation.setDateFinRecharge(LocalDateTime.now());

        Station station = stationDao.getStationById(reservation.getIdStation());
        Tarification tarification = tarificationDao.getTarification(station.getTarificationId());

        float chargingTime = (Duration.between(reservation.getDateDebutRecharge(),reservation.getDateFinRecharge()).toMillis() / (1000f * 60))/60f;;

        float energyConsumed = chargingTime * station.getPower().getValue();
        reservation.setEnergyConsumed(energyConsumed);

        double cost;
        if (tarification.getTypeTarification().getTypeTarificationId() == 0) { // par heure
            cost = chargingTime * tarification.getCost();
        } else if (tarification.getTypeTarification().getTypeTarificationId() == 1) { // par energie
            cost = energyConsumed * tarification.getCost();
        } else {
            throw new RuntimeException("Type de tarification invalide");
        }

        reservation.setCost(cost);

        facturate(reservation);
        reservationDao.updateReservation(reservation);
    }

    /**
     * Set à mettre à jour la facturation mensuelle du propétaire en ajoutant la prix de la réservation.
     * @param reservation
     */
    private void facturate(Reservation reservation) {
        User owner = userDao.getUserByStation(reservation.getIdStation());
        Facturation facturation = getAccurateFacturation(owner.getIdUtilisateur());
        facturation.addToAmount(reservation.getCost());
        facturationDao.updateFacturation(facturation);
    }

    /**
     * Permet de récupérer la dernière facturation ou de la créer si elle n'existe pas ou que la date de paye est déjà passer
     * @param userId
     * @return
     */
    private Facturation getAccurateFacturation(long userId) {
        Facturation lastFacturation = facturationDao.getLastFacturation(userId);
        if (lastFacturation != null && lastFacturation.getPayementDate().isAfter(LocalDate.now())) {
            return lastFacturation;
        } else {
            return facturationDao.insertNewFacturation(new Facturation(
                    -1L,
                    userId,
                    LocalDate.now().withDayOfMonth(1).plusMonths(1),
                    0D));
        }
    }

    @Override
    public List<FullReservationDto> fetchUserFullReservations(long userId){
        List<FullReservationDto> fullReservationDtos = new ArrayList<>();
        List<Vehicle> vehicles = vehicleDao.fetchUsersVehicles(userId);
        for (Vehicle vehicle : vehicles) {
            List<FullReservationDto> fullReservationDtosByVehicle = reservationDao.getFullReservationsByVehicle(vehicle.getVehicleId());
            fullReservationDtos.addAll(fullReservationDtosByVehicle);
        }
        return fullReservationDtos;
    }

    @Override
    public Long ratingReservation(Rating rating) {
        Long ratingId = evaluationDao.ratingReservation(rating);
        return ratingId;
    }
}
