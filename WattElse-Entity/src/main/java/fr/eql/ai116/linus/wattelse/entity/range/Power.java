package fr.eql.ai116.linus.wattelse.entity.range;

import java.io.Serializable;

public class Power implements Serializable {
    private Long powerId;
    private Float value;

    /// Constructeur vide
    public Power() {}

    /// Constructeur surchargé
    public Power(Long powerId, Float value) {
        this.powerId = powerId;
        this.value = value;
    }


    /// Getters
    public Long getPowerId() {
        return powerId;
    }

    public Float getValue() {
        return value;
    }

    /// Setters
    public void setValue(Float value) {
        this.value = value;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    @Override
    public String toString() {
        return "Power{" +
                "powerId=" + powerId +
                ", value=" + value +
                '}';
    }
}
