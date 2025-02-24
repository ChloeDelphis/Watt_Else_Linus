package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.SearchBusiness;
import fr.eql.ai116.linus.wattelse.dao.CalendarDao;
import fr.eql.ai116.linus.wattelse.dao.DataBaseEnumDao;
import fr.eql.ai116.linus.wattelse.dao.EvaluationDao;
import fr.eql.ai116.linus.wattelse.dao.StationDao;
import fr.eql.ai116.linus.wattelse.dao.TarificationDao;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.dao.VehicleDao;
import fr.eql.ai116.linus.wattelse.entity.CalendarDisponibility;
import fr.eql.ai116.linus.wattelse.entity.dto.StationDetailsDto;
import fr.eql.ai116.linus.wattelse.entity.dto.StationOutDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;
import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.pojo.Vehicle;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils.getCity;
import static fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils.getCityLatFromcityId;
import static fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils.getCityLongFromcityId;
import static fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils.getCityNameFromcityId;
import static fr.eql.ai116.linus.wattelse.business.impl.utils.AddressUtils.getZipCodeFromcityId;
import static fr.eql.ai116.linus.wattelse.business.impl.utils.DistanceUtils.calculateDistanceFromCityPointToStation;

@Remote(SearchBusiness.class)
@Stateless
public class SearchBusinessImpl implements SearchBusiness {

    private static final Logger logger = LogManager.getLogger();

    @EJB
    VehicleDao vehicleDao;

    @EJB
    DataBaseEnumDao dataBaseEnumDao;

    @EJB
    StationDao stationDao;

    @EJB
    TarificationDao tarificationDao;

    @EJB
    UserDao userDao;

    @EJB
    EvaluationDao evaluationDao;

    @EJB
    CalendarDao calendarDao;


    @Override
    public List<Socket> getAllSockets() {
        return dataBaseEnumDao.getSocketTypes();
    }

    @Override
    public List<Vehicle> getVehiclesByUser(long userId) {
        return vehicleDao.fetchUsersVehicles(userId);
    }

    // If onlyAvailableNow is true, we only retrieve the stations where isAvailableNow is true
    // If onlyAvailableNow is false, we retrieve all the stations found
    @Override
    public List<StationOutDto> getStationsBySocketAndDptAndAvailability(long idSocket, String nDpt, boolean onlyAvailableNow) {

        List<StationOutDto> stationsOutDto = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        List<Station> stations = stationDao.findStationsBySocketAndDtp(idSocket, nDpt);

        logger.info("SEARCH BUSINESS IMPL - getStationsBySocketAndDpt :" +
                "\r\nidSocket = " + idSocket +
                "\r\nnDpt = " + nDpt +
                "\r\nstations envoyées par le stationDao = " + stations);


        for (Station station : stations) {
            Long stationId = station.getStationId();

            // Vérifier la disponibilité de la station
            CalendarDisponibility calendarDisponibility = calendarDao.findStationDisponibility(now, stationId);
            logger.info("calendarDisponibility = " + calendarDisponibility);
            Boolean isAvailableNow = (calendarDisponibility == CalendarDisponibility.FREE);

            // Si onlyAvailableNow est true, on ignore les stations non disponibles
            if (onlyAvailableNow && !isAvailableNow) {
                continue; // Passe à l'itération suivante
            }

            // Infos tarif
            Tarification tarification = tarificationDao.getTarification(station.getTarificationId());
            TypeTarification typeTarification = tarification.getTypeTarification();
            Double tarificationCost = tarification.getCost();

            // Infos power
            Float powerValue = station.getPower().getValue();

            // Position
            String postalCode = station.getPostalCode();
            String city = getCity(station.getAddressDisplay());
            Double latitude = station.getLatitude();
            Double longitude = station.getLongitude();

            // Non calculé car pas de searching point
            Double distanceFromSearchingPoint = null;

            Double averageGradesForStation = evaluationDao.findAverageGradeByStationId(stationId);

            StationOutDto stationOutDto = new StationOutDto(
                    stationId, typeTarification, tarificationCost,
                    powerValue, postalCode, city, latitude, longitude,
                    distanceFromSearchingPoint, isAvailableNow, averageGradesForStation
            );

            stationsOutDto.add(stationOutDto);
        }


        return stationsOutDto;

    }

    @Override
    public List<StationOutDto> getCSbySocketCityRangeAvailability
            (long idSocket, String cityId, int range, String orderByCriterium, boolean onlyAvailableNow) {

        List<StationOutDto> stationsOutDto = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // Info extraction from cityId
        String cityZipCode = getZipCodeFromcityId(cityId);
        String cityName = getCityNameFromcityId(cityId);
        Double cityLat = getCityLatFromcityId(cityId);
        Double cityLong = getCityLongFromcityId(cityId);

        List<Station> stations = stationDao.findStationsBySocketCityZIPLatLongRange(
                idSocket, cityZipCode,
                cityLat, cityLong,
                range, orderByCriterium
        );

        logger.info("SEARCH BUSINESS IMPL - getCSbySocketCityRangeAvailability :" +
                "\r\nidSocket = " + idSocket +
                "\r\ncityName = " + cityName +
                "\r\nstations envoyées par le stationDao = " + stations);


        for (Station station : stations) {
            Long stationId = station.getStationId();

            // Vérifier la disponibilité de la station
            CalendarDisponibility calendarDisponibility = calendarDao.findStationDisponibility(now, stationId);
            logger.info("calendarDisponibility = " + calendarDisponibility);
            Boolean isAvailableNow = (calendarDisponibility == CalendarDisponibility.FREE);

            // Si onlyAvailableNow est true, on ignore les stations non disponibles
            if (onlyAvailableNow && !isAvailableNow) {
                continue; // Passe à l'itération suivante
            }

            // Infos tarif
            Tarification tarification = tarificationDao.getTarification(station.getTarificationId());
            TypeTarification typeTarification = tarification.getTypeTarification();
            Double tarificationCost = tarification.getCost();

            // Infos power
            Float powerValue = station.getPower().getValue();

            // Position
            String postalCode = station.getPostalCode();
            String city = getCity(station.getAddressDisplay());
            Double latitude = station.getLatitude();
            Double longitude = station.getLongitude();

            // Distance
            Double distanceFromSearchingPoint = calculateDistanceFromCityPointToStation(cityLat, cityLong , latitude, longitude);

            // Notes
            Double averageGradesForStation = evaluationDao.findAverageGradeByStationId(stationId);

            StationOutDto stationOutDto = new StationOutDto(
                    stationId, typeTarification, tarificationCost,
                    powerValue, postalCode, city, latitude, longitude,
                    distanceFromSearchingPoint, isAvailableNow, averageGradesForStation
            );

            stationsOutDto.add(stationOutDto);
        }


        return stationsOutDto;
    }

    @Override
    public StationDetailsDto getStationDetails(long stationId) {
        Station station = stationDao.getStationById(stationId);
        User user = userDao.getUserByStation(stationId);
        Tarification tarification = tarificationDao.getTarification(station.getTarificationId());

        return new StationDetailsDto(
                new User(
                        null,
                        null,
                        null,
                        user.getFirstName(),
                        user.getLastName(),
                        null,
                        user.getPhone(),
                        null,
                        user.getInscriptionDate(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                new Tarification(
                        null,
                        tarification.getTypeTarification(),
                        tarification.getCost(),
                        null,
                        null
                ),
                station.getSocket(),
                station.getPower(),
                station.getLatitude(),
                station.getLongitude(),
                station.getPostalCode(),
                station.getAddressDisplay()

        );
    }
}
