package com.booker.service;

import com.booker.api.model.StylistApi;
import com.booker.model.Stylist;
import com.booker.repo.StylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.booker.utils.StylistTransformer.stylistApiToStylist;
import static com.booker.utils.StylistTransformer.stylistToStylistApi;

@Service
public class StylistService {

    private final StylistRepo stylistRepo;

    @Autowired
    public StylistService(StylistRepo stylistRepo) {
        this.stylistRepo = stylistRepo;
    }

    public StylistApi addStylist(final StylistApi stylistApi) {
        final Stylist createdStylist = this.stylistRepo.save(stylistApiToStylist(stylistApi));
        return stylistToStylistApi(createdStylist);
    }
}
