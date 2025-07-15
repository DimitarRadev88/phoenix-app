package com.drrad.phoenix_app.location.dao;

import com.drrad.phoenix_app.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
