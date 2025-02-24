package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.entity.dto.VehicleDto;

import java.util.List;

public interface SpaceBusiness {

    List<VehicleDto> fetchUsersVehicles(long userId);
    void addModVehicle(VehicleDto vehicleDto);
    void suppVehicle(long vehicleId);
}
