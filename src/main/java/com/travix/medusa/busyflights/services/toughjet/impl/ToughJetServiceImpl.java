package com.travix.medusa.busyflights.services.toughjet.impl;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.services.toughjet.util.HTTPToughJetService;
import com.travix.medusa.busyflights.util.ProjectUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates a Tough Jet response as a external service.
 * <p>
 * Created by ffaria on 7/18/17.
 */
@Service
public class ToughJetServiceImpl implements HTTPToughJetService {

    @Override
    public List<ToughJetResponse> getResponse(ToughJetRequest toughJetRequest) {
        String from = toughJetRequest.getFrom();
        String to = toughJetRequest.getTo();
        LocalDate outboundDate = LocalDate.parse(toughJetRequest.getOutboundDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate inboundDate = LocalDate.parse(toughJetRequest.getInboundDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        int randomFlightAmount = ThreadLocalRandom.current().nextInt(5 + 1);

        List<ToughJetResponse> result = new ArrayList<>();

        for (int i = 0; i < randomFlightAmount; i++) {

            ToughJetResponse toughJetResponse = new ToughJetResponse();
            StringBuffer outboundDateTime = new StringBuffer();
            StringBuffer inboundDateTime = new StringBuffer();

            toughJetResponse.setDepartureAirportName(from);
            toughJetResponse.setArrivalAirportName(to);

            if (outboundDate.equals(inboundDate)) {
                outboundDateTime.append(outboundDate.toString());
                outboundDateTime.append("T");
                outboundDateTime.append(ProjectUtil.generateRandomTime(0, 12));

                toughJetResponse.setOutboundDateTime(outboundDateTime.toString());

                inboundDateTime.append(inboundDate.toString());
                inboundDateTime.append("T");
                inboundDateTime.append(ProjectUtil.generateRandomTime(13, 23));

                toughJetResponse.setInboundDateTime(inboundDateTime.toString());
            } else {
                outboundDateTime.append(outboundDate.toString());
                outboundDateTime.append("T");
                outboundDateTime.append(ProjectUtil.generateRandomTime(0, 23));

                toughJetResponse.setOutboundDateTime(outboundDate.toString());

                inboundDateTime.append(inboundDate.toString());
                inboundDateTime.append("T");
                inboundDateTime.append(ProjectUtil.generateRandomTime(0, 23));

                toughJetResponse.setInboundDateTime(inboundDate.toString());
            }

            toughJetResponse.setCarrier(ProjectUtil.getRandomAirline());

            toughJetResponse.setBasePrice(Math.round(ThreadLocalRandom.current().nextDouble(100.0, 600.0) * 100.0) / 100.0);

            toughJetResponse.setDiscount(Math.round(ThreadLocalRandom.current().nextDouble(0.0, 10.0) * 100.0) / 100.0);

            toughJetResponse.setTax(7.0);

            result.add(toughJetResponse);
        }

        return result;
    }

}
