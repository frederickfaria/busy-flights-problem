package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * DTO class to return the services answer.
 *
 * Created by ffaria on 7/20/17.
 */
public class BusyFlightsControllerAnswer {

    private String message;

    private List<BusyFlightsResponse> busyFlightsResponses;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BusyFlightsResponse> getBusyFlightsResponses() {
        return busyFlightsResponses;
    }

    public void setBusyFlightsResponses(List<BusyFlightsResponse> busyFlightsResponses) {
        this.busyFlightsResponses = busyFlightsResponses;
    }
}
