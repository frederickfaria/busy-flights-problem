package com.travix.medusa.busyflights.adapters.commons;

/**
 * Created by ffaria on 7/18/17.
 */
public interface IBusyFlightsResponseAdapter {

    String getDepartureAirportCode();

    String getDestinationAirportCode();

    String getDepartureDate();

    String getArrivalDate();

    String getAirline();

    double getFare();

    String getSupplier();
}
