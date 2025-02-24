package fr.eql.ai116.linus.wattelse.entity.pojo;

import fr.eql.ai116.linus.wattelse.entity.range.RatingType;

import java.io.Serializable;

public class Rating implements Serializable {

    private Long ratingId;
    private RatingType ratingType;
    private String comment;
    private Integer rate;

    /// Constructeur vide
    public Rating() {
    }

    ///  Constructeur surchargé
    public Rating(Long ratingId, RatingType ratingType, String comment, Integer rate) {
        this.ratingId = ratingId;
        this.ratingType = ratingType;
        this.comment = comment;
        this.rate = rate;
    }

    /// Getters
    public Long getRatingId() {
        return ratingId;
    }

    public RatingType getRatingType() {
        return ratingType;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRate() {
        return rate;
    }

    /// Setters

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public void setRatingType(RatingType ratingType) {
        this.ratingType = ratingType;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
