package fr.eql.ai116.linus.wattelse.entity.dto;

import java.io.Serializable;

public class AuthenticateInDto implements Serializable {

    private String email;
    private String password;

    /// Constructeur vide (par défaut ici) nécessaire pour la réification ///

    /// Getters ///
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    /// Setters (pour réifier) ///
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticateInDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
