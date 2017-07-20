package com.travix.medusa.busyflights.adapters.crazyair;

import com.travix.medusa.busyflights.adapters.busyflights.BusyFlightsAdapterInterface;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.services.crazyair.util.HTTPCrazyAirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffaria on 7/19/17.
 */
@Service
public class CrazyAirAdapter implements BusyFlightsAdapterInterface {

    @Autowired
    HTTPCrazyAirService httpCrazyAirService;

    @Override
    public List<BusyFlightsResponse> getResults(BusyFlightsRequest busyFlightsRequest) {
        return buildBusyFlightsResponse(httpCrazyAirService.getResponse(buildCrazyAirRequest(busyFlightsRequest)));
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

    private List<BusyFlightsResponse> buildBusyFlightsResponse(List<CrazyAirResponse> crazyAirResponses){
        List<BusyFlightsResponse> result = new ArrayList<>();

        for (CrazyAirResponse crazyAirResponse : crazyAirResponses) {
            BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
            busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
            busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
            busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
            busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
            busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
            busyFlightsResponse.setFare(crazyAirResponse.getPrice());
            busyFlightsResponse.setSupplier("CrazyAir");
            result.add(busyFlightsResponse);
        }

        return result;
    }
}
