package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.dao.exceptions.BankAccountAlreadyExistException;
import fr.eql.ai116.linus.wattelse.entity.pojo.BankAccount;

public interface BankAccountDao {
    void addBankAccount(BankAccount bankAccount) throws BankAccountAlreadyExistException;
    BankAccount fetchUsersBankAccount(Long id);
}
