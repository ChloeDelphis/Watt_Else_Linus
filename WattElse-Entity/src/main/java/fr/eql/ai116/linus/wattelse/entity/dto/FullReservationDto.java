package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.pojo.CreditCard;
import fr.eql.ai116.linus.wattelse.entity.pojo.Facturation;
import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationAnormalEnding;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationHour;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationMinute;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FullReservationDto implements Serializable {
    private Long idReservation;
    private VehicleDto vehicleDto;
    private Station station;
    private LocalDate dateReservation;
    private ReservationHour startReservationHours;
    private ReservationMinute startReservationMinutes;
    private ReservationHour endReservationHours;
    private ReservationMinute endReservationMinutes;
    private Facturation facturation;
    private LocalDate reservationRegisterDate;
    private CreditCardDto creditCardDto;
    private Integer energyConsumed;
    private Double cost;
    private Rating rating;
    private LocalDateTime dateDebutRecharge;
    private LocalDateTime dateFinRecharge;
    private LocalDateTime dateDriverFacturation;
    private LocalDateTime dateAnormalEnding;
    private ReservationAnormalEnding reservationAnormalEnding;

    ///Constructeur vide
    public FullReservationDto() {
    }

    /// Constructeur surchargé
    public FullReservationDto(Long idReservation,
                              VehicleDto vehicleDto,
                              Station station,
                              LocalDate dateReservation,
                              ReservationHour startReservationHours,
                              ReservationMinute startReservationMinutes,
                              ReservationHour endReservationHours,
                              ReservationMinute endReservationMinutes,
                              Facturation facturation,
                              LocalDate reservationRegisterDate,
                              CreditCardDto creditCardDto, Integer energyConsumed,
                              Double cost,
                              Rating rating,
                              LocalDateTime dateDebutRecharge,
                              LocalDateTime dateFinRecharge,
                              LocalDateTime dateDriverFacturation,
                              LocalDateTime dateAnormalEnding,
                              ReservationAnormalEnding reservationAnormalEnding) {
        this.idReservation = idReservation;
        this.vehicleDto = vehicleDto;
        this.station = station;
        this.dateReservation = dateReservation;
        this.startReservationHours = startReservationHours;
        this.startReservationMinutes = startReservationMinutes;
        this.endReservationHours = endReservationHours;
        this.endReservationMinutes = endReservationMinutes;
        this.facturation = facturation;
        this.reservationRegisterDate = reservationRegisterDate;
        this.creditCardDto = creditCardDto;
        this.energyConsumed = energyConsumed;
        this.cost = cost;
        this.rating = rating;
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

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public Station getStation() {
        return station;
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

    public Facturation getFacturation() {
        return facturation;
    }

    public LocalDate getReservationRegisterDate() {
        return reservationRegisterDate;
    }

    public CreditCardDto getCreditCardDto() {
        return creditCardDto;
    }

    public Integer getEnergyConsumed() {
        return energyConsumed;
    }

    public Double getCost() {
        return cost;
    }

    public Rating getRating() {
        return rating;
    }

    public LocalDateTime getDateDebutRecharge() {
        return dateDebutRecharge;
    }

    public LocalDateTime getDateFinRecharge() {
        return dateFinRecharge;
    }

    public LocalDateTime getDateDriverFacturation() {
        return dateDriverFacturation;
    }

    public LocalDateTime getDateAnormalEnding() {
        return dateAnormalEnding;
    }

    public ReservationAnormalEnding getReservationAnormalEnding() {
        return reservationAnormalEnding;
    }

    /// Setters
    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public void setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }

    public void setStation(Station station) {
        this.station = station;
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

    public void setFacturation(Facturation facturation) {
        this.facturation = facturation;
    }

    public void setReservationRegisterDate(LocalDate reservationRegisterDate) {
        this.reservationRegisterDate = reservationRegisterDate;
    }

    public void setCreditCardDto(CreditCardDto creditCardDto) {
        this.creditCardDto = creditCardDto;
    }

    public void setEnergyConsumed(Integer energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setDateDebutRecharge(LocalDateTime dateDebutRecharge) {
        this.dateDebutRecharge = dateDebutRecharge;
    }

    public void setDateFinRecharge(LocalDateTime dateFinRecharge) {
        this.dateFinRecharge = dateFinRecharge;
    }

    public void setDateDriverFacturation(LocalDateTime dateDriverFacturation) {
        this.dateDriverFacturation = dateDriverFacturation;
    }

    public void setDateAnormalEnding(LocalDateTime dateAnormalEnding) {
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
                ", vehicleDto=" + vehicleDto +
                ", station=" + station +
                ", dateReservation=" + dateReservation +
                ", startReservationHours=" + startReservationHours +
                ", startReservationMinutes=" + startReservationMinutes +
                ", endReservationHours=" + endReservationHours +
                ", endReservationMinutes=" + endReservationMinutes +
                ", facturation=" + facturation +
                ", reservationRegisterDate=" + reservationRegisterDate +
                ", creditcardDto=" + creditCardDto +
                ", energyConsumed=" + energyConsumed +
                ", cost=" + cost +
                ", rating=" + rating +
                ", dateDebutRecharge=" + dateDebutRecharge +
                ", dateFinRecharge=" + dateFinRecharge +
                ", dateDriverFacturation=" + dateDriverFacturation +
                ", dateAnormalEnding=" + dateAnormalEnding +
                ", reservationAnormalEnding=" + reservationAnormalEnding +
                '}';
    }

}
