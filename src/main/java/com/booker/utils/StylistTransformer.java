package com.booker.utils;

import com.booker.api.model.StylistApi;
import com.booker.model.Stylist;

public class StylistTransformer {

    public static Stylist stylistApiToStylist(final StylistApi stylistApi) {
        return new Stylist(stylistApi.getForename(), stylistApi.getSurname(), stylistApi.getEmail());
    }

    public static StylistApi stylistToStylistApi(final Stylist stylist) {
        return new StylistApi(stylist.getId(), stylist.getForename(), stylist.getSurname(), stylist.getEmail());
    }
}
