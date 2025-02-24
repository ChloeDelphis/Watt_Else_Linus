package fr.eql.ai116.linus.wattelse.entity.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class StationUnavailabilityPeriod implements Serializable {
    private Long idStationUnavailabilityPeriod;
    private Station station;
    private LocalDate startingUnavailabilityPeriod;
    private LocalDate endingUnavailabilityPeriod;

    /// Constructeur vide
    public StationUnavailabilityPeriod() {
    }

    /// Constructeur surchargé
    public StationUnavailabilityPeriod(Long idStationUnavailabilityPeriod, Station station,
                                       LocalDate startingUnavailabilityPeriod, LocalDate endingUnavailabilityPeriod) {
        this.idStationUnavailabilityPeriod = idStationUnavailabilityPeriod;
        this.station = station;
        this.startingUnavailabilityPeriod = startingUnavailabilityPeriod;
        this.endingUnavailabilityPeriod = endingUnavailabilityPeriod;
    }

    /// Getters
    public Long getIdStationUnavailabilityPeriod() {
        return idStationUnavailabilityPeriod;
    }

    public Station getStation() {
        return station;
    }

    public LocalDate getStartingUnavailabilityPeriod() {
        return startingUnavailabilityPeriod;
    }

    public LocalDate getEndingUnavailabilityPeriod() {
        return endingUnavailabilityPeriod;
    }

    /// Setters
    public void setIdStationUnavailabilityPeriod(Long idStationUnavailabilityPeriod) {
        this.idStationUnavailabilityPeriod = idStationUnavailabilityPeriod;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setStartingUnavailabilityPeriod(LocalDate startingUnavailabilityPeriod) {
        this.startingUnavailabilityPeriod = startingUnavailabilityPeriod;
    }

    public void setEndingUnavailabilityPeriod(LocalDate endingUnavailabilityPeriod) {
        this.endingUnavailabilityPeriod = endingUnavailabilityPeriod;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "StationUnavailabilityPeriod{" +
                "idStationUnavailabilityPeriod=" + idStationUnavailabilityPeriod +
                ", station=" + station +
                ", startingUnavailabilityPeriod=" + startingUnavailabilityPeriod +
                ", endingUnavailabilityPeriod=" + endingUnavailabilityPeriod +
                '}';
    }
}
