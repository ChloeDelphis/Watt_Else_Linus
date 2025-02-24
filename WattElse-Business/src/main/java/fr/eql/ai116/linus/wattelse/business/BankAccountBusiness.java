package fr.eql.ai116.linus.wattelse.business;

import fr.eql.ai116.linus.wattelse.business.exceptions.BankAccountException;
import fr.eql.ai116.linus.wattelse.entity.dto.BankAccountDto;
import fr.eql.ai116.linus.wattelse.entity.pojo.BankAccount;


public interface BankAccountBusiness {
    void addBankAccount(BankAccount bankAccount) throws BankAccountException;
    BankAccountDto fetchUsersBankAccount(Long userId);

}
