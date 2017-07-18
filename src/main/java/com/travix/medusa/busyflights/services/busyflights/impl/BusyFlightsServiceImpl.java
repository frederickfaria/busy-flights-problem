package com.travix.medusa.busyflights.services.busyflights.impl;

import com.travix.medusa.busyflights.adapters.crazyair.CrazyAirRequestAdapter;
import com.travix.medusa.busyflights.adapters.crazyair.CrazyAirResponseAdapter;
import com.travix.medusa.busyflights.adapters.toughjet.ToughJetRequestAdapter;
import com.travix.medusa.busyflights.adapters.toughjet.ToughJetResponseAdapter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.services.busyflights.util.IBusyFlightsService;
import com.travix.medusa.busyflights.services.crazyair.util.HTTPCrazyAirService;
import com.travix.medusa.busyflights.services.toughjet.util.HTTPToughJetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ffaria on 7/18/17.
 */
@Service
public class BusyFlightsServiceImpl implements IBusyFlightsService{


    HTTPCrazyAirService httpCrazyAirService;
    HTTPToughJetService httpToughJetService;

    @Autowired
    public BusyFlightsServiceImpl(HTTPCrazyAirService httpCrazyAirService, HTTPToughJetService httpToughJetService) {
        this.httpCrazyAirService = httpCrazyAirService;
        this.httpToughJetService = httpToughJetService;
    }

    @Override
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {

        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();

        CrazyAirRequestAdapter crazyAirRequestAdapter = new CrazyAirRequestAdapter(busyFlightsRequest);

        ToughJetRequestAdapter toughJetRequestAdapter = new ToughJetRequestAdapter(busyFlightsRequest);

        List<CrazyAirResponse> crazyAirResult = this.httpCrazyAirService.getResponse(crazyAirRequestAdapter.getCrazyAirRequest());

        for (CrazyAirResponse crazyAirResponse : crazyAirResult) {

            CrazyAirResponseAdapter crazyAirResponseAdapter = new CrazyAirResponseAdapter(crazyAirResponse);

            busyFlightsResponses.add(crazyAirResponseAdapter.getBusyFlightsResponse());

        }

        List<ToughJetResponse> toughJetResult = this.httpToughJetService.getResponse(toughJetRequestAdapter.getToughJetRequest());

        for (ToughJetResponse toughJetResponse : toughJetResult) {

            ToughJetResponseAdapter toughJetResponseAdapter = new ToughJetResponseAdapter(toughJetResponse);

            busyFlightsResponses.add(toughJetResponseAdapter.getBusyFlightsResponse());
        }

        // sorting by fare
        Collections.sort(busyFlightsResponses, new Comparator<BusyFlightsResponse>() {
            @Override
            public int compare(BusyFlightsResponse o1, BusyFlightsResponse o2) {
                if (o1.getFare() < o2.getFare()) return -1;
                if (o1.getFare() > o2.getFare()) return 1;
                return 0;
            }
        });

        return busyFlightsResponses;
    }
}
