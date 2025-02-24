package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.exceptions.UserAlreadyExistException;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.Role;
import fr.eql.ai116.linus.wattelse.entity.pojo.Session;
import fr.eql.ai116.linus.wattelse.entity.pojo.User;
import fr.eql.ai116.linus.wattelse.entity.range.AccountClosingMotive;
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
import java.sql.Timestamp;
import java.time.Instant;

@Remote(UserDao.class)
@Stateless
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_AUTH =
            "SELECT * FROM obj_utilisateur u " +
            "LEFT JOIN gamme_motif_fermeture_compte mfc ON u.Id_Motif_fermeture = mfc.Id_Motif_fermeture " +
            "WHERE u.Email = ? AND u.Mot_de_passe = ?";

    private static final String REQ_FIND_SESSION = "SELECT * FROM obj_session WHERE token = ? ";

    private static final String REQ_UPDATE_SESSION =
            "INSERT INTO obj_session (token, timestamp, id_utilisateur) VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE token = ?, timestamp = ?";

    private static final String REQ_ROLE_BY_ID = "SELECT role FROM obj_utilisateur WHERE Id_Utilisateur = ?";

    private static  final String REQ_INSERT_USER =
            "INSERT INTO `watt_else_db`.`obj_utilisateur` (`Email`, `Mot_de_passe`, `Prenom`, `Nom`, `Telephone`, `Naissance`, `Ouverture_compte`,`latitude`, `longitude`, `address_display`, `code_postal`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String REQ_FIND_USER_BY_STATION_ID =
            "SELECT * FROM obj_utilisateur u " +
            "LEFT JOIN gamme_motif_fermeture_compte mfc ON u.Id_Motif_fermeture = mfc.Id_Motif_fermeture " +
            "JOIN obj_borne b ON b.Id_Utilisateur = u.Id_Utilisateur " +
            "WHERE b.Id_Station_de_recharge = ?";

    @Override
    public Session findSession(String token) {
        Session session = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_SESSION);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                session = new Session(
                        resultSet.getLong("id_session"),
                        resultSet.getString("token"),
                        resultSet.getTimestamp("timestamp"),
                        resultSet.getLong("id_utilisateur")
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation de la session utilisateur en base de données", e);
        }
        return session;
    }

    @Override
    public void updateSession(String token, long userId) {

        try (Connection connection = dataSource.getConnection()){
			PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_SESSION);
			statement.setString(1, token);
			statement.setTimestamp(2, Timestamp.from(Instant.now()));
			statement.setLong(3, userId);
			statement.setString(4, token);
			statement.setTimestamp(5, Timestamp.from(Instant.now()));
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite " +
					"lors de la mise à jour de la session utilisateur en base de données", e);
		}
    }

    @Override
    public Role findRoleById(long id) {
        Role role = null;
		try (Connection connection = dataSource.getConnection()){
			PreparedStatement statement = connection.prepareStatement(REQ_ROLE_BY_ID);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				role = Role.valueOf(resultSet.getString("role"));
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite " +
					"lors de la consultation du rôle du propriétaire en base de données", e);
		}
		return role;
    }

    @Override
    public User authenticate(String email, String password) {
        User user = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_AUTH);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation du propriétaire en base de données", e);
        }
        return user;
    }

    @Override
    public void registerNewUser(User user) throws UserAlreadyExistException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
// (`Email`, `Mot_de_passe`, `Prenom`, `Nom`, `Telephone`, `Naissance`, `Ouverture_compte`, `latitude`, `longitude`, `address_display`, `code_postal`)
                statement.setString(1, user.getEmail()); // `Email`
                statement.setString(2, user.getPassword()); // `Mot_de_passe`
                statement.setString(3, user.getFirstName()); // `Prenom`
                statement.setString(4, user.getLastName()); // `Nom`
                statement.setString(5, user.getPhone()); // `Telephone`
                statement.setDate(6, DateUtils.convert(user.getBirthDate())); // `Naissance`
                statement.setDate(7, DateUtils.convert(user.getInscriptionDate())); // `Ouverture_compte`
                statement.setDouble(8, user.getLatitude()); // `latitude`
                statement.setDouble(9, user.getLongitude()); // `longitude`
                statement.setString(10, user.getAddressDisplay()); // `address_display`
                statement.setString(11, user.getPostalCode()); // `code_postal`

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        user.setIdUtilisateur(id);
                    }
                }
                connection.commit();
                logger.info("{} a été inséré en base de données avec l'id {}", user.getFullName(), user.getIdUtilisateur());
            } catch (SQLIntegrityConstraintViolationException e) {
                connection.rollback();
                logger.info("Le mail {} est déjà renseigné dans la BDD, insertion impossible", user.getEmail());
                throw new UserAlreadyExistException("le mail existe déjà");
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de {}", user.getFullName(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    @Override
    public User getUserByStation(long idStation) {
        User user = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_USER_BY_STATION_ID);
            statement.setLong(1, idStation);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation d'un utilisateur en base de données", e);
        }
        return user;
    }


    private static final String REQ_FIND_USER_BY_ID =
            "SELECT * FROM obj_utilisateur u " +
                    "LEFT JOIN gamme_motif_fermeture_compte mfc ON u.Id_Motif_fermeture = mfc.Id_Motif_fermeture "+
                    "WHERE u.Id_Utilisateur = ?";
    @Override
    public User findUserById(long idUser) {
        logger.info("findUserById APPELE");
        User user = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_USER_BY_ID);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation d'un utilisateur en base de données", e);
        }
        return user;
    }


    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("Id_Utilisateur"),
                resultSet.getString("Email"),
                resultSet.getString("Mot_de_passe"),
                resultSet.getString("Prenom"),
                resultSet.getString("Nom"),
                Role.valueOf(resultSet.getString("role")),
                resultSet.getString("Telephone"),
                resultSet.getDate("Naissance").toLocalDate(),
                resultSet.getDate("Ouverture_compte").toLocalDate(),
                DateUtils.convert(resultSet.getDate("Fermeture_compte")),
                resultSet.getDouble("latitude"),
                resultSet.getDouble("longitude"),
                resultSet.getString("address_display"),
                resultSet.getString("code_postal"),
                new AccountClosingMotive(
                        resultSet.getLong("Id_Motif_fermeture"),
                        resultSet.getString("Libelle")
                )
        );
    }
}
