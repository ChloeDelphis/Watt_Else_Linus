package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.StationDao;
import fr.eql.ai116.linus.wattelse.dao.exceptions.StationAlreadyExistException;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.dto.MyChargingStationOutDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;
import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Remote(StationDao.class)
@Stateless
public class StationDaoImpl implements StationDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_INSERT_STATION =
            "INSERT INTO obj_borne (nom, Id_Utilisateur, id_tarification, Id_Type_de_prise, Id_puissance,Date_ajout, latitude, longitude, address_display,code_postal) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String REQ_FIND_STATION_BY_ID =
            "SELECT * FROM obj_borne b " +
                    "LEFT JOIN gamme_type_de_prise tdp ON b.Id_Type_de_prise = tdp.Id_Type_de_prise " +
                    "LEFT JOIN gamme_puissance p ON b.id_puissance = p.id_puissance " +
                    "LEFT JOIN gamme_motif_de_retrait_borne mrb ON b.id_motif_retrait = mrb.id_motif_retrait " +
                    "WHERE b.Id_Station_de_recharge = ?";

    private static final String REQ_FIND_STATIONS_BY_USER =
            "SELECT b.nom ,b.address_display, tdp.*,p.*,t.*,tdt.*  FROM obj_borne b " +
                    "JOIN gamme_type_de_prise tdp ON tdp.Id_Type_de_prise = b.Id_Type_de_prise " +
                    "JOIN gamme_puissance p ON p.id_puissance = b.id_puissance " +
                    "JOIN obj_tarification_borne t ON t.id_tarification = b.id_tarification " +
                    "JOIN gamme_type_de_tarification tdt ON tdt.id_type_tarification = t.id_type_tarification " +
                    "WHERE b.Id_Utilisateur = ?";

    @Override
    public void registerNewStation(Station station, long socketId, long powerId) throws StationAlreadyExistException {
        System.out.println("Présent en DAO");
        System.out.println(station);
        System.out.println(socketId);
        System.out.println(powerId);
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            System.out.println("Connexion OK");
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_STATION, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, station.getName()); // `nom`
                statement.setLong(2, station.getUserId()); // `Id_Utilisateur`
                statement.setLong(3, station.getTarificationId()); // `id_tarification`
                statement.setLong(4, socketId); // `Id_Type_de_prise`
                statement.setLong(5, powerId); // `puissance`
                statement.setDate(6, DateUtils.convert(station.getAdditionDate())); // `Date_ajout`
                statement.setDouble(7, station.getLatitude()); // `latitude`
                statement.setDouble(8, station.getLongitude()); // `longitude`
                statement.setString(9, station.getAddressDisplay()); // `address_display`
                statement.setString(10, station.getPostalCode()); // `code_postal`

                int affectedRows = statement.executeUpdate();
                System.out.println(statement);
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        station.setStationId(id);
                    }
                }
                connection.commit();
                logger.info("{} a été inséré en base de données avec l'id {}", station.getName(), station.getStationId());
            } catch (SQLIntegrityConstraintViolationException e) {
                connection.rollback();
                logger.info("Le nom {} est déjà associé à l'utilisateur d'id {} dans la BDD, insertion impossible", station.getName(), station.getUserId());
                throw new StationAlreadyExistException("le nom est déjà associé à cet utilisateur");
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de {}", station.getName(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    @Override
    public Station getStationById(long idStation) {
        Station station = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_STATION_BY_ID);
            statement.setLong(1, idStation);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                station = buildStation(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des stations en base de données", e);
        }
        return station;
    }

    private static final String REQ_FIND_STATIONS_BY_SOCKET_AND_DPT_ORDER_BY_POWER_DESC =
            "SELECT * FROM obj_borne b " +
                    "JOIN gamme_type_de_prise tdp ON b.Id_Type_de_prise = tdp.Id_Type_de_prise " +
                    "JOIN gamme_puissance p ON b.id_puissance = p.id_puissance " +
                    "LEFT JOIN gamme_motif_de_retrait_borne mrb ON b.id_motif_retrait = mrb.id_motif_retrait " +
                    "WHERE b.Id_Type_de_prise = ? " +
                    "AND b.code_postal LIKE ? " +
                    "ORDER BY p.puissance DESC ";


    @Override
    public List<Station> findStationsBySocketAndDtp(long idSocket, String nDpt) {

        List<Station> stations = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_STATIONS_BY_SOCKET_AND_DPT_ORDER_BY_POWER_DESC);
            statement.setLong(1, idSocket);
            statement.setString(2, nDpt + "%");
            ResultSet resultSet = statement.executeQuery();

            logger.info("STATION DAO IMPL - findStationsBySocketAndDtp" +
                    "\r\nresultSet = " + resultSet);

            while (resultSet.next()) {
                stations.add(buildStation(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des stations en base de données", e);
        }
        return stations;
    }


    private static final String REQ_STATIONS_BY_SOCKET_CITY_DISTANCE_ORDERBY =
            "SELECT DISTINCT *, \n" +
                    "(6371 * acos(cos(radians(?)) * cos(radians(b.latitude)) * cos(radians(b.longitude) \n" +
                    "- radians(?)) + sin(radians(?)) * sin(radians(b.latitude)))) AS distance \n" +
                    "FROM obj_borne AS b \n" +
                    "JOIN gamme_type_de_prise tdp ON b.Id_Type_de_prise = tdp.Id_Type_de_prise " +
                    "JOIN gamme_puissance p ON b.id_puissance = p.id_puissance " +
                    "LEFT JOIN gamme_motif_de_retrait_borne mrb ON b.id_motif_retrait = mrb.id_motif_retrait " +
                    "WHERE \n" +
                    "(b.Id_Type_de_prise = ? AND b.code_postal = ?) \n" + // Sélection du code postal
                    "OR \n" +
                    "(\n" +
                    "    b.latitude BETWEEN ? AND ? \n" + // Plage de latitude
                    "    AND b.longitude BETWEEN ? AND ? \n" + // Plage de longitude
                    "    AND (6371 * acos(cos(radians(?)) * cos(radians(b.latitude)) * cos(radians(b.longitude) \n" +
                    "    - radians(?)) + sin(radians(?)) * sin(radians(b.latitude)))) < ? \n" + // Distance
                    ")";

    public List<Station> findStationsBySocketCityZIPLatLongRange(
            long idSocket, String cityZip, double cityLat,
            double cityLong, int range, String orderByCriterium) {

        logger.info("STATION DAO IMPL - findStationsBySocketCityZIPLatLongRange");
        logger.info("idSocket = " + idSocket
                + "\r\ncityZip = " + cityZip
                + "\r\ncityLat = " + cityLat
                + "\r\ncityLong = " + cityLong
                + "\r\nrange = " + range
                + "\r\norderByCriterium = " + orderByCriterium);

        List<Station> stations = new ArrayList<>();

        // Calcul de la plage de latitude et longitude
        double latRange = range / 111.0; // approx. 111 km par degré de latitude
        double longRange = range / (111.0 * Math.cos(Math.toRadians(cityLat))); // ajustement pour la longitude

        // Gestion du tri
        String orderBy = "b.id_puissance DESC";
        if ("orderByDistanceAsc".equals(orderByCriterium)) {
            orderBy = "distance ASC";
        }

        // Construire la requête complète avec le tri dynamique
        String sql = REQ_STATIONS_BY_SOCKET_CITY_DISTANCE_ORDERBY + " ORDER BY " + orderBy;

        logger.info("requête sql = " + sql);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Définir les paramètres de la requête
            stmt.setDouble(1, cityLat);
            stmt.setDouble(2, cityLong);
            stmt.setDouble(3, cityLat);
            stmt.setLong(4, idSocket);
            stmt.setString(5, cityZip);

            // Plage de latitude et longitude
            stmt.setDouble(6, cityLat - latRange);
            stmt.setDouble(7, cityLat + latRange);
            stmt.setDouble(8, cityLong - longRange);
            stmt.setDouble(9, cityLong + longRange);

            // Paramètres pour le calcul de distance
            stmt.setDouble(10, cityLat);
            stmt.setDouble(11, cityLong);
            stmt.setDouble(12, cityLat);
            stmt.setInt(13, range);

            // Exécuter la requête et traiter les résultats
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Station station = buildStation(rs);
                    stations.add(station);
                }
                return stations;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return stations;
        }
    }



    @Override
    public List<MyChargingStationOutDto> fetchUserMyChargingStations(long userId) {
        List<MyChargingStationOutDto> stations = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_STATIONS_BY_USER);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                stations.add(new MyChargingStationOutDto(
                        resultSet.getString("nom"),
                        new Socket(
                                resultSet.getLong("Id_Type_de_prise"),
                                resultSet.getString("Libelle")
                                ),
                        new Power(
                                resultSet.getLong("id_puissance"),
                                resultSet.getFloat("puissance")
                        ),
                        new Tarification(
                                resultSet.getLong("id_tarification"),
                                new TypeTarification(
                                        resultSet.getLong("id_type_tarification"),
                                        resultSet.getString("libelle_type_tarification"),
                                        resultSet.getString("abreviation")
                                ),
                                resultSet.getDouble("prix"),
                                DateUtils.convert(resultSet.getDate("date_de_demarrage_tarif")),
                                DateUtils.convert(resultSet.getDate("date_de_fin_tarif"))
                        ),
                        resultSet.getString("address_display")
                        ));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des stations en base de données", e);
        }
        return stations;
    }

    private Station buildStation(ResultSet resultSet) throws SQLException {
        return new Station(
                resultSet.getLong("Id_Station_de_recharge"),
                resultSet.getString("nom"),
                resultSet.getLong("Id_Utilisateur"),
                resultSet.getLong("id_tarification"),

                new Socket(resultSet.getLong("Id_Type_de_prise"),
                        resultSet.getString("Libelle")),

                new Power(resultSet.getLong("id_puissance"),
                        resultSet.getFloat("puissance")),

                DateUtils.convert(resultSet.getDate("Date_ajout")),
                DateUtils.convert(resultSet.getDate("Date_retrait")),
                resultSet.getDouble("latitude"),
                resultSet.getDouble("longitude"),
                resultSet.getString("code_postal"),
                resultSet.getString("address_display"),

                new StationClosingMotive(resultSet.getLong("id_motif_retrait"),
                        resultSet.getString("motif_retrait")));
    }


}
