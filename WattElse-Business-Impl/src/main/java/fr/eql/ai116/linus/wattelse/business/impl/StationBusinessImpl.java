package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.StationBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.StationRegistrationException;
import fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils;
import fr.eql.ai116.linus.wattelse.dao.StationDao;
import fr.eql.ai116.linus.wattelse.dao.TarificationDao;
import fr.eql.ai116.linus.wattelse.dao.exceptions.StationAlreadyExistException;
import fr.eql.ai116.linus.wattelse.entity.dto.MyChargingStationOutDto;
import fr.eql.ai116.linus.wattelse.entity.dto.RegisterStationInDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;
import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Remote(StationBusiness.class)
@Stateless
public class StationBusinessImpl implements StationBusiness {

    @EJB
    StationDao stationDao;

    @EJB
    TarificationDao tarificationDao;

    @Override
    public void registerNewStation(RegisterStationInDto dto_in) throws StationRegistrationException {
        System.out.println("coucou : " + dto_in);
        Tarification tarification = tarificationDao.insertTarification(
                new Tarification(
                        dto_in.getTarification().getTarificationId(),
                        dto_in.getTarification().getTypeTarification(),
                        dto_in.getTarification().getCost(),
                        LocalDate.now(),
                        dto_in.getTarification().getDateTarificationEnd()
                )
        );
        Station station = new Station(
                -1L,
                dto_in.getName(),
                dto_in.getUserId(),
                tarification.getTarificationId(),
                dto_in.getSocket(),
                dto_in.getPower(),
                LocalDate.now(),
                null,
                dto_in.getLatitude(),
                dto_in.getLongitude(),
                AddressUtils.getPostalCode(dto_in.getAddressDisplay()),
                dto_in.getAddressDisplay(),
                null
                );

        System.out.println(station);

        Long socketId = dto_in.getSocket().getSocketId();
        Long powerId = dto_in.getPower().getPowerId();

        try {
            stationDao.registerNewStation(station, socketId,powerId);
        } catch (StationAlreadyExistException e) {
            throw new StationRegistrationException(e.getMessage());
        }
    }

    @Override
    public List<MyChargingStationOutDto> fetchUserStations(long userId) {
        return stationDao.fetchUserMyChargingStations(userId);
    }
}
