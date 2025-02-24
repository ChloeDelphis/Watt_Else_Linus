package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.StationService;

import java.io.Serializable;
import java.time.LocalDate;

public class Service implements Serializable {
    private Long idService;
    private Station station;
    private StationService stationService;
    private LocalDate serviceAdditionDate;
    private LocalDate serviceRemoveDate;

    /// Constructeur vide
    public Service() {
    }

    /// Constructeur surchargé
    public Service(Long idService, Station station, StationService stationService, LocalDate serviceAdditionDate,
                   LocalDate serviceRemoveDate) {
        this.idService = idService;
        this.station = station;
        this.stationService = stationService;
        this.serviceAdditionDate = serviceAdditionDate;
        this.serviceRemoveDate = serviceRemoveDate;
    }

    /// Getters
    public Long getIdService() {
        return idService;
    }

    public Station getStation() {
        return station;
    }

    public StationService getStationService() {
        return stationService;
    }

    public LocalDate getServiceAdditionDate() {
        return serviceAdditionDate;
    }

    public LocalDate getServiceRemoveDate() {
        return serviceRemoveDate;
    }

    /// Setters
    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public void setServiceAdditionDate(LocalDate serviceAdditionDate) {
        this.serviceAdditionDate = serviceAdditionDate;
    }

    public void setServiceRemoveDate(LocalDate serviceRemoveDate) {
        this.serviceRemoveDate = serviceRemoveDate;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", station=" + station +
                ", stationService=" + stationService +
                ", serviceAdditionDate=" + serviceAdditionDate +
                ", serviceRemoveDate=" + serviceRemoveDate +
                '}';
    }
}
