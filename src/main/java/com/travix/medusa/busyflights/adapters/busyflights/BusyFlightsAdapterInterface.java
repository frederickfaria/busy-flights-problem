package com.travix.medusa.busyflights.adapters.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * Created by ffaria on 7/19/17.
 */
public interface BusyFlightsAdapterInterface {
    List<BusyFlightsResponse> getResults(BusyFlightsRequest busyFlightsRequest);
}
