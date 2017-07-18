package com.travix.medusa.busyflights.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ffaria on 7/18/17.
 */
public class ProjectUtil {

    private static String[] AIRLINE_LIST = new String[]{
            "Alaska Airlines",
            "Allegiant Air",
            "American Airlines",
            "Delta Air Lines",
            "Frontier Airlines",
            "Hawaiian Airlines",
            "JetBlue",
            "Southwest Airlines",
            "Spirit Airlines",
            "United Airlines",
            "Virgin America",
            "Envoy Air",
            "ExpressJet",
            "SkyWest Airlines"
    };

    private static String[] CABIN_CLASS = new String[]{
            "First",
            "Business",
            "Economic"
    };

    public static String getRandomAirline(){
        return AIRLINE_LIST[ThreadLocalRandom.current().nextInt(AIRLINE_LIST.length)];
    }

    public static String getRandomCabinClass(){
        return CABIN_CLASS[ThreadLocalRandom.current().nextInt(CABIN_CLASS.length)];
    }


    public static String generateRandomTime(int min, int max) {
        StringBuffer time = new StringBuffer();

        // creating random time between min:00:00 and max:00:00
        int hour = ThreadLocalRandom.current().nextInt(min, max + 1);
        int minute = ThreadLocalRandom.current().nextInt(59 + 1);

        // hour
        if(hour < 10){
            time.append("0");
            time.append(hour);
        }
        else{
            time.append(hour);
        }

        time.append(":");

        // minute
        if(minute < 10){
            time.append("0");
            time.append(minute);
        }
        else{
            time.append(minute);
        }

        time.append(":");

        //second
        time.append("00");

        return time.toString();
    }

}
