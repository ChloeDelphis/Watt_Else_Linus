package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import java.io.Serializable;

public class MyChargingStationOutDto implements Serializable {
    private String name;
    private Socket socket;
    private Power power;
    private Tarification tarification;
    private String display_address;

    public MyChargingStationOutDto(String name, Socket socket, Power power, Tarification tarification, String display_address) {
        this.name = name;
        this.socket = socket;
        this.power = power;
        this.tarification = tarification;
        this.display_address = display_address;
    }

    public MyChargingStationOutDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Tarification getTarification() {
        return tarification;
    }

    public void setTarification(Tarification tarification) {
        this.tarification = tarification;
    }

    public String getDisplay_address() {
        return display_address;
    }

    public void setDisplay_address(String display_address) {
        this.display_address = display_address;
    }
}
