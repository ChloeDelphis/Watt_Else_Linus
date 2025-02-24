package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class StationService implements Serializable {

    private Long idStationService;
    private String labelStationService;

    /// Constructeur vide
    public StationService() {
    }

    /// Constructeur surchargé
    public StationService(Long idStationService, String labelStationService) {
        this.idStationService = idStationService;
        this.labelStationService = labelStationService;
    }

    /// Getters
    public Long getIdStationService() {
        return idStationService;
    }

    public String getLabelStationService() {
        return labelStationService;
    }

    /// Setters
    public void setIdStationService(Long idStationService) {
        this.idStationService = idStationService;
    }

    public void setLabelStationService(String labelStationService) {
        this.labelStationService = labelStationService;
    }
}
