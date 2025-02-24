package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> fetchUsersVehicles(long userId);
    void addModVehicle(Vehicle vehicle);
    void suppVehicle(long vehicleId);
    User getVehicleUser(long vehicleId);
}
