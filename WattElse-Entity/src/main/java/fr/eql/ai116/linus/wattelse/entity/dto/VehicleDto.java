package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import java.io.Serializable;

public class VehicleDto implements Serializable {

    private Long vehicleId;
    private String vehicleName;
    private String licensePlate;
    private Socket socket;
    private Long userId;

    public VehicleDto(){}

    public VehicleDto(Long vehicleId, String vehicleName, String licensePlate, Socket socket, Long userId) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.licensePlate = licensePlate;
        this.socket = socket;
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Socket getSocket() {
        return socket;
    }

    public Long getUserId() {
        return userId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
