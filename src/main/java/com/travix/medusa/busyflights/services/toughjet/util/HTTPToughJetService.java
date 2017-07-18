package com.travix.medusa.busyflights.services.toughjet.util;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.List;

/**
 * Created by ffaria on 7/17/17.
 */
public interface HTTPToughJetService {
    List<ToughJetResponse> getResponse(ToughJetRequest toughJetRequest);
}
