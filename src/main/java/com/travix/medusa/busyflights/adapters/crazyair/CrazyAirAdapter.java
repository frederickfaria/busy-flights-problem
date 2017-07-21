package com.travix.medusa.busyflights.adapters.crazyair;

import com.travix.medusa.busyflights.adapters.commons.ExternalAdapterInterface;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.services.crazyair.HTTPCrazyAirService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter for Crazy Air service
 *
 * Created by ffaria on 7/20/17.
 */
public class CrazyAirAdapter implements ExternalAdapterInterface {

    private HTTPCrazyAirService httpCrazyAirService;

    public CrazyAirAdapter(HTTPCrazyAirService httpCrazyAirService) {
        this.httpCrazyAirService = httpCrazyAirService;
    }

    @Override
    public List<BusyFlightsResponse> getResults(BusyFlightsRequest busyFlightsRequest) {
        //Calling the external http service
        return httpCrazyAirService.getResponse(buildCrazyAirRequest(busyFlightsRequest)).stream().map(this::buildBusyFlightsResponse).collect(Collectors.toList());
    }

    private CrazyAirRequest buildCrazyAirRequest(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
        crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
        crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
        crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
        crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
        return crazyAirRequest;
    }

    private BusyFlightsResponse buildBusyFlightsResponse(CrazyAirResponse crazyAirResponse) {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
        busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
        busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
        busyFlightsResponse.setFare(crazyAirResponse.getPrice());
        busyFlightsResponse.setSupplier("CrazyAir");
        return busyFlightsResponse;
    }
}
