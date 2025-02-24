package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class StationClosingMotive implements Serializable {

    private Long idStationClosingMotive;
    private String label;

    ///  Constructeur vide
    public StationClosingMotive() {
    }
    ///  Constructeur surchargé
    public StationClosingMotive(Long idStationClosingMotive, String label) {
        this.idStationClosingMotive = idStationClosingMotive;
        this.label = label;
    }

    ///  Getters
    public Long getIdStationClosingMotive() {
        return idStationClosingMotive;
    }

    public String getLabel() {
        return label;
    }

    ///  Setters
    public void setIdStationClosingMotive(Long idStationClosingMotive) {
        this.idStationClosingMotive = idStationClosingMotive;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
