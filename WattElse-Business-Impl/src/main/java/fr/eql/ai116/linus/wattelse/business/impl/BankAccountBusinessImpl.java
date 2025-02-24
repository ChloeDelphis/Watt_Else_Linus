package fr.eql.ai116.linus.wattelse.business.impl;

import fr.eql.ai116.linus.wattelse.business.BankAccountBusiness;
import fr.eql.ai116.linus.wattelse.business.exceptions.BankAccountException;
import fr.eql.ai116.linus.wattelse.dao.BankAccountDao;
import fr.eql.ai116.linus.wattelse.dao.exceptions.BankAccountAlreadyExistException;
import fr.eql.ai116.linus.wattelse.entity.dto.BankAccountDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.BankAccount;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;

@Remote(BankAccountBusiness.class)
@Stateless
public class BankAccountBusinessImpl implements BankAccountBusiness {

    @EJB
    private BankAccountDao bankAccountDao;

    @Override
    public void addBankAccount(BankAccount bankAccount) throws BankAccountException {
        bankAccount.setRegisterDate(LocalDate.now());
        try {
            bankAccountDao.addBankAccount(bankAccount);
        } catch (BankAccountAlreadyExistException e) {
            throw new BankAccountException(e.getMessage());
        }
    }

    @Override
    public BankAccountDto fetchUsersBankAccount(Long userId) {
            BankAccount bankAccount = bankAccountDao.fetchUsersBankAccount(userId);
                String iban = bankAccount.getIban().toString();
                String maskedbic = bankAccount.getBic().toString().substring(0,1) + "xxx";
                    String maskedIBAN = iban.substring(0, 3) + "xxxxxx";

                    BankAccountDto bankAccountDto = new BankAccountDto(
                            maskedIBAN,
                            maskedbic
                    );

            return bankAccountDto ;
    }
}