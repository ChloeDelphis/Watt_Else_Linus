package fr.eql.ai116.linus.wattelse.dao.exceptions;

public class BankAccountAlreadyExistException extends Exception {
    public BankAccountAlreadyExistException(String message) {
        super(message);
    }
}
