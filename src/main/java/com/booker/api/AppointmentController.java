package com.booker.api;

import com.booker.api.model.AppointmentApi;
import com.booker.api.model.AppointmentApiRequest;
import com.booker.api.model.AvailableAppoitmentResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public interface AppointmentController {

    @PostMapping(value = "/appointment",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Book an appointment with the stylist.", response = AppointmentApi.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK")
    ResponseEntity bookAppointment(@RequestBody AppointmentApiRequest appointmentApiRequest) throws Exception;

    @GetMapping(value = "/appointment/free",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "List free appointments slots.", response = AvailableAppoitmentResponse.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK")
    ResponseEntity<AvailableAppoitmentResponse> getAvailableAppointments(@RequestParam int days) throws Exception;
}
