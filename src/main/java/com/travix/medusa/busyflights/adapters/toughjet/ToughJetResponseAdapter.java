package com.travix.medusa.busyflights.adapters.toughjet;

import com.travix.medusa.busyflights.adapters.commons.IBusyFlightsResponseAdapter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ffaria on 7/18/17.
 */
public class ToughJetResponseAdapter {

    BusyFlightsResponse busyFlightsResponse;

    public ToughJetResponseAdapter(ToughJetResponse toughJetResponse) {
        busyFlightsResponse = new BusyFlightsResponse();
        busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime());
        busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime());
        busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
        double basePrice = toughJetResponse.getBasePrice();
        double discount = toughJetResponse.getDiscount();
        double tax = toughJetResponse.getTax();
        double total = Math.round((basePrice - discount) * (1.0 + (tax / 100.0)) * 100.0) / 100.0;
        busyFlightsResponse.setFare(total);
        busyFlightsResponse.setSupplier("ToughJet");
    }

    public BusyFlightsResponse getBusyFlightsResponse() {
        return busyFlightsResponse;
    }
}
