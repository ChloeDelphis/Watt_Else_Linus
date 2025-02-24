package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;


public class ReservationAnormalEnding implements Serializable {

    private Long anormalEndingId;
    private String anormalEndingLabel;

    /// Constructeur vide
    public ReservationAnormalEnding() {
    }

    /// Constructeur surchargé
    public ReservationAnormalEnding(Long anormalEndingId, String anormalEndingLabel) {
        this.anormalEndingId = anormalEndingId;
        this.anormalEndingLabel = anormalEndingLabel;
    }

    /// Getters
    public Long getAnormalEndingId() {
        return anormalEndingId;
    }

    public String getAnormalEndingLabel() {
        return anormalEndingLabel;
    }

    /// Setters
    public void setAnormalEndingId(Long anormalEndingId) {
        this.anormalEndingId = anormalEndingId;
    }

    public void setAnormalEndingLabel(String anormalEndingLabel) {
        this.anormalEndingLabel = anormalEndingLabel;
    }

}
