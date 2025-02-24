package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class BanMotive implements Serializable {
    private Long idBan;
    private String motive;

    /// Constructeur vide
    public BanMotive() {
    }

    /// Constructeur surchargé
    public BanMotive(Long idBan, String motive) {
        this.idBan = idBan;
        this.motive = motive;
    }

    /// Getters
    public Long getIdBan() {
        return idBan;
    }

    public String getMotive() {
        return motive;
    }

    /// Setters
    public void setIdBan(Long idBan) {
        this.idBan = idBan;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
