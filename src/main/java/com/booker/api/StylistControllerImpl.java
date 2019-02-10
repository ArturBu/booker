package com.booker.api;

import com.booker.api.model.StylistApi;
import com.booker.service.StylistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Component
public class StylistControllerImpl implements StylistController {

    final private StylistService stylistService;

    @Autowired
    public StylistControllerImpl(final StylistService stylistService) {
        this.stylistService = stylistService;
    }

    @Override
    public ResponseEntity addStylist(@RequestBody StylistApi stylist) {
        final StylistApi createdStylist = stylistService.addStylist(stylist);
        log.info("Created new {}.", createdStylist);
        return new ResponseEntity(createdStylist, HttpStatus.CREATED);
    }
}