package com.booker.api;

import com.booker.api.model.AppointmentApi;
import com.booker.api.model.AppointmentApiRequest;
import com.booker.api.model.AvailableAppoitmentResponse;
import com.booker.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentControllerImpl implements AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentControllerImpl(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public ResponseEntity bookAppointment(AppointmentApiRequest appointmentApiRequest) throws Exception {
        final AppointmentApi appointmentApi = this.appointmentService.bookAppointment(appointmentApiRequest);
        log.info("Booked new appointment {}", appointmentApi);
        return new ResponseEntity(appointmentApi, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAvailableAppointments(final int days) throws Exception {
        final AvailableAppoitmentResponse availableAppointments = this.appointmentService.getAvailableAppointments(days);
        return new ResponseEntity(availableAppointments, HttpStatus.OK);
    }
}
