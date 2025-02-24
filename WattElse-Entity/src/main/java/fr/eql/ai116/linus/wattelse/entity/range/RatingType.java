package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class RatingType implements Serializable {
    private Long idRatingType;
    private String labelRatingType;

    /// Constructeur vide
    public RatingType() {
    }

    /// Constructeur surchargé
    public RatingType(Long idRatingType, String labelRatingType) {
        this.idRatingType = idRatingType;
        this.labelRatingType = labelRatingType;
    }

    ///  Getters
    public Long getIdRatingType() {
        return idRatingType;
    }

    public String getLabelRatingType() {
        return labelRatingType;
    }

    ///  Setters

    public void setIdRatingType(Long idRatingType) {
        this.idRatingType = idRatingType;
    }

    public void setLabelRatingType(String labelRatingType) {
        this.labelRatingType = labelRatingType;
    }
}
