package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.CreditCardDao;
import fr.eql.ai116.linus.wattelse.dao.exceptions.CreditCardAlreadyExistException;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.pojo.CreditCard;
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
import java.util.ArrayList;
import java.util.List;

@Remote(CreditCardDao.class)
@Stateless
public class CreditCardDaoImpl implements CreditCardDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_INSERT_CREDITCARD = "INSERT INTO obj_carte_bancaire (" +
            "Id_Utilisateur, dateDAjout, numeroDeCarte, dateDeValidite, codeDeSecurite) " +
            "VALUES (?,?,?,?,?);";

    private static final String REQ_FIND_BY_OWNER = "SELECT id_Carte, dateDAjout, dateDeRetrait, " +
            "numeroDeCarte, dateDeValidite, codeDeSecurite " +
            " FROM obj_carte_bancaire " +
            "WHERE Id_Utilisateur = ? AND dateDeRetrait IS NULL " +
            "ORDER BY dateDAjout ; ";

    @Override
    public List<CreditCard> fetchUsersCreditCard(Long userId) {
        List<CreditCard> creditCardsListDto = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_OWNER);

            System.out.println(REQ_FIND_BY_OWNER);

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            System.out.println(resultSet);

            while (resultSet.next()) {
                creditCardsListDto.add(
                        new CreditCard(
                                resultSet.getLong("id_Carte"),
                                DateUtils.convert(resultSet.getDate("dateDAjout")),
                                DateUtils.convert(resultSet.getDate("dateDeRetrait")),
                                resultSet.getString("numeroDeCarte"),
                                DateUtils.convert(resultSet.getDate("dateDeValidite")),
                                resultSet.getInt("codeDeSecurite")
                        )
                );
                for (CreditCard creditCardDto : creditCardsListDto) {
                    System.out.println(creditCardDto.getIdCreditCard());

                }
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des cartes de crédit en base de données", e);
        }
        return creditCardsListDto;
    }

    @Override
    public void addCreditCard(CreditCard creditCardDto) throws CreditCardAlreadyExistException {


        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_CREDITCARD);
                statement.setLong(1, creditCardDto.getIdUser());
                statement.setDate(2, Date.valueOf(creditCardDto.getRegisterDate()));
                statement.setString(3, creditCardDto.getCardNumber());
                statement.setDate(4, Date.valueOf(creditCardDto.getExpiryDate()));
                statement.setLong(5, creditCardDto.getCcs());
                System.out.println(statement);

                statement.executeUpdate();
                connection.commit();
                logger.info("{} a été inséré en base de données", creditCardDto.getCardNumber());
            } catch (SQLIntegrityConstraintViolationException e) {
                connection.rollback();
                logger.info("La carte {} est déjà associé à l'utilisateur d'id {} dans la BDD, " +
                        "insertion impossible", creditCardDto.getCardNumber(), creditCardDto.getIdUser());
                throw new CreditCardAlreadyExistException("Cette carte est déjà associé à cet utilisateur");
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de l'insertion d'une carte bancaire en base de données", e);
        }

    }

    @Override
    public void modCreditCard(CreditCard CreditCardDto) {

    }

    @Override
    public void suppCreditCard(CreditCard CreditCardDto) {

    }
}
