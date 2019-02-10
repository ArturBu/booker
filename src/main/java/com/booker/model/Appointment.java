package com.booker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static com.booker.utils.AppointmentTransformer.createId;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    private Stylist stylist;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "customer_id")
    private String customerId;

    public Appointment() {
    }

    public Appointment(final Stylist stylist, final LocalDateTime startTime, final String customerId) {
        this.id = createId(stylist, startTime);
        this.stylist = stylist;
        this.startTime = startTime;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return String.format("[Appointment: %s, %s, %s, %s.]", id, stylist, startTime, customerId);
    }
}

