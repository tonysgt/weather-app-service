package org.tonysgt.utils;

public class UnitUtils {

    public static String getTempUnits(String units){
        return switch (units) {
            case "metric" -> "Celsius";
            case "imperial" -> "fahrenheit";
            default -> "kelvin";
        };
    }


    public static String getSpeedUnits(String units){
        return switch (units) {
            case "imperial" -> "miles/hour";
            default -> "meter/sec";
        };
    }

}
