package com.travix.medusa.busyflights.services.crazyair.util;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.util.List;

/**
 * Created by ffaria on 7/17/17.
 */
public interface HTTPCrazyAirService {
    List<CrazyAirResponse> getResponse(CrazyAirRequest crazyAirRequest);
}
