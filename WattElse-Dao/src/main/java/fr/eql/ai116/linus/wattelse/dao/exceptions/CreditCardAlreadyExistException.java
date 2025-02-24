package fr.eql.ai116.linus.wattelse.dao.exceptions;

public class CreditCardAlreadyExistException extends RuntimeException {
    public CreditCardAlreadyExistException(String message) {
        super(message);
    }
}
