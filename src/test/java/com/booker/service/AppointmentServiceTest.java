package com.booker.service;

import com.booker.api.model.AvailableAppoitmentResponse;
import com.booker.api.model.StylistApi;
import com.booker.exception.NoFreeStylistsException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static com.booker.model.AppointmentRange.MIN_30;
import static com.booker.utils.AppointmentTransformer.APPOINTMENT_RESPONSE_FORMAT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @LocalServerPort
    private int port;

    @Test
    void getAvailableAppointmentsWithoutStylist() {
        Response response = RestAssured.given()
                .port(port)
                .when().get("/appointment/free");

        assertThat("No valid stylist should return 400", response.getStatusCode(), is(HttpStatus.BAD_REQUEST.value()));
        assertThrows(NoFreeStylistsException.class, () -> appointmentService.getAvailableAppointments(1));
    }

    @Test
    void getAvailableAppointmentsForTwoDays() {
        Response stylistResponse = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new StylistApi(1, "Artur", "Bujnicki", "test@email.com"))
                .port(port)
                .when().post("/stylist");

        Response appointmentResponse = RestAssured.given()
                .port(port)
                .when().get("/appointment/free?days=2");
        final AvailableAppoitmentResponse respBody = appointmentResponse.getBody().as(AvailableAppoitmentResponse.class);

        assertThat("Should return 201 after stylist creation", stylistResponse.getStatusCode(), is(HttpStatus.CREATED.value()));
        assertThat(appointmentResponse.getStatusCode(), is(HttpStatus.OK.value()));
        assertThat(appointmentResponse.getStatusCode(), is(HttpStatus.OK.value()));

        for(String timestamp : respBody.getAvailableTimeSlots()) {
            final LocalDateTime parsed = LocalDateTime.parse(timestamp, APPOINTMENT_RESPONSE_FORMAT);
            assertThat(MIN_30.isValidTime(parsed), is(true));
        }
    }
}