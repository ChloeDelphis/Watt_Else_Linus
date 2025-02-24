package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;

import java.io.Serializable;
import java.time.LocalDate;

public class StationDetailsDto implements Serializable {
    private User owner;
    private Tarification tarification;
    private Socket socket;
    private Power power;
    private Double latitude;
    private Double longitude;
    private String postalCode;
    private String addressDisplay;

    public StationDetailsDto(User owner, Tarification tarification, Socket socket, Power power, Double latitude, Double longitude, String postalCode, String addressDisplay) {
        this.owner = owner;
        this.tarification = tarification;
        this.socket = socket;
        this.power = power;
        this.latitude = latitude;
        this.longitude = longitude;
        this.postalCode = postalCode;
        this.addressDisplay = addressDisplay;
    }

    public StationDetailsDto() {
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddressDisplay(String addressDisplay) {
        this.addressDisplay = addressDisplay;
    }

    public User getOwner() {
        return owner;
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

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddressDisplay() {
        return addressDisplay;
    }

    @Override
    public String toString() {
        return "StationDetailsDto{" +
                "owner=" + owner +
                ", tarification=" + tarification +
                ", socket=" + socket +
                ", power=" + power +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", postalCode='" + postalCode + '\'' +
                ", addressDisplay='" + addressDisplay + '\'' +
                '}';
    }
}
