package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.pojo.Rating;

public interface EvaluationDao {

    Double findAverageGradeByStationId (long stationId);
    Long ratingReservation(Rating rating);

}
