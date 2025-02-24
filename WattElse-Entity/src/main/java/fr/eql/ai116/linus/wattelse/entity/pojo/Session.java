package fr.eql.ai116.linus.wattelse.entity.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Session implements Serializable {

    private  Long id;
    private  String token;
    private  Timestamp timestamp;
    private  Long userId;

    /// Constructeur vide
    public Session() {
    }

    ///  Constructeur surchargé
    public Session(Long id, String token, Timestamp timestamp, Long userId) {
        this.id = id;
        this.token = token;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    ///  Getters
    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Long getUserId() {
        return userId;
    }

    /// Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /// Méthodes

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                '}';
    }
}
