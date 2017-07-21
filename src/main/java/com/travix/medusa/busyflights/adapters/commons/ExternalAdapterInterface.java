package com.travix.medusa.busyflights.adapters.commons;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * Contract interface for external services
 *
 * Created by ffaria on 7/20/17.
 */
public interface ExternalAdapterInterface {
    List<BusyFlightsResponse> getResults(BusyFlightsRequest busyFlightsRequest);
}
