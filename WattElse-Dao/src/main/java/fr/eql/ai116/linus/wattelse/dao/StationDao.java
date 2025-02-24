package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.dao.exceptions.StationAlreadyExistException;
import fr.eql.ai116.linus.wattelse.entity.dto.MyChargingStationOutDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;

import java.util.List;

public interface StationDao {
    void registerNewStation(Station station, long socketId, long powerId) throws StationAlreadyExistException;

    Station getStationById(long idStation);

    List<Station> findStationsBySocketAndDtp(long idSocket, String nDpt);

    List<Station> findStationsBySocketCityZIPLatLongRange(long idSocket, String cityZip,
                                                          double cityLat, double cityLong,
                                                          int range, String orderByCriterium);

    List<MyChargingStationOutDto> fetchUserMyChargingStations(long userId);
}
