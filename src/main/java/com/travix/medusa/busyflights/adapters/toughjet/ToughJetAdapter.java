package com.travix.medusa.busyflights.adapters.toughjet;

import com.travix.medusa.busyflights.adapters.busyflights.BusyFlightsAdapterInterface;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.services.toughjet.util.HTTPToughJetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffaria on 7/19/17.
 */
@Service
public class ToughJetAdapter implements BusyFlightsAdapterInterface {

    @Autowired
    HTTPToughJetService httpToughJetService;

    @Override
    public List<BusyFlightsResponse> getResults(BusyFlightsRequest busyFlightsRequest) {
        return buildBusyFlightsResponse(this.httpToughJetService.getResponse(buildToughJetRequest(busyFlightsRequest)));
    }

    private ToughJetRequest buildToughJetRequest(BusyFlightsRequest busyFlightsRequest){
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        toughJetRequest.setTo(busyFlightsRequest.getDestination());
        toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
        toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
        toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
        return toughJetRequest;
    }

    private List<BusyFlightsResponse> buildBusyFlightsResponse(List<ToughJetResponse> toughJetResponses){
        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();
        for (ToughJetResponse toughJetResponse : toughJetResponses) {
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
            busyFlightsResponses.add(busyFlightsResponse);
        }
        return busyFlightsResponses;
    }
}
