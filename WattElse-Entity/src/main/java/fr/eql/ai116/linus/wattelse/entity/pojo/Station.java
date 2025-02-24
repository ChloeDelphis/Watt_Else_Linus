package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;

import java.io.Serializable;
import java.time.LocalDate;

public class Station implements Serializable {

    private Long stationId;
    private String name;
    private Long userId;
    private Long tarificationId;
    private Socket socket;
    private Power power;
    private LocalDate additionDate;
    private LocalDate suppressionDate;
    private Double latitude;
    private Double longitude;
    private String postalCode;
    private String addressDisplay;
    private StationClosingMotive stationClosingMotive;

    ///  Constructeur vide
    public Station() {
    }

    ///  Constructeur surchargé

    public Station(Long stationId, String name, Long userId, Long tarificationId, Socket socket, Power power, LocalDate additionDate, LocalDate suppressionDate, Double latitude, Double longitude, String postalCode, String addressDisplay, StationClosingMotive stationClosingMotive) {
        this.stationId = stationId;
        this.name = name;
        this.userId = userId;
        this.tarificationId = tarificationId;
        this.socket = socket;
        this.power = power;
        this.additionDate = additionDate;
        this.suppressionDate = suppressionDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.postalCode = postalCode;
        this.addressDisplay = addressDisplay;
        this.stationClosingMotive = stationClosingMotive;
    }

    ///  Getters ///
    public Long getStationId() {
        return stationId;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTarificationId() {
        return tarificationId;
    }

    public Socket getSocket() {
        return socket;
    }

    public LocalDate getAdditionDate() {
        return additionDate;
    }

    public LocalDate getSuppressionDate() {
        return suppressionDate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAddressDisplay() {
        return addressDisplay;
    }

    public StationClosingMotive getStationClosingMotive() {
        return stationClosingMotive;
    }

    public Power getPower() {
        return power;
    }

    public String getPostalCode() {
        return postalCode;
    }

    /// Setters ///
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTarificationId(Long tarificationId) {
        this.tarificationId = tarificationId;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setAdditionDate(LocalDate additionDate) {
        this.additionDate = additionDate;
    }

    public void setSuppressionDate(LocalDate suppressionDate) {
        this.suppressionDate = suppressionDate;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setAddressDisplay(String addressDisplay) {
        this.addressDisplay = addressDisplay;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public void setStationClosingMotive(StationClosingMotive stationClosingMotive) {
        this.stationClosingMotive = stationClosingMotive;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    ///  Méthodes
    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", tarificationId=" + tarificationId +
                ", socket=" + socket +
                ", power=" + power +
                ", additionDate=" + additionDate +
                ", suppressionDate=" + suppressionDate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", postalCode=" + postalCode +
                ", addressDisplay='" + addressDisplay + '\'' +
                ", stationClosingMotive=" + stationClosingMotive +
                '}';
    }
}
