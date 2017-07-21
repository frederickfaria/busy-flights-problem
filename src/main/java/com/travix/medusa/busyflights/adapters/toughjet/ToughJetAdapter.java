package com.travix.medusa.busyflights.adapters.toughjet;

import com.travix.medusa.busyflights.adapters.commons.ExternalAdapterInterface;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.services.toughjet.HTTPToughJetService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter for tough jet service
 *
 * Created by ffaria on 7/20/17.
 */
public class ToughJetAdapter implements ExternalAdapterInterface {

    private HTTPToughJetService httpToughJetService;

    public ToughJetAdapter(HTTPToughJetService httpToughJetService) {
        this.httpToughJetService = httpToughJetService;
    }

    @Override
    public List<BusyFlightsResponse> getResults(BusyFlightsRequest busyFlightsRequest) {
        //Calling the external http service
        return this.httpToughJetService.getResponse(buildToughJetRequest(busyFlightsRequest)).stream().map(this::buildBusyFlightsResponse).collect(Collectors.toList());
    }

    private ToughJetRequest buildToughJetRequest(BusyFlightsRequest busyFlightsRequest) {
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        toughJetRequest.setTo(busyFlightsRequest.getDestination());
        toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
        toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
        toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
        return toughJetRequest;
    }

    private BusyFlightsResponse buildBusyFlightsResponse(ToughJetResponse toughJetResponse) {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
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
        return busyFlightsResponse;
    }
}
