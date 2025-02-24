package fr.eql.ai116.linus.wattelse.business.impl.utils;

public class AddressUtils {
    public static String getPostalCode(String address_display) {
        String[] splitted = address_display.split(", ");
        return splitted[splitted.length - 2];
    }

    public static String getCity(String address_display) {
        String[] splitted = address_display.split(", ");
        return splitted[splitted.length - 6];
    }


    // Info extraction from cityId
    // option.value = city.Code_postal +"-"+ city.Nom_commune+"-"+city.coordonnees_gps;
    // "48.3976372242, 2.19700639301"
    public static String getZipCodeFromcityId (String cityId){
        String[] parts = cityId.split("-");
        return parts[0];
    }

    public static String getCityNameFromcityId (String cityId){
        String[] parts = cityId.split("-");
        return parts[1];
    }

    public static Double getCityLatFromcityId (String cityId){
        String[] parts = cityId.split("-");
        String latString = parts[2].trim();
        Double latDouble = Double.valueOf(latString);
        return latDouble;

    }

    public static Double getCityLongFromcityId (String cityId){
        String[] parts = cityId.split("-");
        String latString = parts[3].trim();
        Double longDouble = Double.valueOf(latString);
        return longDouble;
    }
}
