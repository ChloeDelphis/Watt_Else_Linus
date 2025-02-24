package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.ReservationDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.CreditCardUtils;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.SqlUtils;
import fr.eql.ai116.linus.wattelse.entity.dto.CreditCardDto;
import fr.eql.ai116.linus.wattelse.entity.dto.FullReservationDto;
import fr.eql.ai116.linus.wattelse.entity.dto.VehicleDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.Facturation;
import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;
import fr.eql.ai116.linus.wattelse.entity.pojo.Reservation;
import fr.eql.ai116.linus.wattelse.entity.pojo.Station;
import fr.eql.ai116.linus.wattelse.entity.range.Power;
import fr.eql.ai116.linus.wattelse.entity.range.RatingType;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationAnormalEnding;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationHour;
import fr.eql.ai116.linus.wattelse.entity.range.ReservationMinute;
import fr.eql.ai116.linus.wattelse.entity.range.Socket;
import fr.eql.ai116.linus.wattelse.entity.range.StationClosingMotive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Remote(ReservationDao.class)
@Stateless
public class ReservationDaoImpl implements ReservationDao {

    private static final Logger logger = LogManager.getLogger();

    private final DataSource dataSource = new WattElseDataSource();

    private static  final String REQ_INSERT_RESERVATION =
            "INSERT INTO obj_reservation (id_vehicule, id_borne, date_reservation, id_heure_debut_reservation, id_minute_debut_reservation, id_heure_fin_reservation, id_minute_fin_reservation, id_facturation, Date_enreg_resa) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static  final String REQ_UPDATE_RESERVATION =
            "UPDATE obj_reservation " +
            "SET Quantite_energie_consomee = ?, " +
            "Cout_recharge = ?, " +
            "Id_Evaluation = ?, " +
            "Date_debut_recharge = ?, " +
            "Date_fin_recharge = ?, " +
            "date_de_validation_paie = ?, " +
            "date_de_fin_ano = ?, " +
            "Id_Fin_anormale = ?, " +
            "id_vehicule = ?, " +
            "date_reservation = ?, " +
            "id_heure_debut_reservation = ?, " +
            "id_minute_debut_reservation = ?, " +
            "id_heure_fin_reservation = ?, " +
            "id_minute_fin_reservation = ?, " +
            "id_Carte = ? " +
            "WHERE Id_reservation = ?; ";

    private static  final String REQ_FETCH_RESERVATION_BY_ID =
            "SELECT r.*,mrd.gamme_minute mrd,hrd.valeur_heure hrd,mrf.gamme_minute mrf,hrf.valeur_heure hrf,mfar.Libelle mfar FROM obj_reservation r\n" +
            "LEFT JOIN gamme_minutes_reservation mrd ON r.id_minute_debut_reservation = mrd.id_min\n" +
            "LEFT JOIN gamme_heure_reservation hrd ON r.id_heure_debut_reservation = hrd.id_he\n" +
            "LEFT JOIN gamme_minutes_reservation mrf ON r.id_minute_debut_reservation = mrf.id_min\n" +
            "LEFT JOIN gamme_heure_reservation hrf ON r.id_heure_debut_reservation = hrf.id_he\n" +
            "LEFT JOIN gamme_motif_fin_anormale_reservation mfar ON r.Id_Fin_anormale = mfar.Id_Fin_anormale\n" +
            "WHERE r.Id_reservation = ?";

    private static  final String REQ_FETCH_RESERVATIONS_BY_VEHICLE =
            "SELECT r.*,mrd.gamme_minute mrd,hrd.valeur_heure hrd,mrf.gamme_minute mrf,hrf.valeur_heure hrf,mfar.Libelle mfar FROM obj_reservation r\n" +
            "JOIN obj_vehicule v ON r.id_vehicule = v.Id_Vehicule\n" +
            "LEFT JOIN gamme_minutes_reservation mrd ON r.id_minute_debut_reservation = mrd.id_min\n" +
            "LEFT JOIN gamme_heure_reservation hrd ON r.id_heure_debut_reservation = hrd.id_he\n" +
            "LEFT JOIN gamme_minutes_reservation mrf ON r.id_minute_debut_reservation = mrf.id_min\n" +
            "LEFT JOIN gamme_heure_reservation hrf ON r.id_heure_debut_reservation = hrf.id_he\n" +
            "LEFT JOIN gamme_motif_fin_anormale_reservation mfar ON r.Id_Fin_anormale = mfar.Id_Fin_anormale\n" +
            "WHERE v.Id_Vehicule = ? ";

    private static  final String REQ_FETCH_RESERVATIONS_BY_STATION =
            "SELECT r.*,mrd.gamme_minute mrd,hrd.valeur_heure hrd,mrf.gamme_minute mrf,hrf.valeur_heure hrf,mfar.Libelle mfar FROM obj_reservation r " +
            "JOIN obj_borne b ON r.id_borne = b.Id_Station_de_recharge " +
            "LEFT JOIN gamme_minutes_reservation mrd ON r.id_minute_debut_reservation = mrd.id_min " +
            "LEFT JOIN gamme_heure_reservation hrd ON r.id_heure_debut_reservation = hrd.id_he " +
            "LEFT JOIN gamme_minutes_reservation mrf ON r.id_minute_debut_reservation = mrf.id_min " +
            "LEFT JOIN gamme_heure_reservation hrf ON r.id_heure_debut_reservation = hrf.id_he " +
            "LEFT JOIN gamme_motif_fin_anormale_reservation mfar ON r.Id_Fin_anormale = mfar.Id_Fin_anormale " +
            "WHERE b.Id_Station_de_recharge = ?";

    private static  final String REQ_FETCH_RESERVATIONS_BY_DRIVER =
            "SELECT r.*,mrd.gamme_minute mrd,hrd.valeur_heure hrd,mrf.gamme_minute mrf,hrf.valeur_heure hrf,mfar.Libelle mfar FROM obj_reservation r " +
            "JOIN obj_vehicule v ON r.id_vehicule = v.Id_Vehicule " +
            "LEFT JOIN gamme_minutes_reservation mrd ON r.id_minute_debut_reservation = mrd.id_min " +
            "LEFT JOIN gamme_heure_reservation hrd ON r.id_heure_debut_reservation = hrd.id_he " +
            "LEFT JOIN gamme_minutes_reservation mrf ON r.id_minute_debut_reservation = mrf.id_min " +
            "LEFT JOIN gamme_heure_reservation hrf ON r.id_heure_debut_reservation = hrf.id_he " +
            "LEFT JOIN gamme_motif_fin_anormale_reservation mfar ON r.Id_Fin_anormale = mfar.Id_Fin_anormale " +
            "WHERE v.Id_Utilisateur = ?";

    private static  final String REQ_FETCH_RESERVATIONS_BY_OWNER =
            "SELECT r.*,mrd.gamme_minute mrd,hrd.valeur_heure hrd,mrf.gamme_minute mrf,hrf.valeur_heure hrf,mfar.Libelle mfar FROM obj_reservation r " +
            "JOIN obj_borne b ON r.id_borne = b.Id_Station_de_recharge " +
            "LEFT JOIN gamme_minutes_reservation mrd ON r.id_minute_debut_reservation = mrd.id_min " +
            "LEFT JOIN gamme_heure_reservation hrd ON r.id_heure_debut_reservation = hrd.id_he " +
            "LEFT JOIN gamme_minutes_reservation mrf ON r.id_minute_debut_reservation = mrf.id_min " +
            "LEFT JOIN gamme_heure_reservation hrf ON r.id_heure_debut_reservation = hrf.id_he " +
            "LEFT JOIN gamme_motif_fin_anormale_reservation mfar ON r.Id_Fin_anormale = mfar.Id_Fin_anormale " +
            "WHERE b.Id_Utilisateur = ?";

    @Override
    public void registerNewReservation(Reservation reservation, long idHeureDebutReservation, long idMinuteDebutReservation, long idHeureFinReservation, long idMinuteFinReservation) {

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_RESERVATION, Statement.RETURN_GENERATED_KEYS);
//(`id_vehicule`, `id_borne`, `date_reservation`, `id_heure_debut_reservation`, `id_minute_debut_reservation`, `id_heure_fin_reservation`, `id_minute_fin_reservation`, ` id_facturation`)
                statement.setLong(1, reservation.getIdVehicule()); //id_vehicule
                statement.setLong(2, reservation.getIdStation()); //id_borne
                statement.setDate(3, DateUtils.convert(reservation.getDateReservation())); //date_reservation
                statement.setLong(4, idHeureDebutReservation); //id_heure_debut_reservation
                statement.setLong(5, idMinuteDebutReservation); //id_minute_debut_reservation
                statement.setLong(6, idHeureFinReservation); //id_heure_fin_reservation
                statement.setLong(7, idMinuteFinReservation); //id_minute_fin_reservation
                statement.setLong(8, reservation.getIdFacturation()); // id_facturation
                statement.setDate(9, DateUtils.convert(reservation.getReservationRegisterDate())); // Date_enreg_resa

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        reservation.setIdReservation(id);
                    }
                }
                connection.commit();
                logger.info("la réservation du véhicule d'id {} sur la station d'id {} a été inséré en base de données avec l'id {}", reservation.getIdVehicule(),reservation.getIdStation(), reservation.getIdReservation());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de la réservation", e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    /**
     * Met à jour les paramètres suivant dans une réservation:
     * Quantite_energie_consomee
     * Cout_recharge
     * Id_Evaluation
     * Date_debut_recharge
     * Date_fin_recharge
     * date_de_validation_paie
     * date_de_fin_ano
     * Id_Fin_anormale
     * @param reservation
     */
    @Override
    public void updateReservation(Reservation reservation) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            System.out.println("updateReservation" + reservation);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_RESERVATION);
                statement.setFloat(1, reservation.getEnergyConsumed()); // Quantite_energie_consomee
                statement.setDouble(2, reservation.getCost()); // Cout_recharge
                statement.setObject(3, reservation.getIdRating()); // Id_Evaluation
                statement.setTimestamp(4, DateUtils.convert(reservation.getDateDebutRecharge())); // Date_debut_recharge
                statement.setTimestamp(5, DateUtils.convert(reservation.getDateFinRecharge())); // Date_fin_recharge
                statement.setDate(6, DateUtils.convert(reservation.getDateDriverFacturation())); // date_de_validation_paie
                statement.setDate(7, DateUtils.convert(reservation.getDateAnormalEnding())); // date_de_fin_ano
                statement.setObject(8, (reservation.getReservationAnormalEnding() == null
                        ?null
                        :reservation.getReservationAnormalEnding().getAnormalEndingId())); // Id_Fin_anormale
                statement.setLong(9, reservation.getIdVehicule()); // id_vehicule
                statement.setDate(10, DateUtils.convert(reservation.getDateReservation())); // date_reservation
                statement.setLong(11, reservation.getStartReservationHours().getIdHour()); // id_heure_debut_reservation
                statement.setLong(12, reservation.getStartReservationMinutes().getIdHour()); // id_minute_debut_reservation
                statement.setLong(13, reservation.getEndReservationHours().getIdHour()); // id_heure_fin_reservation
                statement.setLong(14, reservation.getEndReservationMinutes().getIdHour()); // id_minute_fin_reservation
                statement.setLong(15, 1); // id_Carte
                statement.setLong(16, reservation.getIdReservation()); // Id_reservation

                statement.executeUpdate();
                connection.commit();
                logger.info("la réservation d'id {} a été mise à jour.", reservation.getIdReservation());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de la mise à jour de la réservation d'id {}",reservation.getIdReservation(),  e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    public Reservation getReservationsById(long reservationId) {
        List<Reservation> result = requestReservations(REQ_FETCH_RESERVATION_BY_ID, reservationId);
        return result.isEmpty()?null:result.get(0);
    }

    public List<Reservation> getReservationsByStation(long stationId) {
        return requestReservations(REQ_FETCH_RESERVATIONS_BY_STATION, stationId);
    }

    public List<Reservation> getReservationsByVehicle(long vehicleId) {
        return requestReservations(REQ_FETCH_RESERVATIONS_BY_VEHICLE, vehicleId);
    }

    public List<Reservation> getReservationsByOwner(long userId) {
        return requestReservations(REQ_FETCH_RESERVATIONS_BY_OWNER, userId);
    }

    public List<Reservation> getReservationsByDriver(long userId) {
        return requestReservations(REQ_FETCH_RESERVATIONS_BY_DRIVER, userId);
    }

    private List<Reservation> requestReservations(String SQLRequest, long parameter) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQLRequest);
            statement.setLong(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                reservations.add(buildReservation(resultSet));

            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des réservations en base de données avec la requête suivante:\r\n" + SQLRequest, e);
        }
        return reservations;
    }

    private Reservation buildReservation(ResultSet resultSet) throws SQLException {
        return new Reservation(
                resultSet.getLong("Id_reservation"),  // Long idReservation
                resultSet.getLong("id_vehicule"), // Long idVehicule
                resultSet.getLong("id_borne"), // Long idStation
                DateUtils.convert(resultSet.getDate("date_reservation")), // LocalDate dateReservation
                new ReservationHour( // ReservationHour startReservationHours
                        resultSet.getLong("id_heure_debut_reservation"),
                        resultSet.getInt("hrd")
                ),
                new ReservationMinute( // ReservationMinute startReservationMinutes
                        resultSet.getLong("id_minute_debut_reservation"),
                        resultSet.getInt("mrd")
                ),
                new ReservationHour( //ReservationHour endReservationHours
                        resultSet.getLong("id_heure_fin_reservation"),
                        resultSet.getInt("hrf")
                ),
                new ReservationMinute( //ReservationMinute endReservationMinutes
                        resultSet.getLong("id_minute_fin_reservation"),
                        resultSet.getInt("mrf")
                ),
                resultSet.getLong("id_facturation"), // Long idFacturation
                DateUtils.convert(resultSet.getDate("Date_enreg_resa")), // LocalDate reservationRegisterDate
                resultSet.getLong("id_Carte"), // Long idCreditcard
                resultSet.getFloat("Quantite_energie_consomee"), // Float energyConsumed
                resultSet.getDouble("Cout_recharge"), // Double cost
                SqlUtils.readLong("Id_Evaluation",resultSet), // Long idRating
                DateUtils.convert(resultSet.getTimestamp("Date_debut_recharge")), // LocalDate dateDebutRecharge
                DateUtils.convert(resultSet.getTimestamp("Date_fin_recharge")), // LocalDate dateFinRecharge
                DateUtils.convert(resultSet.getDate("date_de_validation_paie")), // LocalDate dateDriverFacturation
                DateUtils.convert(resultSet.getDate("date_de_fin_ano")), // LocalDate dateAnormalEnding
                new ReservationAnormalEnding( // ReservationAnormalEnding reservationAnormalEnding
                        SqlUtils.readLong("Id_Fin_anormale",resultSet),
                        resultSet.getString("mfar")
                )
        );
    }

    /// FULL RESERVATION

    private static  final String REQ_FETCH_FULL_RESERVATION_BY_VEHICLE =
            "SELECT " +
            "r.Id_reservation, " +
            "v.Id_Vehicule, v.Nom, v.Plaque_immatriculation, v.Id_Utilisateur AS Id_Utilisateur_vehicle, " +
            "pv.Id_Type_de_prise AS Id_Type_de_prise_vehicle, pv.Libelle AS Libelle_prise_vehicle, " +
            "p.Id_Type_de_prise, p.Libelle, " +
            "v.Date_ajout, v.Date_retrait, " +
            "b.Id_Station_de_recharge, b.nom AS nom_station, b.Id_Utilisateur AS Id_Utilisateur_borne, b.latitude, b.longitude, b.address_display, b.code_postal AS code_postal_station, b.id_motif_retrait, " +

            "uf.Id_Utilisateur AS Id_Utilisateur_facturation, uf.Email, uf.Mot_de_passe, uf.Prenom, uf.Nom AS Nom_Utilisateur, " +
            "uf.role, uf.latitude AS user_latitude_borne, uf.longitude AS user_longitude_borne, " +
            "uf.address_display AS user_address_borne, uf.code_postal AS code_postal_user_borne, " +

            "gmf.Id_Motif_fermeture, gmf.Libelle AS libelle_motif_fermeture, " +
            "tb.id_tarification, tb.prix, tb.date_de_demarrage_tarif, tb.date_de_fin_tarif, " +
            "gtt.id_type_tarification, gtt.libelle_type_tarification, gtt.abreviation, " +
            "gp.id_puissance, gp.puissance, " +
            "hr.id_he AS id_heure_debut, hr.valeur_heure AS Heure_Debut, " +
            "mr.id_min AS id_minute_debut, mr.gamme_minute AS Minute_Debut, " +
            "hr_fin.id_he AS id_heure_fin, hr_fin.valeur_heure AS Heure_Fin, " +
            "mr_fin.id_min AS id_minute_fin, mr_fin.gamme_minute AS Minute_Fin, " +
            "gmb.id_motif_retrait AS id_motif_retrait_borne, gmb.motif_retrait AS motif_retrait_borne, " +
            "rp.id_facturation, rp.id_user, rp.Date_reglement, rp.Montant, " +
            "cb.id_Carte, cb.numeroDeCarte, cb.dateDeValidite, cb.codeDeSecurite, " +
            "r.Id_Evaluation, " +
            "oev.Id_Evaluation AS id_rating, oev.Id_type_avis, oev.commentaire_additionnel, oev.note, " +
            "gta.Id_type_avis AS Id_type_avis_gamme, gta.Libelle_type_avis, " +
            "r.Id_Fin_anormale, " +
            "fa.Libelle AS Fin_Anormale, fa.Id_Fin_anormale AS Fin_Anormale_Id, " +
            "r.Date_debut_recharge, r.Date_fin_recharge, r.date_de_validation_paie, r.date_de_fin_ano, r.date_reservation, r.Date_enreg_resa, r.Quantite_energie_consomee, r.Cout_recharge " +
            "FROM obj_reservation r " +
            "LEFT JOIN obj_vehicule v ON r.id_vehicule = v.Id_Vehicule " +
            "LEFT JOIN gamme_type_de_prise pv ON v.Id_Type_de_prise = pv.Id_Type_de_prise " +
            "LEFT JOIN obj_borne b ON r.id_borne = b.Id_Station_de_recharge " +
            "LEFT JOIN gamme_type_de_prise p ON v.Id_Type_de_prise = p.Id_Type_de_prise " +
            "LEFT JOIN obj_utilisateur uf ON b.Id_Utilisateur = uf.Id_Utilisateur " +
            "LEFT JOIN gamme_motif_fermeture_compte gmf ON uf.Id_Motif_fermeture = gmf.Id_Motif_fermeture " +
            "LEFT JOIN obj_tarification_borne tb ON b.id_tarification = tb.id_tarification " +
            "LEFT JOIN gamme_type_de_tarification gtt ON tb.id_type_tarification = gtt.id_type_tarification " +
            "LEFT JOIN gamme_puissance gp ON b.id_puissance = gp.id_puissance " +
            "LEFT JOIN gamme_heure_reservation hr ON r.id_heure_debut_reservation = hr.id_he " +
            "LEFT JOIN gamme_minutes_reservation mr ON r.id_minute_debut_reservation = mr.id_min " +
            "LEFT JOIN gamme_heure_reservation hr_fin ON r.id_heure_fin_reservation = hr_fin.id_he " +
            "LEFT JOIN gamme_minutes_reservation mr_fin ON r.id_minute_fin_reservation = mr_fin.id_min " +
            "LEFT JOIN gamme_motif_de_retrait_borne gmb ON b.id_motif_retrait = gmb.id_motif_retrait " +
            "LEFT JOIN obj_facturation rp ON r.id_facturation = rp.id_facturation " +
            "LEFT JOIN obj_carte_bancaire cb ON r.id_Carte = cb.id_Carte " +
            "LEFT JOIN obj_evaluation oev ON r.Id_Evaluation = oev.Id_Evaluation " +
            "LEFT JOIN gamme_type_d_avis gta ON oev.Id_Evaluation = gta.Id_type_avis " +
            "LEFT JOIN gamme_motif_fin_anormale_reservation fa ON r.Id_Fin_anormale = fa.Id_Fin_anormale " +
            "WHERE v.Id_Vehicule = ? AND r.date_de_fin_ano IS NULL " +
            "ORDER BY r.date_reservation ";

    public List<FullReservationDto> getFullReservationsByVehicle(long vehicleId) {
        List<FullReservationDto> reservations = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FETCH_FULL_RESERVATION_BY_VEHICLE);
            statement.setLong(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                reservations.add(buildFullReservation(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des réservations (par véhicule) en base de données", e);
        }
        return reservations;
    }

    private FullReservationDto buildFullReservation(ResultSet resultSet) throws SQLException {
        return new FullReservationDto(
                resultSet.getLong("Id_reservation"),
                new VehicleDto(
                        resultSet.getLong("Id_Vehicule"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Plaque_immatriculation"),
                        new Socket(
                                resultSet.getLong("Id_Type_de_prise_vehicle"),
                                resultSet.getString("Libelle_prise_vehicle")
                        ),
                        resultSet.getLong("Id_Utilisateur_vehicle")
                ),
                new Station(
                        resultSet.getLong("Id_Station_de_recharge"),
                        resultSet.getString("nom_station"),  // Nom de la station
                        resultSet.getLong("Id_Utilisateur_borne"),
                        resultSet.getLong("id_tarification"),
                        new Socket(
                                resultSet.getLong("Id_Type_de_prise"),
                                resultSet.getString("Libelle")
                        ),
                        new Power(
                                resultSet.getLong("id_puissance"),
                                resultSet.getFloat("puissance")
                        ),
                        DateUtils.convert(resultSet.getDate("Date_ajout")),
                        DateUtils.convert(resultSet.getDate("Date_retrait")),
                        resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude"),
                        resultSet.getString("code_postal_station"),
                        resultSet.getString("address_display"),
                        (resultSet.getObject("id_motif_retrait", Long.class) == null
                                ? null
                                : new StationClosingMotive(
                                    resultSet.getLong("id_motif_retrait_borne"),
                                    SqlUtils.ifNullString(resultSet.getString("motif_retrait_borne"))
                                )
                        )
                ),
                DateUtils.convert(resultSet.getDate("date_reservation")),
                new ReservationHour(
                        resultSet.getLong("id_heure_debut"),
                        resultSet.getInt("Heure_Debut")
                ),
                new ReservationMinute(
                        resultSet.getLong("id_minute_debut"),
                        resultSet.getInt("Minute_Debut")
                ),
                new ReservationHour(
                        resultSet.getLong("id_heure_fin"),
                        resultSet.getInt("Heure_Fin")
                ),
                new ReservationMinute(
                        resultSet.getLong("id_minute_fin"),
                        resultSet.getInt("Minute_Fin")
                ),
                new Facturation(
                        resultSet.getLong("id_facturation"),
                        resultSet.getLong("Id_Utilisateur_facturation"),
                        DateUtils.convert(resultSet.getDate("Date_reglement")),
                        resultSet.getDouble("Montant")
                ),
                DateUtils.convert(resultSet.getDate("Date_enreg_resa")),
                new CreditCardDto(
                        resultSet.getLong("id_Carte"),
                        CreditCardUtils.hideCreditCard(resultSet.getString("numeroDeCarte")),
                        DateUtils.convert(resultSet.getDate("dateDeValidite")),
                        CreditCardUtils.hideCss(resultSet.getInt("codeDeSecurite"))
                ),
                resultSet.getInt("Quantite_energie_consomee"),
                resultSet.getDouble("Cout_recharge"),
                (resultSet.getObject("Id_Evaluation", Long.class) == null
                        ? null
                        : new Rating(
                            resultSet.getLong("id_rating"),
                            new RatingType(
                                    resultSet.getLong("Id_type_avis_gamme"),
                                    SqlUtils.ifNullString(resultSet.getString("Libelle_type_avis"))
                            ),
                            SqlUtils.ifNullString(resultSet.getString("commentaire_additionnel")),
                            resultSet.getInt("note")
                        )
                ),
                DateUtils.convert(resultSet.getTimestamp("Date_debut_recharge")),
                DateUtils.convert(resultSet.getTimestamp("Date_fin_recharge")),
                DateUtils.convert(resultSet.getTimestamp("date_de_validation_paie")),
                DateUtils.convert(resultSet.getTimestamp("date_de_fin_ano")),
                (resultSet.getObject("Id_Fin_anormale", Long.class) == null
                        ? null
                        : new ReservationAnormalEnding(
                            resultSet.getLong("Fin_Anormale_Id"),
                            SqlUtils.ifNullString(resultSet.getString("Fin_Anormale"))
                        )
                )
        );
    }
}
