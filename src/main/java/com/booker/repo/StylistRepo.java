package com.booker.repo;

import com.booker.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylistRepo extends JpaRepository<Stylist, Integer> {

}
