package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.business.exceptions.StationRegistrationException;
import fr.eql.ai116.linus.wattelse.entity.dto.MyChargingStationOutDto;
import fr.eql.ai116.linus.wattelse.entity.dto.RegisterStationInDto;

import java.util.List;

public interface StationBusiness {
    void registerNewStation(RegisterStationInDto dto_in) throws StationRegistrationException;
    List<MyChargingStationOutDto> fetchUserStations(long userId);
}
