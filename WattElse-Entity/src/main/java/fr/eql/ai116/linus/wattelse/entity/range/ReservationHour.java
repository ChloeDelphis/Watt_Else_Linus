package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class ReservationHour implements Serializable {

    private Long idHour;
    private Integer hour;

    /// Constructeur vide
    public ReservationHour() {
    }

    /// Constructeur surchargé
    public ReservationHour(Long idHour, Integer hour) {
        this.idHour = idHour;
        this.hour = hour;
    }

    /// Getters
    public Long getIdHour() {
        return idHour;
    }

    public Integer getHour() {
        return hour;
    }

    /// Setters
    public void setIdHour(Long idHour) {
        this.idHour = idHour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
