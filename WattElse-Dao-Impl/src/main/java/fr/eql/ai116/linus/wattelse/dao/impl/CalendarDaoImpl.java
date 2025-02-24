package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.CalendarDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.CalendarDisponibility;
import fr.eql.ai116.linus.wattelse.entity.pojo.Calendar;
import fr.eql.ai116.linus.wattelse.entity.pojo.HourSlots;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Remote(CalendarDao.class)
@Stateless
public class CalendarDaoImpl implements CalendarDao {

    private static final Logger logger = LogManager.getLogger();

    private final DataSource dataSource = new WattElseDataSource();

    private final String REQ_FETCH_OPENING_HOURS =
    "SELECT hob.id_horaire_ouverture id,hob.Id_Station_de_recharge stationId, ghr_s.valeur_heure startHour, gmr_s.gamme_minute startMinute,ghr_f.valeur_heure endHour, gmr_f.gamme_minute endMinute, gjs.* FROM obj_horaire_ouverture_borne hob " +
    "JOIN gamme_heure_reservation ghr_s ON hob.id_he_deb = ghr_s.id_he " +
    "JOIN gamme_minutes_reservation gmr_s ON hob.id_min_deb = gmr_s.id_min " +
    "JOIN gamme_heure_reservation ghr_f ON hob.id_he_fin = ghr_f.id_he " +
    "JOIN gamme_minutes_reservation gmr_f ON hob.id_min_fin = gmr_f.id_min " +
    "JOIN gamme_jour_de_semaine gjs ON hob.id_jour = gjs.id_jour " +
    "WHERE Id_Station_de_recharge = 1";

    private static  final String REQ_FETCH_WEEKLY_RESERVATIONS_BY_STATION =
            "SELECT mrd.gamme_minute startMinute,hrd.valeur_heure startHour,mrf.gamme_minute endMinute,hrf.valeur_heure endHour,r.date_reservation FROM obj_reservation r \n" +
                    "LEFT JOIN gamme_minutes_reservation mrd ON r.id_minute_debut_reservation = mrd.id_min \n" +
                    "LEFT JOIN gamme_heure_reservation hrd ON r.id_heure_debut_reservation = hrd.id_he \n" +
                    "LEFT JOIN gamme_minutes_reservation mrf ON r.id_minute_fin_reservation = mrf.id_min \n" +
                    "LEFT JOIN gamme_heure_reservation hrf ON r.id_heure_fin_reservation = hrf.id_he\n" +
                    "WHERE r.id_borne = ? AND r.date_reservation BETWEEN ? AND ?";

    private static  final String REQ_FETCH_WEEKLY_OPENINGHOURS_BY_STATION =
            "SELECT mrd.gamme_minute startMinute,hrd.valeur_heure startHour,mrf.gamme_minute endMinute,hrf.valeur_heure endHour,hob.id_jour FROM obj_horaire_ouverture_borne hob \n" +
            "LEFT JOIN gamme_minutes_reservation mrd ON hob.id_min_deb = mrd.id_min \n" +
            "LEFT JOIN gamme_heure_reservation hrd ON hob.id_he_deb = hrd.id_he \n" +
            "LEFT JOIN gamme_minutes_reservation mrf ON hob.id_min_fin = mrf.id_min \n" +
            "LEFT JOIN gamme_heure_reservation hrf ON hob.id_he_fin = hrf.id_he\n" +
            "WHERE hob.Id_Station_de_recharge = ?";

    private final String REQ_FETCH_DISPONIBILITY = 
            "SET @station_id = ?; " +
                    "SET @moment = ?; " +
                    " " +
                    "SELECT  " +
                    "    CASE  " +
                    "        -- Vérifier si la station est fermée pendant les vacances " +
                    "        WHEN EXISTS ( " +
                    "            SELECT 1 " +
                    "            FROM obj_periode_de_fermeture_borne pdfb " +
                    "            WHERE pdfb.Id_Station_de_recharge = @station_id " +
                    "            AND @moment BETWEEN pdfb.date_de_debut AND pdfb.date_de_fin " +
                    "        ) THEN 'CLOSED' " +
                    " " +
                    "        -- Vérifier si la station est ouverte à l'heure spécifiée " +
                    "        WHEN NOT EXISTS ( " +
                    "            SELECT 1 " +
                    "        FROM obj_horaire_ouverture_borne hob " +
                    "         " +
                    "        JOIN gamme_heure_reservation ghr_s ON hob.id_he_deb = ghr_s.id_he " +
                    "        JOIN gamme_minutes_reservation gmr_s ON hob.id_min_deb = gmr_s.id_min " +
                    "        JOIN gamme_heure_reservation ghr_f ON hob.id_he_fin = ghr_f.id_he " +
                    "        JOIN gamme_minutes_reservation gmr_f ON hob.id_min_fin = gmr_f.id_min " +
                    "        JOIN gamme_jour_de_semaine gjs ON hob.id_jour = gjs.id_jour " +
                    "         " +
                    "        WHERE hob.Id_Station_de_recharge = @station_id " +
                    "        AND DAYOFWEEK(@moment) = 5 " +
                    "        AND TIME(@moment) BETWEEN TIME(STR_TO_DATE(CONCAT('2000-01-1 ',ghr_s.valeur_heure, ':', gmr_s.gamme_minute), '%Y-%m-%e %H:%i')) AND TIME(STR_TO_DATE(CONCAT('2030-01-1 ',ghr_f.valeur_heure, ':', gmr_f.gamme_minute), '%Y-%m-%e %H:%i')) " +
                    "        ) THEN 'CLOSED' " +
                    " " +
                    "        -- Vérifier si la station est réservée à l'heure spécifiée " +
                    "        WHEN EXISTS ( " +
                    "            SELECT 1 " +
                    "            FROM obj_reservation r " +
                    "             " +
                    "            JOIN gamme_heure_reservation ghr_s ON r.id_heure_debut_reservation = ghr_s.id_he " +
                    "        JOIN gamme_minutes_reservation gmr_s ON r.id_minute_debut_reservation = gmr_s.id_min " +
                    "        JOIN gamme_heure_reservation ghr_f ON r.id_heure_fin_reservation = ghr_f.id_he " +
                    "        JOIN gamme_minutes_reservation gmr_f ON r.id_minute_fin_reservation = gmr_f.id_min " +
                    "             " +
                    "            WHERE r.id_borne = @station_id " +
                    "        AND r.date_reservation = DATE(@moment) " +
                    "        AND TIME(@moment) BETWEEN TIME(STR_TO_DATE(CONCAT('2000-01-1 ',ghr_s.valeur_heure, ':', gmr_s.gamme_minute), '%Y-%m-%e %H:%i')) AND TIME(STR_TO_DATE(CONCAT('2030-01-1 ',ghr_f.valeur_heure, ':', gmr_f.gamme_minute), '%Y-%m-%e %H:%i')) " +
                    "        ) THEN 'RESERVED' " +
                    " " +
                    "        -- Si toutes les conditions précédentes échouent, la station est libre " +
                    "        ELSE 'FREE' " +
                    "    END AS STATUS";

    public Calendar getCalendar(long stationId, LocalDate start) {
        Calendar calendar = new Calendar();
        try (Connection connection = dataSource.getConnection()) {

            setReservations(calendar,connection,stationId,start);
            setOpeningHours(calendar,connection,stationId,start);

        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la création d'un calendrier pour la station d'{}",stationId, e);
        }
        return calendar;
    }

    private void setReservations(Calendar calendar, Connection connection, long stationId, LocalDate start) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(REQ_FETCH_WEEKLY_RESERVATIONS_BY_STATION);
        statement.setLong(1, stationId);
        statement.setDate(2, DateUtils.convert(start));
        statement.setDate(3, DateUtils.convert(start.plusWeeks(1)));
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            int day = (int)ChronoUnit.DAYS.between(start,DateUtils.convert(resultSet.getDate("date_reservation")));
            calendar.getJ()[day].getReservations().add(buildHourSlot(resultSet));
        }
    }

    private void setOpeningHours(Calendar calendar, Connection connection, long stationId, LocalDate start) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(REQ_FETCH_WEEKLY_OPENINGHOURS_BY_STATION);
        statement.setLong(1, stationId);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {

            int startDay = start.getDayOfWeek().plus(1).getValue();
            int endDay = (int)resultSet.getLong("id_jour");
            int day = (endDay - startDay);
            if (day < 0) day += 7;
            calendar.getJ()[day].getOpeningHours().add(buildHourSlot(resultSet));
        }
    }

        private HourSlots buildHourSlot(ResultSet resultSet) throws SQLException {
        return new HourSlots(
                resultSet.getInt("startHour"),
                resultSet.getInt("startMinute"),
                resultSet.getInt("endHour"),
                resultSet.getInt("endMinute")
        );
    }

    public CalendarDisponibility fetchDisponibility(LocalDateTime moment, long stationId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FETCH_DISPONIBILITY);
            statement.setLong(1, stationId);
            statement.setTimestamp(2, DateUtils.convert(moment));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return CalendarDisponibility.valueOf(resultSet.getString("STATUS"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des horaires d'ouverture de la borne d'id {}", stationId, e);
        }
        return null;
    }


    // Alternative to REQ_FETCH_DISPONIBILITY
    // without SET @ because not supported by JDBC
    private final String REQ_FIND_DISPONIBILITY =
            "SELECT " +
            "    CASE " +
            "        WHEN EXISTS ( " +
            "            SELECT 1 " +
            "            FROM obj_periode_de_fermeture_borne pdfb " +
            "            WHERE pdfb.Id_Station_de_recharge = ? " +
            "            AND ? BETWEEN pdfb.date_de_debut AND pdfb.date_de_fin " +
            "        ) THEN 'CLOSED' " +
            " " +
            "        WHEN NOT EXISTS ( " +
            "            SELECT 1 " +
            "            FROM obj_horaire_ouverture_borne hob " +
            "            JOIN gamme_heure_reservation ghr_s ON hob.id_he_deb = ghr_s.id_he " +
            "            JOIN gamme_minutes_reservation gmr_s ON hob.id_min_deb = gmr_s.id_min " +
            "            JOIN gamme_heure_reservation ghr_f ON hob.id_he_fin = ghr_f.id_he " +
            "            JOIN gamme_minutes_reservation gmr_f ON hob.id_min_fin = gmr_f.id_min " +
            "            JOIN gamme_jour_de_semaine gjs ON hob.id_jour = gjs.id_jour " +
            "            WHERE hob.Id_Station_de_recharge = ? " +
            "            AND DAYOFWEEK(?) = hob.id_jour " + // lundi = 0 vendredi = 4
            "            AND TIME(?) BETWEEN TIME(STR_TO_DATE(CONCAT('2000-01-1 ', ghr_s.valeur_heure, ':', gmr_s.gamme_minute), '%Y-%m-%e %H:%i')) " +
            "            AND TIME(STR_TO_DATE(CONCAT('2030-01-1 ', ghr_f.valeur_heure, ':', gmr_f.gamme_minute), '%Y-%m-%e %H:%i')) " +
            "        ) THEN 'CLOSED' " +
            " " +
            "        WHEN EXISTS ( " +
            "            SELECT 1 " +
            "            FROM obj_reservation r " +
            "            JOIN gamme_heure_reservation ghr_s ON r.id_heure_debut_reservation = ghr_s.id_he " +
            "            JOIN gamme_minutes_reservation gmr_s ON r.id_minute_debut_reservation = gmr_s.id_min " +
            "            JOIN gamme_heure_reservation ghr_f ON r.id_heure_fin_reservation = ghr_f.id_he " +
            "            JOIN gamme_minutes_reservation gmr_f ON r.id_minute_fin_reservation = gmr_f.id_min " +
            "            WHERE r.id_borne = ? " +
            "            AND r.date_reservation = DATE(?) " +
            "            AND TIME(?) BETWEEN TIME(STR_TO_DATE(CONCAT('2000-01-1 ', ghr_s.valeur_heure, ':', gmr_s.gamme_minute), '%Y-%m-%e %H:%i')) " +
            "            AND TIME(STR_TO_DATE(CONCAT('2030-01-1 ', ghr_f.valeur_heure, ':', gmr_f.gamme_minute), '%Y-%m-%e %H:%i')) " +
            "        ) THEN 'RESERVED' " +
            " " +
            "        ELSE 'FREE' " +
            "    END AS STATUS";


    public CalendarDisponibility findStationDisponibility(LocalDateTime moment, long stationId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(REQ_FIND_DISPONIBILITY)) {



            // WHEN EXISTS
            statement.setLong(1, stationId);
            statement.setTimestamp(2, Timestamp.valueOf(moment));

            // WHEN NOT EXISTS
            statement.setLong(3, stationId);
            statement.setTimestamp(4, Timestamp.valueOf(moment));
            statement.setTimestamp(5, Timestamp.valueOf(moment));

            // WHEN EXISTS
            statement.setLong(6, stationId);
            statement.setTimestamp(7, Timestamp.valueOf(moment));
            statement.setTimestamp(8, Timestamp.valueOf(moment));

            logger.info("moment = "+moment);
            logger.info("Timestamp.valueOf(moment) = "+Timestamp.valueOf(moment));
            //logger.info("dayOfWeek = "+dayOfWeek);

            // Exécution de la requête et traitement du résultat
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("CalendarDisponibility.valueOf(resultSet.getString(\"STATUS\") = "+CalendarDisponibility.valueOf(resultSet.getString("STATUS")));
                return CalendarDisponibility.valueOf(resultSet.getString("STATUS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}