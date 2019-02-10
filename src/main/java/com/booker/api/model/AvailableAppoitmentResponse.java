package com.booker.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class AvailableAppoitmentResponse {

    @ApiModelProperty(allowableValues = "2020-01-01 16:30")
    private List<String> availableTimeSlots;

    public AvailableAppoitmentResponse() {
    }

    public AvailableAppoitmentResponse(final List<String> availableTimeSlots) {
        this.availableTimeSlots = availableTimeSlots;
    }
}
