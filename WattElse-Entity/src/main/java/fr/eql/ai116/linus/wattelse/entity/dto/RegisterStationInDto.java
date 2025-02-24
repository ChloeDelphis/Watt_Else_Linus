package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import java.io.Serializable;

public class RegisterStationInDto implements Serializable {

    private String name;
    private Long userId;
    private Tarification tarification;
    private Socket socket;
    private Power power;
    private Double latitude;
    private Double longitude;
    private String addressDisplay;

    ///  Constructeur vide
    public RegisterStationInDto() {
    }

    /// Constructeur surchargé

    public RegisterStationInDto(String name, Long userId, Tarification tarification, Socket socket, Power power, Double latitude, Double longitude, String addressDisplay) {
        this.name = name;
        this.userId = userId;
        this.tarification = tarification;
        this.socket = socket;
        this.power = power;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressDisplay = addressDisplay;
    }

    /// Getters
    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public Tarification getTarification() {
        return tarification;
    }

    public Socket getSocket() {
        return socket;
    }

    public Power getPower() {
        return power;
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

    /// Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTarification(Tarification tarification) {
        this.tarification = tarification;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setPower(Power power) {
        this.power = power;
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

    /// Methodes
    @Override
    public String toString() {
        return "RegisterStationInDto{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", typeTarification=" + tarification +
                ", socket=" + socket +
                ", power=" + power +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", addressDisplay='" + addressDisplay + '\'' +
                '}';
    }
}