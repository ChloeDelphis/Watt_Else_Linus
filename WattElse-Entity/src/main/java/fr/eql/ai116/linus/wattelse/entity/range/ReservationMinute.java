package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class ReservationMinute implements Serializable {

    private Long idHour;
    private Integer minutes;

    /// Constructeur vide
    public ReservationMinute() {
    }

    /// Constructeur surchargé
    public ReservationMinute(Long idHour, Integer minutes) {
        this.idHour = idHour;
        this.minutes = minutes;
    }

    /// Getters
    public Long getIdHour() {
        return idHour;
    }

    public Integer getMinutes() {
        return minutes;
    }
    /// Setters
    public void setIdHour(Long idHour) {
        this.idHour = idHour;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
}
