package com.travix.medusa.busyflights.services.busyflights.impl;

import com.travix.medusa.busyflights.adapters.crazyair.CrazyAirAdapter;
import com.travix.medusa.busyflights.adapters.toughjet.ToughJetAdapter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.services.crazyair.HTTPCrazyAirService;
import com.travix.medusa.busyflights.services.toughjet.HTTPToughJetService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ffaria on 7/20/17.
 */
public class BusyFlightsServiceImplTest {

    @Test
    public void searchFlights() throws Exception {

        BusyFlightsServiceImpl busyFlightsService = new BusyFlightsServiceImpl();
        busyFlightsService.addAdapter(new CrazyAirAdapter(new HTTPCrazyAirService()));
        busyFlightsService.addAdapter(new ToughJetAdapter(new HTTPToughJetService()));

        String origin = "PTY";
        String destination = "AMS";
        String departureDate = "2017-12-03";
        String returnDate = "2017-12-04";
        int numberOfPassengers = 1;

        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin(origin);
        busyFlightsRequest.setDestination(destination);
        busyFlightsRequest.setDepartureDate(departureDate);
        busyFlightsRequest.setReturnDate(returnDate);
        busyFlightsRequest.setNumberOfPassengers(numberOfPassengers);

        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();

        while(busyFlightsResponses.size() == 0){
            busyFlightsResponses.addAll(busyFlightsService.searchFlights(busyFlightsRequest));
        }

        double lastFare = 0.0;

        for (BusyFlightsResponse busyFlightsResponse : busyFlightsResponses) {
            assertEquals(origin, busyFlightsResponse.getDepartureAirportCode());
            assertEquals(destination, busyFlightsResponse.getDestinationAirportCode());
            assertTrue(StringUtils.contains(busyFlightsResponse.getDepartureDate(), departureDate + "T"));
            assertTrue(StringUtils.contains(busyFlightsResponse.getArrivalDate(),returnDate + "T"));
            assertNotNull(busyFlightsResponse.getSupplier());
            assertTrue(busyFlightsResponse.getFare() > lastFare);
            lastFare = busyFlightsResponse.getFare();
        }
    }

}