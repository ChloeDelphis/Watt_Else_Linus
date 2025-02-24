package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.FacturationDao;
import fr.eql.ai116.linus.wattelse.dao.UserDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.entity.pojo.Facturation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;

@Remote(FacturationDao.class)
@Stateless
public class FacturationDaoImpl implements FacturationDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    @EJB
    private UserDao userDao;

    private static final String REQ_FIND_LAST_FACTURATION_BY_USER_ID =
            "SELECT * FROM obj_facturation rp " +
            "WHERE rp.id_user = ? " +
            "ORDER BY rp.Date_reglement ASC " +
            "LIMIT 1";

    private static final String REQ_INSERT_FACTURATION =
            "INSERT INTO obj_facturation (id_user, Date_reglement, Montant) " +
            "VALUES (?, ?, ?);";

    private static final String REQ_UPDATE_FACTURATION =
            "UPDATE obj_facturation " +
            "SET Montant = ? " +
            "WHERE id_facturation = ?;";

    private static final String REQ_FIND_BANK_ACCOUNTS_BY_USERID =
            "SELECT * FROM obj_compte_bancaire " +
            "WHERE Id_Utilisateur = ?";

    @Override
    public Facturation getLastFacturation(long userId) {
        Facturation lastFacturation = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_LAST_FACTURATION_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lastFacturation = buildFacturation(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des vehicules en base de données", e);
        }
        return lastFacturation;
    }

    @Override
    public Facturation insertNewFacturation(Facturation facturation) {

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_FACTURATION, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, facturation.getIdUser()); //id_user
                statement.setDate(2, Date.valueOf(facturation.getPayementDate())); //Date_reglement
                statement.setDouble(3, facturation.getAmount()); //Montant

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        facturation.setIdMonthlyFacturation(id);
                    }
                }
                connection.commit();
                logger.info("la facturation du {} lié au compte bancaire d'id {} a été inséré en base de données avec l'id {}", facturation.getPayementDate(), facturation.getIdUser(), facturation.getIdMonthlyFacturation());
                return facturation;
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de la facturation d'id {}", facturation.getIdMonthlyFacturation(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }

        return null;
    }

    @Override
    public void updateFacturation(Facturation facturation) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_FACTURATION);
                statement.setDouble(1, facturation.getAmount()); //Montant
                statement.setLong(2, facturation.getIdMonthlyFacturation()); //id_user

                statement.executeUpdate();
                connection.commit();
                logger.info("la facturation d'id {} a été mise à jour avec le montant {}", facturation.getIdMonthlyFacturation(), facturation.getAmount());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de la mise à jour de la facturation d'id {}", facturation.getIdMonthlyFacturation(),  e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    private Facturation buildFacturation(ResultSet resultSet) throws SQLException {
        return new Facturation(
                resultSet.getLong("id_facturation"),
                resultSet.getLong("id_user"),
                resultSet.getDate("Date_reglement").toLocalDate(),
                resultSet.getBigDecimal("Montant").doubleValue()
        );
    }
}
