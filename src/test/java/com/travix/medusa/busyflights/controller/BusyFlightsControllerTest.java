package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.services.busyflights.util.IBusyFlightsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ffaria on 7/20/17.
 */
public class BusyFlightsControllerTest {

    @Mock
    private IBusyFlightsService busyFlightsService;

    @InjectMocks
    private BusyFlightsController busyFlightsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(busyFlightsController).build();
    }

    @Test
    public void controllerInvocationTest() throws Exception {

        List<BusyFlightsResponse> busyFlightsResponseList = new ArrayList<>();

        BusyFlightsResponse busyFlightsResponseCrazyAir = new BusyFlightsResponse();
        busyFlightsResponseCrazyAir.setDepartureAirportCode("PTY");
        busyFlightsResponseCrazyAir.setDestinationAirportCode("AMS");
        busyFlightsResponseCrazyAir.setDepartureDate("2017-12-03T10:07:00");
        busyFlightsResponseCrazyAir.setArrivalDate("2017-12-04T18:18:00");
        busyFlightsResponseCrazyAir.setAirline("Allegiant Air");
        busyFlightsResponseCrazyAir.setFare(321.95);
        busyFlightsResponseCrazyAir.setSupplier("CrazyAir");
        busyFlightsResponseList.add(busyFlightsResponseCrazyAir);

        BusyFlightsResponse busyFlightsResponseToughJet = new BusyFlightsResponse();
        busyFlightsResponseToughJet.setDepartureAirportCode("PTY");
        busyFlightsResponseToughJet.setDestinationAirportCode("AMS");
        busyFlightsResponseToughJet.setDepartureDate("2017-12-03T01:52:00");
        busyFlightsResponseToughJet.setArrivalDate("2017-12-04T23:15:00");
        busyFlightsResponseToughJet.setAirline("SkyWest Airlines");
        busyFlightsResponseToughJet.setFare(406.15);
        busyFlightsResponseToughJet.setSupplier("ToughJet");
        busyFlightsResponseList.add(busyFlightsResponseToughJet);

        when(busyFlightsService.searchFlights(any(BusyFlightsRequest.class))).thenReturn(busyFlightsResponseList);

        mockMvc.perform(get("/searchAirFare?origin=PTY&" +
                "destination=AMS&" +
                "departureDate=2017-12-03&" +
                "returnDate=2017-12-04&" +
                "numberOfPassengers=1"))
                .andDo(print())
                .andExpect(jsonPath("$.message", is("2 results found.")))
                .andExpect(status().isOk());
    }



}