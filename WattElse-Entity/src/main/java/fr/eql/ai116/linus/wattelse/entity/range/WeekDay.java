package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class WeekDay implements Serializable {
    private Long idWeekDay;
    private String labelWeekDay;


    /// Constructeur vide
    public WeekDay() {
    }

    /// Constructeur surchargé
    public WeekDay(Long idWeekDay, String labelWeekDay) {
        this.idWeekDay = idWeekDay;
        this.labelWeekDay = labelWeekDay;
    }

    /// Getters
    public Long getIdWeekDay() {
        return idWeekDay;
    }

    public String getLabelWeekDay() {
        return labelWeekDay;
    }

    /// Setters
    public void setIdWeekDay(Long idWeekDay) {
        this.idWeekDay = idWeekDay;
    }

    public void setLabelWeekDay(String labelWeekDay) {
        this.labelWeekDay = labelWeekDay;
    }
}
