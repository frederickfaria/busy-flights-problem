package com.travix.medusa.busyflights.services.busyflights.impl;

import com.travix.medusa.busyflights.adapters.commons.ExternalAdapterInterface;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.services.busyflights.util.IBusyFlightsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mail service for search the flights using the external services
 *
 * Created by ffaria on 7/18/17.
 */
@Service
public class BusyFlightsServiceImpl implements IBusyFlightsService {

    List<ExternalAdapterInterface> adapters = new ArrayList<>();

    @Override
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest) {

        // the result list
        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();

        // getting the result
        for (ExternalAdapterInterface commonsAdapter : this.adapters) {
            busyFlightsResponses.addAll(commonsAdapter.getResults(busyFlightsRequest));
        }

        // sorting the result by fare
        Collections.sort(busyFlightsResponses, (BusyFlightsResponse o1, BusyFlightsResponse o2) -> {
            if (o1.getFare() < o2.getFare()) return -1;
            if (o1.getFare() > o2.getFare()) return 1;
            return 0;
        });

        return busyFlightsResponses;
    }

    @Override
    public void addAdapter(ExternalAdapterInterface adapter) {
        this.adapters.add(adapter);
    }
}
