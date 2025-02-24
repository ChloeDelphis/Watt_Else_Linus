package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.EvaluationDao;
import fr.eql.ai116.linus.wattelse.dao.StationDao;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.entity.pojo.Facturation;
import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;
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

@Remote(EvaluationDao.class)
@Stateless
public class EvaluationDaoImpl implements EvaluationDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_FIND_AVG_GRADE_BY_STATION_ID =
            "SELECT AVG (e.note) " +
                    "FROM obj_evaluation AS e " +
                    "RIGHT JOIN obj_reservation AS r ON e.Id_Evaluation = r.Id_Evaluation " +
                    "WHERE r.id_borne = ?";

    private static final String REQ_ADD_RATING =
            "INSERT INTO obj_evaluation (Id_type_avis, commentaire_additionnel, note) " +
                    "VALUES (?, ?, ?); ";

    @Override
    public Double findAverageGradeByStationId(long stationId) {
        Double grade = null;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_AVG_GRADE_BY_STATION_ID);
            statement.setLong(1, stationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                grade = resultSet.getDouble("AVG (e.note)") ;
                logger.info("La note moyenne de la station à l'id "+ stationId+
                        " est de "+grade);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des notes en base de données", e);
        }

        return grade;
    }

    @Override
    public Long ratingReservation(Rating rating) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_ADD_RATING, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, rating.getRatingType().getIdRatingType());
                statement.setString(2, rating.getComment());
                statement.setLong(3, rating.getRate());
                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        Long ratingId = resultSet.getLong(1);
                        rating.setRatingId(ratingId);
                    }
                }
                connection.commit();
                logger.info("l'évaluation a été insérée en base de données avec l'id {}", rating.getRatingId());
                return rating.getRatingId();
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Une erreur s'est produite lors de l'insertion de l'évaluation", e);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la connexion avec la base de données", e);
        }

        return null;
    }

}
