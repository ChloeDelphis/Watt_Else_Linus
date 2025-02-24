package fr.eql.ai116.linus.wattelse.business.impl.utils;

public class DistanceUtils {

    // Renvoie la distance à vol d'oiseau entre deux points
    // Formule d'Haversine
    public static double calculateDistanceFromCityPointToStation(
            Double cityLat, Double cityLong, Double stationLat, Double stationLong) {

        // Rayon de la Terre en kilomètres
        final int R = 6371;

        // Convertir les coordonnées de la ville et de la station en radians
        double lat1 = Math.toRadians(cityLat);
        double lon1 = Math.toRadians(cityLong);
        double lat2 = Math.toRadians(stationLat);
        double lon2 = Math.toRadians(stationLong);

        // Différences entre les latitudes et longitudes
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Appliquer la formule de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculer la distance en kilomètres
        double distance = R * c;

        // Arrondi à 1 chiffre après la virgule
        return Math.round(distance * 10.0) / 10.0;
    }

}
