package com.booker.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppointmentTest {

    @Test
    public void idCreationTest() {
        Stylist stylist = new Stylist("Janusz", "Hund", "janusz@wp.pl");
        stylist.setId(333);
        Appointment appointment = new Appointment(stylist, LocalDateTime.of(2019,1,12,16,00), "");

        assertThat(appointment.getId(), is("2019-01-12_16:00_333"));
    }
}
