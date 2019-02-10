package com.booker.utils;

import com.booker.api.model.AppointmentApi;
import com.booker.model.Appointment;
import com.booker.model.Stylist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.booker.utils.StylistTransformer.stylistApiToStylist;
import static com.booker.utils.StylistTransformer.stylistToStylistApi;

public class AppointmentTransformer {

    public final static String APPOINTMENT_ID = "%s_%s";
    public final static DateTimeFormatter APPOINTMENT_ID_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");
    public final static DateTimeFormatter APPOINTMENT_RESPONSE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toStringFormat(final LocalDateTime timestamp) {
        return timestamp.format(APPOINTMENT_RESPONSE_FORMAT);
    }

    public static String createId(final Stylist stylist, final LocalDateTime startTime) {
        final String appointmentString = startTime.format(APPOINTMENT_ID_TIME_FORMAT);
        return String.format(APPOINTMENT_ID, appointmentString, stylist.getId());
    }

    public static Appointment appointmentApiToAppointment(final AppointmentApi appointmentApi) {
        return new Appointment(stylistApiToStylist(appointmentApi.getStylist()), appointmentApi.getStartTime(), appointmentApi.getCustomerId());
    }

    public static AppointmentApi appointmentToAppointmentApi(final Appointment appointment) {
        return new AppointmentApi(appointment.getId(), stylistToStylistApi(appointment.getStylist()), appointment.getStartTime(), appointment.getCustomerId());
    }
}
