package fr.eql.ai116.linus.wattelse.dao;

import fr.eql.ai116.linus.wattelse.entity.range.Socket;

import java.util.List;

public interface DataBaseEnumDao {
    List<String[]> getWeekDays();
    List<Integer> getReservationHours();
    List<Integer> getReservationMinutes();
    List<String[]> getBanReasons();
    List<String[]> getStationClosingReasons();
    List<String[]> getAccountClosingReasons();
    List<String[]> getAnormalReservationEndingTypes();
    List<Socket> getSocketTypes();
    List<String[]> getTarificationTypes();
    List<String[]> getEvaluationTypes();
    List<String[]> getServicesTypes();
}
