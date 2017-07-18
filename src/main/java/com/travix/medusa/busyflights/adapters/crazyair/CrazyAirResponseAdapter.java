package com.travix.medusa.busyflights.adapters.crazyair;

import com.travix.medusa.busyflights.adapters.commons.IBusyFlightsResponseAdapter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

/**
 * Created by ffaria on 7/18/17.
 */
public class CrazyAirResponseAdapter {


    BusyFlightsResponse busyFlightsResponse;

    public CrazyAirResponseAdapter(CrazyAirResponse crazyAirResponse) {
        busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
        busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
        busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
        busyFlightsResponse.setFare(crazyAirResponse.getPrice());
        busyFlightsResponse.setSupplier("CrazyAir");
    }

    public BusyFlightsResponse getBusyFlightsResponse() {
        return busyFlightsResponse;
    }
}
