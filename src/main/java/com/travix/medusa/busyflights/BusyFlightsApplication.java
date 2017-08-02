package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.adapters.crazyair.CrazyAirAdapter;
import com.travix.medusa.busyflights.adapters.toughjet.ToughJetAdapter;
import com.travix.medusa.busyflights.services.busyflights.impl.BusyFlightsServiceImpl;
import com.travix.medusa.busyflights.services.busyflights.util.IBusyFlightsService;
import com.travix.medusa.busyflights.services.crazyair.HTTPCrazyAirService;
import com.travix.medusa.busyflights.services.toughjet.HTTPToughJetService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusyFlightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusyFlightsApplication.class, args);
    }

    @Bean
    IBusyFlightsService busyFlightsService() {
        IBusyFlightsService busyFlightsService = new BusyFlightsServiceImpl();
        busyFlightsService.addAdapter(new CrazyAirAdapter(new HTTPCrazyAirService()));
        busyFlightsService.addAdapter(new ToughJetAdapter(new HTTPToughJetService()));
        return busyFlightsService;
    }
}
