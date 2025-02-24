package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.TarificationDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.pojo.Tarification;
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
import java.sql.Statement;

@Remote(TarificationDao.class)
@Stateless
public class TarificationDaoImpl implements TarificationDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_INSERT_FACTURATION =
            "INSERT INTO obj_tarification_borne (id_type_tarification, prix, date_de_demarrage_tarif, date_de_fin_tarif) " +
            "VALUES (?, ?, ?, ?);";

    private static final String REQ_UPDATE_FACTURATION =
            "UPDATE obj_tarification_borne " +
            "SET id_type_tarification = ? " +
            "prix = ? " +
            "date_de_fin_tarif = ? " +
            "WHERE id_tarification = ?;";

    private static final String REQ_FIND_TARIFICATION_BY_ID =
            "SELECT * FROM obj_tarification_borne otb " +
            "JOIN gamme_type_de_tarification gtt ON otb.id_type_tarification = gtt.id_type_tarification " +
            "WHERE id_tarification = ?";

    @Override
    public Tarification getTarification(long tarificationId) {
        Tarification tarification = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_TARIFICATION_BY_ID);
            statement.setLong(1, tarificationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tarification = buildTarification(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation de la facturation en base de données", e);
        }
        return tarification;
    }

    @Override
    public Tarification insertTarification(Tarification tarification) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            System.out.println("insertTarification : tarification" + tarification);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_FACTURATION, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, tarification.getTypeTarification().getTypeTarificationId()); //id_type_tarification
                statement.setDouble(2, tarification.getCost()); //prix
                statement.setDate(3, DateUtils.convert(tarification.getDateTarificationStart())); //date_de_demarrage_tarif
                statement.setDate(4, DateUtils.convert(tarification.getDateTarificationEnd())); //date_de_fin_tarif
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        long id = resultSet.getLong(1);
                        tarification.setTarificationId(id);
                    }
                    connection.commit();
                    logger.info("la tarification a été inséré en base de données avec l'id {}", tarification.getTarificationId());
                    return tarification;
                }
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de la tarification d'id {}", tarification.getTarificationId(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }

        return null;
    }

    @Override
    public void updateTarification(Tarification tarification) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_FACTURATION);
                statement.setLong(1, tarification.getTarificationId()); //id_type_tarification
                statement.setDouble(2, tarification.getCost()); //prix
                statement.setDate(3, Date.valueOf(tarification.getDateTarificationEnd())); //date_de_fin_tarif
                statement.setLong(4, tarification.getTarificationId()); //id_tarification

                statement.executeUpdate();
                connection.commit();
                logger.info("la tarification a été mise à jour en base de données avec l'id {}", tarification.getTarificationId());
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de la mise à jour de la tarification d'id {}", tarification.getTarificationId(), e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }
    }

    private Tarification buildTarification(ResultSet resultSet) throws SQLException {
        TypeTarification typeTarification = new TypeTarification(
                resultSet.getLong("id_type_tarification"),
                resultSet.getString("libelle_type_tarification"),
                resultSet.getString("abreviation")
                );

        return new Tarification(
                resultSet.getLong("id_tarification"),
                typeTarification,
                resultSet.getDouble("prix"),
                DateUtils.convert(resultSet.getDate("date_de_demarrage_tarif")),
                DateUtils.convert(resultSet.getDate("date_de_fin_tarif"))
        );
    }
}
