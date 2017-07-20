package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.adapters.crazyair.CrazyAirAdapter;
import com.travix.medusa.busyflights.adapters.toughjet.ToughJetAdapter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.services.busyflights.util.IBusyFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by ffaria on 7/18/17.
 */
@Controller
public class BusyFlightsController {

    @Autowired
    IBusyFlightsService busyFlightsService;

    @RequestMapping(value = "searchAirFare", method = RequestMethod.GET)
    public
    @ResponseBody
    List<BusyFlightsResponse> searchAirFare(@RequestParam Map<String, String> requestParams) throws Exception {

        // getting input data from HTTP request
        String origin = requestParams.get("origin");
        String destination = requestParams.get("destination");
        String departureDate = requestParams.get("departureDate");
        String returnDate = requestParams.get("returnDate");
        int numberOfPassengers = Integer.valueOf(requestParams.get("numberOfPassengers"));

        // creating BusyFlightsRequest object to be processed by BusyFlightsService
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setOrigin(origin);
        busyFlightsRequest.setDestination(destination);
        busyFlightsRequest.setDepartureDate(departureDate);
        busyFlightsRequest.setReturnDate(returnDate);
        busyFlightsRequest.setNumberOfPassengers(numberOfPassengers);

        busyFlightsService.addAdapter(new CrazyAirAdapter());
        busyFlightsService.addAdapter(new ToughJetAdapter());

        List<BusyFlightsResponse> searchResult = busyFlightsService.searchFlights(busyFlightsRequest);

        return searchResult;
    }
}
