package com.travix.medusa.busyflights.services.crazyair.impl;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.services.crazyair.util.HTTPCrazyAirService;
import com.travix.medusa.busyflights.util.ProjectUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ffaria on 7/18/17.
 */
@Service
public class CrazyAirServiceImpl implements HTTPCrazyAirService {

    @Override
    public List<CrazyAirResponse> getResponse(CrazyAirRequest crazyAirRequest){

        String origin = crazyAirRequest.getOrigin();
        String destination = crazyAirRequest.getDestination();
        LocalDate departureDate = LocalDate.parse(crazyAirRequest.getDepartureDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate returnDate = LocalDate.parse(crazyAirRequest.getReturnDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        int randomFlightAmount = ThreadLocalRandom.current().nextInt(3 + 1);

        List<CrazyAirResponse> result = new ArrayList<>();


        for (int i = 0; i < randomFlightAmount; i++) {

            CrazyAirResponse crazyAirResponse = new CrazyAirResponse();
            StringBuffer departureDateTime = new StringBuffer();
            StringBuffer returnDateTime = new StringBuffer();

            crazyAirResponse.setDepartureAirportCode(origin);
            crazyAirResponse.setDestinationAirportCode(destination);

            if(departureDate.equals(returnDate)){
                departureDateTime.append(departureDate.toString());
                departureDateTime.append("T");
                departureDateTime.append(ProjectUtil.generateRandomTime(0,12));

                crazyAirResponse.setDepartureDate(departureDateTime.toString());

                returnDateTime.append(returnDate.toString());
                returnDateTime.append("T");
                returnDateTime.append(ProjectUtil.generateRandomTime(13,23));

                crazyAirResponse.setArrivalDate(returnDateTime.toString());
            }
            else {
                departureDateTime.append(departureDate.toString());
                departureDateTime.append("T");
                departureDateTime.append(ProjectUtil.generateRandomTime(0,23));

                crazyAirResponse.setDepartureDate(departureDateTime.toString());

                returnDateTime.append(returnDate.toString());
                returnDateTime.append("T");
                returnDateTime.append(ProjectUtil.generateRandomTime(0,23));

                crazyAirResponse.setArrivalDate(returnDateTime.toString());
            }

            crazyAirResponse.setAirline(ProjectUtil.getRandomAirline());

            crazyAirResponse.setCabinclass(ProjectUtil.getRandomCabinClass());

            crazyAirResponse.setPrice(Math.round(ThreadLocalRandom.current().nextDouble(100.0,600.0) * 100.0) / 100.0);

            result.add(crazyAirResponse);

        }

        return result;
    }
}
