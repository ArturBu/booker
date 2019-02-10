package com.booker.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class AppointmentApi {

    private String id;
    private StylistApi stylist;
    @ApiModelProperty(notes = "The only valid format is yyyy-MM-dd HH:mm", example = "2020-01-01 16:30")
    private LocalDateTime startTime;
    private String customerId;

    public AppointmentApi() {
    }

    public AppointmentApi(final String id, final StylistApi stylist, final LocalDateTime startTime, final String customerId) {
        this.id = id;
        this.stylist = stylist;
        this.startTime = startTime;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return String.format("Appointment: %s, %s, %s.", id, startTime, stylist);
    }
}
