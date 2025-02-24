package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class TypeTarification implements Serializable  {

    private Long typeTarificationId;
    private String labelTypeTarification;
    private String abbreviationTypeTarification;

    ///  Constructeur vide
    public TypeTarification() {
    }

    ///  Constructeurs surchargés
    public TypeTarification(Long typeTarificationId, String labelTypeTarification, String abbreviationTypeTarification) {
        this.typeTarificationId = typeTarificationId;
        this.labelTypeTarification = labelTypeTarification;
        this.abbreviationTypeTarification = abbreviationTypeTarification;
    }

    /// Getters
    public Long getTypeTarificationId() {
        return typeTarificationId;
    }

    public String getLabelTypeTarification() {
        return labelTypeTarification;
    }

    public String getAbbreviationTypeTarification() {
        return abbreviationTypeTarification;
    }

    /// Setters
    public void setTypeTarificationId(Long typeTarificationId) {
        this.typeTarificationId = typeTarificationId;
    }

    public void setLabelTypeTarification(String labelTypeTarification) {
        this.labelTypeTarification = labelTypeTarification;
    }

    public void setAbbreviationTypeTarification(String abbreviationTypeTarification) {
        this.abbreviationTypeTarification = abbreviationTypeTarification;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "TypeTarification{" +
                "typeTarificationId=" + typeTarificationId +
                ", labelTypeTarification='" + labelTypeTarification + '\'' +
                ", abbreviationTypeTarification='" + abbreviationTypeTarification + '\'' +
                '}';
    }
}
