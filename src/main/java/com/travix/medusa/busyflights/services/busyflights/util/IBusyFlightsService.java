package com.travix.medusa.busyflights.services.busyflights.util;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * Created by ffaria on 7/17/17.
 */
public interface IBusyFlightsService {

    List<BusyFlightsResponse> searchFlights(BusyFlightsRequest busyFlightsRequest);

}
