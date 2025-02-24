package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.ReservationAnormalEnding;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationHour;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationMinute;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation implements Serializable {
    private Long idReservation;
    private Long idVehicule;
    private Long idStation;
    private LocalDate dateReservation;
    private ReservationHour startReservationHours;
    private ReservationMinute startReservationMinutes;
    private ReservationHour endReservationHours;
    private ReservationMinute endReservationMinutes;
    private Long idFacturation;
    private LocalDate reservationRegisterDate;
    private Long idCreditcard;
    private Float energyConsumed;
    private Double cost;
    private Long idRating;
    private LocalDateTime dateDebutRecharge;
    private LocalDateTime dateFinRecharge;
    private LocalDate dateDriverFacturation;
    private LocalDate dateAnormalEnding;
    private ReservationAnormalEnding reservationAnormalEnding;

    ///Constructeur vide
    public Reservation() {
    }

    /// Constructeur surchargé
    public Reservation(Long idReservation, Long idVehicule, Long idStation, LocalDate dateReservation, ReservationHour startReservationHours, ReservationMinute startReservationMinutes, ReservationHour endReservationHours, ReservationMinute endReservationMinutes, Long idFacturation, LocalDate reservationRegisterDate, Long idCreditcard, Float energyConsumed, Double cost, Long idRating, LocalDateTime dateDebutRecharge, LocalDateTime dateFinRecharge, LocalDate dateDriverFacturation, LocalDate dateAnormalEnding, ReservationAnormalEnding reservationAnormalEnding) {
        this.idReservation = idReservation;
        this.idVehicule = idVehicule;
        this.idStation = idStation;
        this.dateReservation = dateReservation;
        this.startReservationHours = startReservationHours;
        this.startReservationMinutes = startReservationMinutes;
        this.endReservationHours = endReservationHours;
        this.endReservationMinutes = endReservationMinutes;
        this.idFacturation = idFacturation;
        this.reservationRegisterDate = reservationRegisterDate;
        this.idCreditcard = idCreditcard;
        this.energyConsumed = energyConsumed;
        this.cost = cost;
        this.idRating = idRating;
        this.dateDebutRecharge = dateDebutRecharge;
        this.dateFinRecharge = dateFinRecharge;
        this.dateDriverFacturation = dateDriverFacturation;
        this.dateAnormalEnding = dateAnormalEnding;
        this.reservationAnormalEnding = reservationAnormalEnding;
    }

    /// Getters
    public Long getIdReservation() {
        return idReservation;
    }

    public Long getIdVehicule() {
        return idVehicule;
    }

    public Long getIdStation() {
        return idStation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public ReservationHour getStartReservationHours() {
        return startReservationHours;
    }

    public ReservationMinute getStartReservationMinutes() {
        return startReservationMinutes;
    }

    public ReservationHour getEndReservationHours() {
        return endReservationHours;
    }

    public ReservationMinute getEndReservationMinutes() {
        return endReservationMinutes;
    }

    public Long getIdFacturation() {
        return idFacturation;
    }

    public LocalDate getReservationRegisterDate() {
        return reservationRegisterDate;
    }

    public Long getIdCreditcard() {
        return idCreditcard;
    }

    public Float getEnergyConsumed() {
        return energyConsumed;
    }

    public Double getCost() {
        return cost;
    }

    public Long getIdRating() {
        return idRating;
    }

    public LocalDateTime getDateDebutRecharge() {
        return dateDebutRecharge;
    }

    public LocalDateTime getDateFinRecharge() {
        return dateFinRecharge;
    }

    public LocalDate getDateDriverFacturation() {
        return dateDriverFacturation;
    }

    public LocalDate getDateAnormalEnding() {
        return dateAnormalEnding;
    }

    public ReservationAnormalEnding getReservationAnormalEnding() {
        return reservationAnormalEnding;
    }

    /// Setters
    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public void setIdVehicule(Long idVehicule) {
        this.idVehicule = idVehicule;
    }

    public void setIdStation(Long idStation) {
        this.idStation = idStation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setStartReservationHours(ReservationHour startReservationHours) {
        this.startReservationHours = startReservationHours;
    }

    public void setStartReservationMinutes(ReservationMinute startReservationMinutes) {
        this.startReservationMinutes = startReservationMinutes;
    }

    public void setEndReservationHours(ReservationHour endReservationHours) {
        this.endReservationHours = endReservationHours;
    }

    public void setEndReservationMinutes(ReservationMinute endReservationMinutes) {
        this.endReservationMinutes = endReservationMinutes;
    }

    public void setIdFacturation(Long idFacturation) {
        this.idFacturation = idFacturation;
    }

    public void setReservationRegisterDate(LocalDate reservationRegisterDate) {
        this.reservationRegisterDate = reservationRegisterDate;
    }

    public void setIdCreditcard(Long idCreditcard) {
        this.idCreditcard = idCreditcard;
    }

    public void setEnergyConsumed(Float energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setIdRating(Long idRating) {
        this.idRating = idRating;
    }

    public void setDateDebutRecharge(LocalDateTime dateDebutRecharge) {
        this.dateDebutRecharge = dateDebutRecharge;
    }

    public void setDateFinRecharge(LocalDateTime dateFinRecharge) {
        this.dateFinRecharge = dateFinRecharge;
    }

    public void setDateDriverFacturation(LocalDate dateDriverFacturation) {
        this.dateDriverFacturation = dateDriverFacturation;
    }

    public void setDateAnormalEnding(LocalDate dateAnormalEnding) {
        this.dateAnormalEnding = dateAnormalEnding;
    }

    public void setReservationAnormalEnding(ReservationAnormalEnding reservationAnormalEnding) {
        this.reservationAnormalEnding = reservationAnormalEnding;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", idVehicule=" + idVehicule +
                ", idStation=" + idStation +
                ", dateReservation=" + dateReservation +
                ", startReservationHours=" + startReservationHours +
                ", startReservationMinutes=" + startReservationMinutes +
                ", endReservationHours=" + endReservationHours +
                ", endReservationMinutes=" + endReservationMinutes +
                ", idFacturation=" + idFacturation +
                ", reservationRegisterDate=" + reservationRegisterDate +
                ", idCreditcard=" + idCreditcard +
                ", energyConsumed=" + energyConsumed +
                ", cost=" + cost +
                ", idRating=" + idRating +
                ", dateDebutRecharge=" + dateDebutRecharge +
                ", dateFinRecharge=" + dateFinRecharge +
                ", dateDriverFacturation=" + dateDriverFacturation +
                ", dateAnormalEnding=" + dateAnormalEnding +
                ", reservationAnormalEnding=" + reservationAnormalEnding +
                '}';
    }
}
