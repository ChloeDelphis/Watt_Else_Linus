package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.RangeDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.entity.range.AccountClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.BanMotive;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.RatingType;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationAnormalEnding;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationHour;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationMinute;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;
import fr.eql.ai116.linus.wattelse.entity.range.StationService;
import fr.eql.ai116.linus.wattelse.entity.range.TypeTarification;
import fr.eql.ai116.linus.wattelse.entity.range.WeekDay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Remote(RangeDao.class)
@Stateless
public class RangeDaoImpl implements RangeDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_FIND_SOCKETS =
            "SELECT * FROM gamme_type_de_prise ORDER BY gamme_type_de_prise.Libelle ";
    private static final String REQ_FIND_POWER =
            "SELECT * FROM gamme_puissance ORDER BY gamme_puissance.puissance ";
    private static final String REQ_FIND_TARIFICATION =
            "SELECT * FROM gamme_type_de_tarification ORDER BY gamme_type_de_tarification.abreviation ";
    private static final String REQ_FIND_RESERVATION_HOUR =
            "SELECT * FROM gamme_heure_reservation ORDER BY gamme_heure_reservation.valeur_heure";
    private static final String REQ_FIND_RESERVATION_MINUTES =
            "SELECT * FROM gamme_minutes_reservation ORDER BY gamme_minutes_reservation.gamme_minute";
    private static final String REQ_FIND_WEEK_DAYS =
            "SELECT * FROM gamme_jour_de_semaine ORDER BY gamme_jour_de_semaine.id_jour";
    private static final String REQ_FIND_BAN_MOTIVE =
            "SELECT * FROM gamme_motif_banissement ORDER BY gamme_motif_banissement.id";
    private static final String REQ_FIND_STATION_CM =
            "SELECT * FROM gamme_motif_de_retrait ORDER BY gamme_motif_de_retrait.id_motif_retrait";
    private static final String REQ_FIND_ACCOUNT_CM =
            "SELECT * FROM gamme_motif_fermeture ORDER BY gamme_motif_fermeture.Id_Motif_fermeture";
    private static final String REQ_FIND_RAE =
            "SELECT * FROM gamme_motif_fin_anormale_reservation ORDER BY gamme_motif_fin_anormale_reservation.Id_Fin_anormale";
    private static final String REQ_FIND_RATING_TYPE =
            "SELECT * FROM gamme_type_d_avis ORDER BY gamme_type_d_avis.Id_type_avis";
    private static final String REQ_FIND_STATION_SERVICE =
            "SELECT * FROM gamme_type_service ORDER BY gamme_type_service.Id_service";


    private final DataSource dataSource = new WattElseDataSource();

    @Override
    public List<Socket> fetchSockets() {
        List<Socket> sockets = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_SOCKETS);
            while (resultSet.next()) {
                Long socketId = resultSet.getLong("Id_Type_de_prise");
                String socketLabel = resultSet.getString("Libelle");
                sockets.add(new Socket(socketId, socketLabel));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types de prises en bdd", e);
        }
        return sockets;
    }

    @Override
    public List<Power> fetchPower() {
        List<Power> power = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_POWER);
            while (resultSet.next()) {
                Long powerId = resultSet.getLong("id_puissance");
                Float value = resultSet.getFloat("puissance");
                power.add(new Power(powerId, value));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types de puissances en bdd", e);
        }
        return power;
    }

    @Override
    public List<TypeTarification> fetchTarification() {
        List<TypeTarification> tarifications = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_TARIFICATION);
            while (resultSet.next()) {
                Long typeTarificationId = resultSet.getLong("id_type_tarification");
                String labelTypeTarification = resultSet.getString("libelle_type_tarification");
                String abbreviationTypeTarification = resultSet.getString("abreviation");
                tarifications.add(new TypeTarification(typeTarificationId, labelTypeTarification, abbreviationTypeTarification));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des tarifications en bdd", e);
        }
        return tarifications;
    }

    @Override
    public List<ReservationHour> fetchReservationHour() {
        List<ReservationHour> reservationHours = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_RESERVATION_HOUR);
            while (resultSet.next()) {
                Long idHour = resultSet.getLong("id_he");
                Integer hour = resultSet.getInt("valeur_heure");
                reservationHours.add(new ReservationHour(idHour, hour));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des heures de réservations en bdd", e);
        }
        return reservationHours;
    }

    @Override
    public List<ReservationMinute> fetchReservationMinute() {
        List<ReservationMinute> reservationMinutes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_RESERVATION_MINUTES);
            while (resultSet.next()) {
                Long idHour = resultSet.getLong("id_min");
                Integer minutes = resultSet.getInt("gamme_minute");
                reservationMinutes.add(new ReservationMinute(idHour, minutes));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des minutes de réservations en bdd", e);
        }
        return reservationMinutes;
    }

    @Override
    public List<WeekDay> fetchWeekDays() {
        List<WeekDay> weekDay = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_WEEK_DAYS);
            while (resultSet.next()) {
                Long idWeekDay = resultSet.getLong("id_jour");
                String labelWeekDay = resultSet.getString("libelle");
                weekDay.add(new WeekDay(idWeekDay,labelWeekDay));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des jours de la semaine en bdd", e);
        }
        return weekDay;
    }

    @Override
    public List<BanMotive> fetchBanMotive() {
        List<BanMotive> banMotives = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_BAN_MOTIVE);
            while (resultSet.next()) {
                Long idBan = resultSet.getLong("id");
                String motive = resultSet.getString("raison");
                banMotives.add(new BanMotive(idBan,motive));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des motifs de banissement en bdd", e);
        }
        return banMotives;
    }

    @Override
    public List<StationClosingMotive> fetchStationClosingMotive() {
        List<StationClosingMotive> stationClosingMotives = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_STATION_CM);
            while (resultSet.next()) {
                Long idStationClosingMotive = resultSet.getLong("id_motif_retrait");
                String label = resultSet.getString("motif_retrait");
                stationClosingMotives.add(new StationClosingMotive(idStationClosingMotive,label));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des motifs de fermeture de Station de recharge en bdd", e);
        }
        return stationClosingMotives;
    }

    @Override
    public List<AccountClosingMotive> fetchAccountClosingMotive() {
        List<AccountClosingMotive> accountClosingMotives = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_ACCOUNT_CM);
            while (resultSet.next()) {
                Long idMotifFermeture = resultSet.getLong("Id_Motif_fermeture");
                String libelle = resultSet.getString("libelle");
                accountClosingMotives.add(new AccountClosingMotive(idMotifFermeture,libelle));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des motifs de fermeture de compte en bdd", e);
        }
        return accountClosingMotives;
    }

    @Override
    public List<ReservationAnormalEnding> fetchReservationAnormalEnding() {
        List<ReservationAnormalEnding> reservationAnormalEndings = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_RAE);
            while (resultSet.next()) {
                Long anormalEndingId = resultSet.getLong("Id_Fin_anormale");
                String anormalEndingLabel = resultSet.getString("Libelle");
                reservationAnormalEndings.add(new ReservationAnormalEnding(anormalEndingId,anormalEndingLabel));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation de la fin anormale de recharge en bdd", e);
        }
        return reservationAnormalEndings;
    }

    @Override
    public List<RatingType> fetchRatingType() {
        List<RatingType> ratingTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_RATING_TYPE);
            while (resultSet.next()) {
                Long idRatingType = resultSet.getLong("Id_type_avis");
                String labelRatingType = resultSet.getString("Libelle_type_avis");
                ratingTypes.add(new RatingType(idRatingType,labelRatingType));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types d'avis en bdd", e);
        }
        return ratingTypes;
    }

    @Override
    public List<StationService> fetchStationService() {
        List<StationService> stationServices = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_STATION_SERVICE);
            while (resultSet.next()) {
                Long idStationService = resultSet.getLong("Id_service");
                String labelStationService = resultSet.getString("Libelle_service");
                stationServices.add(new StationService(idStationService,labelStationService));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des services en bdd", e);
        }
        return stationServices;
    }

    ;


}
