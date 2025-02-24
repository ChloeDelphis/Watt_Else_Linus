package fr.eql.ai116.linus.wattelse.entity.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class CreditCard implements Serializable{

    private Long idCreditCard;
    private Long idUser;
    private LocalDate registerDate;
    private LocalDate unRegisterDate;
    private String cardNumber;
    private LocalDate expiryDate;
    private Integer ccs;

    /// Constructeur vide
    public CreditCard() {
    }

    /// Constructeur surchargé

    public CreditCard(Long idCreditCard, Long idUser, LocalDate registerDate, LocalDate unRegisterDate,
                      String cardNumber, LocalDate expiryDate, Integer ccs) {
        this.idCreditCard = idCreditCard;
        this.idUser = idUser;
        this.registerDate = registerDate;
        this.unRegisterDate = unRegisterDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.ccs = ccs;
    }

    public CreditCard(Long idUser, LocalDate registerDate, String cardNumber, LocalDate expiryDate, Integer ccs) {
        this.idUser = idUser;
        this.registerDate = registerDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.ccs = ccs;
    }

    public CreditCard(Long idCreditCard, LocalDate registerDate, LocalDate unRegisterDate, String cardNumber, LocalDate expiryDate, Integer ccs) {
        this.idCreditCard = idCreditCard;
        this.registerDate = registerDate;
        this.unRegisterDate = unRegisterDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.ccs = ccs;
    }

    /// Getters
    public Long getIdCreditCard() {
        return idCreditCard;
    }

    public Long getIdUser() {
        return idUser;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public LocalDate getUnRegisterDate() {
        return unRegisterDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Integer getCcs() {
        return ccs;
    }

    /// Setters
    public void setIdCreditCard(Long idCreditCard) {
        this.idCreditCard = idCreditCard;
    }

    public void setIdUser(Long idUser) {this.idUser = idUser;}

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public void setUnRegisterDate(LocalDate unRegisterDate) {
        this.unRegisterDate = unRegisterDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCcs(Integer ccs) {
        this.ccs = ccs;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "CreditCard{" +
                "idCreditCard=" + idCreditCard +
                ", idUser=" + idUser +
                ", registerDate=" + registerDate +
                ", unRegisterDate=" + unRegisterDate +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", ccs=" + ccs +
                '}';
    }
}
