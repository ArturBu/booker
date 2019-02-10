package com.booker.api;

import com.booker.api.model.StylistApi;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public interface StylistController {

    @PostMapping(value = "/stylist", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Creates new stylist.", response = StylistApi.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK")
    ResponseEntity addStylist(@RequestBody StylistApi stylist);
}