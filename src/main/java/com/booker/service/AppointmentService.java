package com.booker.service;

import com.booker.api.model.AppointmentApi;
import com.booker.api.model.AppointmentApiRequest;
import com.booker.api.model.AvailableAppoitmentResponse;
import com.booker.config.Config;
import com.booker.exception.InvalidAppointmentTimeException;
import com.booker.exception.NoFreeStylistsException;
import com.booker.model.Appointment;
import com.booker.model.AppointmentRange;
import com.booker.model.Stylist;
import com.booker.repo.AppointmentRepo;
import com.booker.repo.StylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.booker.model.AppointmentRange.END_WORK_HOUR;
import static com.booker.model.AppointmentRange.START_WORK_HOUR;
import static com.booker.model.AppointmentRange.isValidWorkingHours;
import static com.booker.utils.AppointmentTransformer.appointmentToAppointmentApi;
import static com.booker.utils.AppointmentTransformer.toStringFormat;

@Service
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final StylistRepo stylistRepo;
    private final Config config;
    private final AppointmentRange appointmentRange;

    @Autowired
    public AppointmentService(final AppointmentRepo appointmentRepo, final StylistRepo stylistRepo, final Config config) {
        this.appointmentRepo = appointmentRepo;
        this.stylistRepo = stylistRepo;
        this.config = config;
        this.appointmentRange = AppointmentRange.valueOf(config.getAppointmentTimeRange());
    }

    public AvailableAppoitmentResponse getAvailableAppointments(int days) throws Exception {
        if (days < 1) {
            days = 0;
        } else {
            days = days - 1;
        }

        LocalDateTime startPoint = LocalDateTime.now().withSecond(0).withNano(0);
        final LocalDateTime till = startPoint.plusDays(days).withHour(END_WORK_HOUR);
        final List<Appointment> bookedAppointmentsInRange = appointmentRepo.getBookedAppointmentsInRange(startPoint, till);
        final List<Stylist> allStylists = this.stylistRepo.findAll();
        final List<String> freeSlots = new ArrayList<>();

        if (allStylists.size() == 0) {
            throw new NoFreeStylistsException();
        }

        startPoint = appointmentRange.setToNextValidTime(startPoint);
        while (startPoint.compareTo(till) < 0) {
            while (isValidWorkingHours(startPoint)) {
                final List<Appointment> currentBookedAppointments = filterAppointments(bookedAppointmentsInRange, startPoint);
                if (currentBookedAppointments.size() >= allStylists.size()) {
                    startPoint = startPoint.plusMinutes(appointmentRange.getAppointmentLength());
                    continue;
                }
                freeSlots.add(toStringFormat(startPoint));
                startPoint = startPoint.plusMinutes(appointmentRange.getAppointmentLength());
            }
            startPoint = startPoint.plusDays(1).withHour(START_WORK_HOUR).withMinute(0);
        }
        return new AvailableAppoitmentResponse(freeSlots);
    }

    public AppointmentApi bookAppointment(final AppointmentApiRequest appointmentReq) throws Exception {
        final LocalDateTime startTime = appointmentReq.getStartTime();

        if (startTime == null || !appointmentRange.isValidTime(startTime) || LocalDateTime.now().compareTo(startTime) > 0) {
            throw new InvalidAppointmentTimeException();
        }
        final List<Appointment> bookedAppointments = this.appointmentRepo.getBookedAppointments(startTime);
        final List<Stylist> allStylists = this.stylistRepo.findAll();

        if (allStylists.size() == 0 || bookedAppointments.size() >= allStylists.size()) {
            throw new NoFreeStylistsException();
        }

        final List<Integer> busyStylists = bookedAppointments.stream().map(p -> p.getStylist().getId()).collect(Collectors.toList());
        final List<Stylist> freeStylists = allStylists.stream().filter(s -> !busyStylists.contains(s.getId())).collect(Collectors.toList());
        final Appointment appointment = new Appointment(freeStylists.get(0), startTime, appointmentReq.getCustomerId());
        final Appointment bookedAppointment = appointmentRepo.save(appointment);
        return appointmentToAppointmentApi(bookedAppointment);
    }

    private List<Appointment> filterAppointments(List<Appointment> bookedAppointments, final LocalDateTime timestamp) {
        return bookedAppointments
                .stream()
                .filter(p -> Objects.equals(p.getStartTime(), timestamp))
                .collect(Collectors.toList());
    }

}
