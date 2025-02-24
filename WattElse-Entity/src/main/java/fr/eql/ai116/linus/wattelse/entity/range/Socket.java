package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class Socket implements Serializable {

    private Long socketId;
    private String socketLabel;

    /// Constructeur vide
    public Socket() {}

    /// Constructeur surchargé
    public Socket(Long socketId, String socketLabel) {
        this.socketId = socketId;
        this.socketLabel = socketLabel;
    }

    /// Getters
    public Long getSocketId() {
        return socketId;
    }

    public String getSocketLabel() {
        return socketLabel;
    }

    /// Setters
    public void setSocketId(Long socketId) {
        this.socketId = socketId;
    }

    public void setSocketLabel(String socketLabel) {
        this.socketLabel = socketLabel;
    }

    /// Méthodes
    @Override
    public String toString() {
        return "Socket{" +
                "socketId=" + socketId +
                ", socketLabel='" + socketLabel + '\'' +
                '}';
    }
}
