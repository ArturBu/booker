package com.booker.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class AppointmentApiRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @ApiModelProperty(notes = "The only valid format is yyyy-MM-dd HH:mm", example = "2020-01-01 16:30")
    private LocalDateTime startTime;
    private String customerId;

    public AppointmentApiRequest() {
    }

    public AppointmentApiRequest(final LocalDateTime startTime, final String customerId) {
        this.startTime = startTime;
        this.customerId = customerId;
    }
}
