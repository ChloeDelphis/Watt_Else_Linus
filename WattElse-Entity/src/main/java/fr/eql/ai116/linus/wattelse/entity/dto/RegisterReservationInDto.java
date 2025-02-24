package fr.eql.ai116.linus.wattelse.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RegisterReservationInDto implements Serializable {

    private String token;
    private long idVehicule;
    private long idStation;
    private String dateReservation;
    private long idHeureDebutReservation;
    private long idMinuteDebutReservation;
    private long idHeureFinReservation;
    private long idMinuteFinReservation;

    public RegisterReservationInDto(String token, long idVehicule, long idStation, String dateReservation, long idHeureDebutReservation, long idMinuteDebutReservation, long idHeureFinReservation, long idMinuteFinReservation) {
        this.token = token;
        this.idVehicule = idVehicule;
        this.idStation = idStation;
        this.dateReservation = dateReservation;
        this.idHeureDebutReservation = idHeureDebutReservation;
        this.idMinuteDebutReservation = idMinuteDebutReservation;
        this.idHeureFinReservation = idHeureFinReservation;
        this.idMinuteFinReservation = idMinuteFinReservation;
    }

    public RegisterReservationInDto() {
    }

    public long getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(long idVehicule) {
        this.idVehicule = idVehicule;
    }

    public long getIdStation() {
        return idStation;
    }

    public void setIdStation(long idStation) {
        this.idStation = idStation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public long getIdHeureDebutReservation() {
        return idHeureDebutReservation;
    }

    public void setIdHeureDebutReservation(long idHeureDebutReservation) {
        this.idHeureDebutReservation = idHeureDebutReservation;
    }

    public long getIdMinuteDebutReservation() {
        return idMinuteDebutReservation;
    }

    public void setIdMinuteDebutReservation(long idMinuteDebutReservation) {
        this.idMinuteDebutReservation = idMinuteDebutReservation;
    }

    public long getIdHeureFinReservation() {
        return idHeureFinReservation;
    }

    public void setIdHeureFinReservation(long idHeureFinReservation) {
        this.idHeureFinReservation = idHeureFinReservation;
    }

    public long getIdMinuteFinReservation() {
        return idMinuteFinReservation;
    }

    public void setIdMinuteFinReservation(long idMinuteFinReservation) {
        this.idMinuteFinReservation = idMinuteFinReservation;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
