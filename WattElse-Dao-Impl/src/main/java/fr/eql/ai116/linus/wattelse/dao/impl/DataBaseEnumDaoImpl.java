package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.DataBaseEnumDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Remote(DataBaseEnumDao.class)
@Stateless
public class DataBaseEnumDaoImpl implements DataBaseEnumDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static  final String REQ_FETCH_WEEK_DAYS = "SELECT * FROM gamme_jour_de_semaine;";
    private static  final String REQ_FETCH_RESERVATION_HOURS = "SELECT * FROM gamme_heure_reservation;";
    private static  final String REQ_FETCH_RESERVATION_MINUTES = "SELECT * FROM gamme_minutes_reservation;";
    private static  final String REQ_FETCH_BAN_REASONS = "SELECT * FROM gamme_motif_banissement;";
    private static  final String REQ_FETCH_STATION_CLOSING_REASONS = "SELECT * FROM gamme_motif_de_retrait_borne;";
    private static  final String REQ_FETCH_ACCOUNT_CLOSING_REASONS = "SELECT * FROM gamme_motif_fermeture_compte;";
    private static  final String REQ_FETCH_ANORMAL_RESERVATION_ENDING_TYPES = "SELECT * FROM gamme_motif_fin_anormale_reservation;";
    private static  final String REQ_FETCH_SOCKET_TYPES = "SELECT * FROM gamme_type_de_prise;";
    private static  final String REQ_FETCH_TARIFICATION_TYPES = "SELECT * FROM gamme_type_de_tarification;";
    private static  final String REQ_FETCH_EVALUATION_TYPES = "SELECT * FROM gamme_type_d_avis;";
    private static  final String REQ_FETCH_SERVICE_TYPES = "SELECT * FROM gamme_type_service;";

    @Override
    public List<String[]> getWeekDays() {
        List<String[]> weekdays = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_WEEK_DAYS);
            while(resultSet.next()) {
                weekdays.add(new String[] {resultSet.getString("id_jour"),resultSet.getString("libelle")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des jour de la semaine en base de données", e);
        }
        return weekdays;
    }

    @Override
    public List<Integer> getReservationHours() {
        List<Integer> reservationHours = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_RESERVATION_HOURS);
            while(resultSet.next()) {
                reservationHours.add(resultSet.getInt("id_he"),resultSet.getInt("valeur_heure"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des heures de réservation en base de données", e);
        }
        return reservationHours;
    }

    @Override
    public List<Integer> getReservationMinutes() {
        List<Integer> reservationMinutes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_RESERVATION_MINUTES);
            while(resultSet.next()) {
                reservationMinutes.add(resultSet.getInt("id_min"),resultSet.getInt("gamme_minute"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des jour de la semaine en base de données", e);
        }
        return reservationMinutes;
    }

    @Override
    public List<String[]> getBanReasons() {
        List<String[]> banReasons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_BAN_REASONS);
            while(resultSet.next()) {
                banReasons.add(new String[] {resultSet.getString("id"),resultSet.getString("raison")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des motifs de bannisement en base de données", e);
        }
        return banReasons;
    }

    @Override
    public List<String[]> getStationClosingReasons() {
        List<String[]> stationClosingReasons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_STATION_CLOSING_REASONS);
            while(resultSet.next()) {
                stationClosingReasons.add(new String[] {resultSet.getString("id_motif_retrait"),resultSet.getString("motif_retrait")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des motif de retrait d'une borne en base de données", e);
        }
        return stationClosingReasons;
    }

    @Override
    public List<String[]> getAccountClosingReasons() {
        List<String[]> accountClosingReasons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_ACCOUNT_CLOSING_REASONS);
            while(resultSet.next()) {
                accountClosingReasons.add(new String[] {resultSet.getString("Id_Motif_fermeture"),resultSet.getString("Libelle")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des raison de fermeture de compte en base de données", e);
        }
        return accountClosingReasons;
    }

    @Override
    public List<String[]> getAnormalReservationEndingTypes() {
        List<String[]> anormalReservationEndingTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_ANORMAL_RESERVATION_ENDING_TYPES);
            while(resultSet.next()) {
                anormalReservationEndingTypes.add(new String[] {resultSet.getString("Id_Fin_anormale"),resultSet.getString("Libelle")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types de fin anormale de réservation en base de données", e);
        }
        return anormalReservationEndingTypes;
    }

    @Override
    public List<Socket> getSocketTypes() {
        List<Socket> socketTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_SOCKET_TYPES);
            while(resultSet.next()) {
                socketTypes.add(new Socket(resultSet.getLong("Id_Type_de_prise"),resultSet.getString("Libelle")));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types de prise en base de données", e);
        }
        return socketTypes;
    }

    @Override
    public List<String[]> getTarificationTypes() {
        List<String[]> tarificationTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_TARIFICATION_TYPES);
            while(resultSet.next()) {
                tarificationTypes.add(new String[] {resultSet.getString("id_type_tarification"),resultSet.getString("libelle_type_tarification"),resultSet.getString("abreviation")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types de tarifiaction en base de données", e);
        }
        return tarificationTypes;
    }

    @Override
    public List<String[]> getEvaluationTypes() {
        List<String[]> evaluationTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_EVALUATION_TYPES);
            while(resultSet.next()) {
                evaluationTypes.add(new String[] {resultSet.getString("Id_type_avis"),resultSet.getString("Libelle_type_avis")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types d'évaluation en base de données", e);
        }
        return evaluationTypes;
    }

    @Override
    public List<String[]> getServicesTypes() {
        List<String[]> servicesTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FETCH_SERVICE_TYPES);
            while(resultSet.next()) {
                servicesTypes.add(new String[] {resultSet.getString("Id_service"),resultSet.getString("Libelle_service")});
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des types de services alentour en base de données", e);
        }
        return servicesTypes;
    }
}
