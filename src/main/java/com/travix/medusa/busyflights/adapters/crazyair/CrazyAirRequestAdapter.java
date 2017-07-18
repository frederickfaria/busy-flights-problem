package com.travix.medusa.busyflights.adapters.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;

/**
 * Class used to transform a BusyFlightsRequest object to CrazyAirRequest
 * <p>
 * Created by ffaria on 7/18/17.
 */
public class CrazyAirRequestAdapter {

    private CrazyAirRequest crazyAirRequest;

    public CrazyAirRequestAdapter(BusyFlightsRequest busyFlightsRequest) {
        this.crazyAirRequest = new CrazyAirRequest();
        this.crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
        this.crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
        this.crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
        this.crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
        this.crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
    }

    public CrazyAirRequest getCrazyAirRequest() {
        return this.crazyAirRequest;
    }
}
