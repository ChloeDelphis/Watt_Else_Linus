package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.BanMotive;

import java.io.Serializable;
import java.time.LocalDate;

public class Ban implements Serializable {

    private Long idBan;
    private LocalDate startDate;
    private LocalDate endDate;
    private String comment;
    private Long bannedUserId;
    private BanMotive banMotive;

    /// Constructeur vide
    public Ban() {
    }

    /// Constructeur surchargé
    public Ban(Long idBan, LocalDate startDate, LocalDate endDate,
               String comment, Long bannedUserId, BanMotive banMotive) {
        this.idBan = idBan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comment = comment;
        this.bannedUserId = bannedUserId;
        this.banMotive = banMotive;
    }

    /// Getters
    public Long getIdBan() {
        return idBan;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getComment() {
        return comment;
    }

    public Long getBannedUserId() {
        return bannedUserId;
    }

    public BanMotive getBanMotive() {
        return banMotive;
    }

    /// Setters
    public void setIdBan(Long idBan) {
        this.idBan = idBan;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBannedUserId(Long bannedUserId) {
        this.bannedUserId = bannedUserId;
    }

    public void setBanMotive(BanMotive banMotive) {
        this.banMotive = banMotive;
    }
}
