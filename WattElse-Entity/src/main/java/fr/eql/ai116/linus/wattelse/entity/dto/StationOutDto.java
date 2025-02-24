package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;

import java.io.Serializable;

public class StationOutDto implements Serializable {

    // Infos station
    private Long stationId;
    private TypeTarification typeTarification;
    private Double tarificationCost;
    private Float powerValue;

    // Position
    private String postalCode;
    public String city;
    private Double latitude;
    private Double longitude;
    private Double distanceFromSearchingPoint;

    // Disponibilité
    private Boolean isAvailableNow;

    // Notes
    private Double averageGradeForStation;

    ///  Constructeur vide
    public StationOutDto() {
    }

    public StationOutDto(Long stationId, TypeTarification typeTarification,
                         Double tarificationCost, Float powerValue,
                         String postalCode, String city, Double latitude,
                         Double longitude, Double distanceFromSearchingPoint,
                         Boolean isAvailableNow, Double averageGradeForStation) {
        this.stationId = stationId;
        this.typeTarification = typeTarification;
        this.tarificationCost = tarificationCost;
        this.powerValue = powerValue;
        this.postalCode = postalCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceFromSearchingPoint = distanceFromSearchingPoint;
        this.isAvailableNow = isAvailableNow;
        this.averageGradeForStation = averageGradeForStation;
    }

    /// Getters
    public Long getStationId() {
        return stationId;
    }

    public TypeTarification getTypeTarification() {
        return typeTarification;
    }

    public Double getTarificationCost() {
        return tarificationCost;
    }

    public Float getPowerValue() {
        return powerValue;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Boolean getAvailableNow() {
        return isAvailableNow;
    }

    public Double getAverageGradeForStation() {
        return averageGradeForStation;
    }

    public Double getDistanceFromSearchingPoint() {
        return distanceFromSearchingPoint;
    }


    /// Setters

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public void setTypeTarification(TypeTarification typeTarification) {
        this.typeTarification = typeTarification;
    }

    public void setTarificationCost(Double tarificationCost) {
        this.tarificationCost = tarificationCost;
    }

    public void setPowerValue(Float powerValue) {
        this.powerValue = powerValue;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setAvailableNow(Boolean availableNow) {
        isAvailableNow = availableNow;
    }

    public void setAverageGradeForStation(Double averageGradesForStation) {
        this.averageGradeForStation = averageGradesForStation;
    }

    public void setDistanceFromSearchingPoint(Double distanceFromSearchingPoint) {
        this.distanceFromSearchingPoint = distanceFromSearchingPoint;
    }
}
