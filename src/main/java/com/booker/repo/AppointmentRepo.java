package com.booker.repo;

import com.booker.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.startTime = ?1")
    List<Appointment> getBookedAppointments(LocalDateTime starTime);

    @Query("SELECT a FROM Appointment a WHERE a.startTime >= ?1 AND a.startTime <= ?2")
    List<Appointment> getBookedAppointmentsInRange(LocalDateTime starTime, LocalDateTime endTime);
}