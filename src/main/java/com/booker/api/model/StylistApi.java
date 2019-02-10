package com.booker.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StylistApi {

    private Integer id;
    private String forename;
    private String surname;
    private String email;

    public StylistApi(final Integer id, final String forename, final String surname, final String email) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
    }

    public StylistApi() {
    }

    @Override
    public String toString() {
        return String.format("Stylist: %s, %s, %s.", forename, surname, email);
    }

}