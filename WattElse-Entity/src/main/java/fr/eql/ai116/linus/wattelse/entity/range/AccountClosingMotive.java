package fr.eql.ai116.linus.wattelse.entity.range;


import java.io.Serializable;

public class AccountClosingMotive implements Serializable {
    private Long idMotifFermeture;
    private String libelle;

    ///  Constructeur vide
     public AccountClosingMotive() {
     }

     ///  Constructeur surchargé
    public AccountClosingMotive(Long idMotifFermeture, String libelle) {
        this.idMotifFermeture = idMotifFermeture;
        this.libelle = libelle;
    }

    ///  Getters
    public Long getIdMotifFermeture() {
        return idMotifFermeture;
    }

    public String getLibelle() {
        return libelle;
    }

    ///  Setters

    public void setIdMotifFermeture(Long idMotifFermeture) {
        this.idMotifFermeture = idMotifFermeture;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


}
