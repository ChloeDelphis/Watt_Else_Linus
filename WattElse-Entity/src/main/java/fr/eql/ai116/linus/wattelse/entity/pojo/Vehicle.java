package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Vehicle implements Serializable {

    private Long vehicleId;
    private String vehicleName;
    private String licensePlate;
    private LocalDate additionDate;
    private LocalDate suppressionDate;

    private Socket socket;
    private Long userId;

    /// Constructeur vide
    public Vehicle() {
    }

    /// Constructeur surchargé
    public Vehicle(Long vehicleId,
                   String vehicleName,
                   String licensePlate,
                   LocalDate additionDate,
                   LocalDate suppressionDate) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.licensePlate = licensePlate;
        this.additionDate = additionDate;
        this.suppressionDate = suppressionDate;
    }

    public Vehicle(Long vehicleId,
                   String vehicleName,
                   String licensePlate,
                   LocalDate additionDate,
                   LocalDate suppressionDate,
                   Socket socket,
                   Long userId) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.licensePlate = licensePlate;
        this.additionDate = additionDate;
        this.suppressionDate = suppressionDate;
        this.socket = socket;
        this.userId = userId;
    }

    ///  Getters

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

    public LocalDate getAdditionDate() {
        return additionDate;
    }

    public LocalDate getSuppressionDate() {
        return suppressionDate;
    }

    public Long getUserId() {
        return userId;
    }

    ///  Setters

    public void setSuppressionDate(LocalDate suppressionDate) {
        this.suppressionDate = suppressionDate;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }


    public void setAdditionDate(LocalDate additionDate) {
        this.additionDate = additionDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    ///  Méthodes
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleId == vehicle.vehicleId
                && Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, vehicleName, licensePlate);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleName='" + vehicleName + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", additionDate=" + additionDate +
                ", suppressionDate=" + suppressionDate +
                ", socket=" + socket +
                ", userId=" + userId +
                '}';
    }
}
