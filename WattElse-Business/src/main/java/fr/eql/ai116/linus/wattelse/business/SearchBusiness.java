package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.entity.dto.StationDetailsDto;
import fr.eql.ai116.linus.wattelse.entity.dto.StationOutDto;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;

import java.util.List;

public interface SearchBusiness {

    List<Socket> getAllSockets();

    List<Vehicle> getVehiclesByUser(long userId);

    List<StationOutDto> getStationsBySocketAndDptAndAvailability(long idSocket, String nDpt, boolean onlyAvailableNow);

    List<StationOutDto> getCSbySocketCityRangeAvailability(
            long idSocket, String cityId, int range,
            String orderByCriterium, boolean onlyAvailableNow);

    StationDetailsDto getStationDetails(long stationId);
}
