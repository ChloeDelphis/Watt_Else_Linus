package fr.eql.ai116.linus.wattelse.entity.dto;

import fr.eql.ai116.linus.wattelse.entity.pojo.User;

import java.io.Serializable;
import java.time.LocalDate;

public class CreditCardDto implements Serializable {
    private Long idCreditCard;
    private String cardNumber;
    private LocalDate expiryDate;
    private String ccs;

    /// Constructeur vide
    public CreditCardDto() {
    }

    /// Constructeur surchargé
    public CreditCardDto(Long idCreditCard, String cardNumber, LocalDate expiryDate, String ccs) {
        this.idCreditCard = idCreditCard;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.ccs = ccs;
    }

    /// Getters
    public Long getIdCreditCard() {
        return idCreditCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getCcs() {
        return ccs;
    }


    /// Setters
    public void setIdCreditCard(Long idCreditCard) {
        this.idCreditCard = idCreditCard;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCcs(String ccs) {
        this.ccs = ccs;
    }
}
