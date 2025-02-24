package fr.eql.ai116.linus.wattelse.entity.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class Facturation implements Serializable {

    private Long idMonthlyFacturation;
    private Long idUser;
    private LocalDate payementDate;
    private Double amount;

    /// Constructeur vide
    public Facturation() {
    }

    /// Constructeur surchargé
    public Facturation(Long idMonthlyFacturation, Long idUser, LocalDate payementDate, Double amount) {
        this.idMonthlyFacturation = idMonthlyFacturation;
        this.idUser = idUser;
        this.payementDate = payementDate;
        this.amount = amount;
    }

    /// Getters
    public Long getIdMonthlyFacturation() {
        return idMonthlyFacturation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public LocalDate getPayementDate() {
        return payementDate;
    }

    public Double getAmount() {
        return amount;
    }

    /// Setters
    public void setIdMonthlyFacturation(Long idMonthlyFacturation) {
        this.idMonthlyFacturation = idMonthlyFacturation;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setPayementDate(LocalDate payementDate) {
        this.payementDate = payementDate;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "Facturation{" +
                "idMonthlyFacturation=" + idMonthlyFacturation +
                ", idUser=" + idUser +
                ", payementDate=" + payementDate +
                ", amount=" + amount +
                '}';
    }

    public void addToAmount(Double cost) {
        amount += cost;
    }
}
