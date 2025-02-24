package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;

import java.io.Serializable;
import java.time.LocalDate;

public class Tarification implements Serializable {

    private Long tarificationId;
    private TypeTarification typeTarification;
    private Double cost;
    private LocalDate dateTarificationStart;
    private LocalDate dateTarificationEnd;

    /// Constructeur vide
    public Tarification() {
    }

    /// Constructeur surchargé
    public Tarification(Long tarificationId, TypeTarification typeTarification, Double cost,
                        LocalDate dateTarificationStart, LocalDate dateTarificationEnd) {
        this.tarificationId = tarificationId;
        this.typeTarification = typeTarification;
        this.cost = cost;
        this.dateTarificationStart = dateTarificationStart;
        this.dateTarificationEnd = dateTarificationEnd;
    }

    /// Getters ///
    public Long getTarificationId() {
        return tarificationId;
    }

    public TypeTarification getTypeTarification() {
        return typeTarification;
    }

    public Double getCost() {
        return cost;
    }

    public LocalDate getDateTarificationStart() {
        return dateTarificationStart;
    }

    public LocalDate getDateTarificationEnd() {
        return dateTarificationEnd;
    }
    /// Setters ///
    public void setTarificationId(Long tarificationId) {
        this.tarificationId = tarificationId;
    }

    public void setTypeTarification(TypeTarification typeTarification) {
        this.typeTarification = typeTarification;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setDateTarificationStart(LocalDate dateTarificationStart) {
        this.dateTarificationStart = dateTarificationStart;
    }

    public void setDateTarificationEnd(LocalDate dateTarificationEnd) {
        this.dateTarificationEnd = dateTarificationEnd;
    }

    @Override
    public String toString() {
        return "Tarification{" +
                "tarificationId=" + tarificationId +
                ", typeTarification=" + typeTarification +
                ", cost=" + cost +
                ", dateTarificationStart=" + dateTarificationStart +
                ", dateTarificationEnd=" + dateTarificationEnd +
                '}';
    }
}
