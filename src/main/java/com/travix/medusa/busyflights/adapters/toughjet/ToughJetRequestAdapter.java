package com.travix.medusa.busyflights.adapters.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;

/**
 * Class used to transform BusyFlightsRequest object in a ToughJetRequest object
 * <p>
 * Created by ffaria on 7/18/17.
 */
public class ToughJetRequestAdapter {

    ToughJetRequest toughJetRequest;

    public ToughJetRequestAdapter(BusyFlightsRequest busyFlightsRequest) {
        this.toughJetRequest = new ToughJetRequest();
        this.toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        this.toughJetRequest.setTo(busyFlightsRequest.getDestination());
        this.toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
        this.toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
        this.toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
    }

    public ToughJetRequest getToughJetRequest() {
        return toughJetRequest;
    }
}
