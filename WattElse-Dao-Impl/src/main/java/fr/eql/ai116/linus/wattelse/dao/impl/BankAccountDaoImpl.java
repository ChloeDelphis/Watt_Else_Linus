package fr.eql.ai116.linus.wattelse.dao.impl;

import fr.eql.ai116.linus.wattelse.dao.BankAccountDao;
import fr.eql.ai116.linus.wattelse.dao.exceptions.BankAccountAlreadyExistException;
import fr.eql.ai116.linus.wattelse.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.linus.wattelse.dao.impl.utils.DateUtils;
import fr.eql.ai116.linus.wattelse.entity.pojo.BankAccount;
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
import java.time.LocalDate;

@Remote(BankAccountDao.class)
@Stateless
public class BankAccountDaoImpl implements BankAccountDao {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new WattElseDataSource();

    private static final String REQ_INSERT_BANKACCOUNT = "INSERT INTO obj_compte_bancaire (" +
            "Id_Utilisateur, dateDAjout, iban, bic) " +
            "VALUES (?,?,?,?);";
    private static final String REQ_FIND_BY_OWNER = "SELECT iban, bic " +
            "FROM obj_compte_bancaire " +
            "WHERE Id_Utilisateur = ? AND dateDeRetrait IS NULL; ";

    @Override
    public void addBankAccount(BankAccount bankAccount) throws BankAccountAlreadyExistException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement(REQ_INSERT_BANKACCOUNT);
                statement.setLong(1, bankAccount.getUserId());
                statement.setDate(2, Date.valueOf(bankAccount.getRegisterDate()));
                statement.setString(3, bankAccount.getIban());
                statement.setString(4, bankAccount.getBic());

                statement.executeUpdate();
                connection.commit();

            } catch (SQLIntegrityConstraintViolationException e) {
                connection.rollback();
                logger.info("Le compte {} est déjà associé à l'utilisateur d'id {} dans la BDD, " +
                        "insertion impossible", bankAccount.getIban(), bankAccount.getUserId());
                throw new BankAccountAlreadyExistException("Cette carte est déjà associé à cet utilisateur");
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de l'insertion d'un compte bancaire en base de données", e);
        }
    }

    @Override
    public BankAccount fetchUsersBankAccount(Long id) {
        System.out.println("dans le dao chef");

        BankAccount bankAccount = new BankAccount();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_OWNER);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bankAccount.setIban(resultSet.getString("iban"));
                bankAccount.setBic(resultSet.getString("bic"));
            } else {
                // Si aucun résultat n'est trouvé
                System.out.println("Aucun compte bancaire trouvé pour l'utilisateur.");
            }

            System.out.println(bankAccount);
            return bankAccount;

        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des cartes de crédit en base de données", e);
        }
        return bankAccount;
    }
}
