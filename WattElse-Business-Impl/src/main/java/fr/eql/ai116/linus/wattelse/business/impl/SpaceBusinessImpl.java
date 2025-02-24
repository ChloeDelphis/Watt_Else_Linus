package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.SpaceBusiness;
import fr.eql.ai116.linus.wattelse.dao.VehicleDao;
import fr.eql.ai116.linus.wattelse.entity.dto.VehicleDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

@Remote(SpaceBusiness.class)
@Stateless
public class SpaceBusinessImpl implements SpaceBusiness {

    @EJB
    VehicleDao vehicleDao;

    @Override
    public List<VehicleDto> fetchUsersVehicles(long userId) {
        List<Vehicle> vehicles = vehicleDao.fetchUsersVehicles(userId);
        List<VehicleDto> vehiclesDto = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehiclesDto.add( new VehicleDto(
                    vehicle.getVehicleId(),
                    vehicle.getVehicleName(),
                    vehicle.getLicensePlate(),
                    vehicle.getSocket(),
                    vehicle.getUserId()
            ));
        }
        return vehiclesDto;
    }

    @Override
    public void addModVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle(
                vehicleDto.getVehicleId(),
                vehicleDto.getVehicleName(),
                vehicleDto.getLicensePlate(),
                now(),
                null,
                vehicleDto.getSocket(),
                vehicleDto.getUserId());
        vehicleDao.addModVehicle(vehicle);
    }

    @Override
    public void suppVehicle(long vehicleId) {
        vehicleDao.suppVehicle(vehicleId);
    }
}
