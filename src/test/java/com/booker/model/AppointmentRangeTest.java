package com.booker.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.booker.model.AppointmentRange.MIN_30;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AppointmentRangeTest {

    @Test
    public void invalidWorkingHoursTest() {
        final LocalDateTime dateTime = LocalDateTime.of(2019, 1, 1, 7, 23);
        assertThat(MIN_30.isValidTime(dateTime), is(false));
    }

    @Test
    public void invalidMinutesTimeTest() {
        final LocalDateTime dateTime = LocalDateTime.of(2019, 1, 1, 10, 23);
        assertThat(MIN_30.isValidTime(dateTime), is(false));
    }

    @Test
    public void validAppointmentTimeTest() {
        final LocalDateTime dateTime = LocalDateTime.of(2019, 1, 1, 10, 00);
        assertThat(MIN_30.isValidTime(dateTime), is(true));
    }
}
