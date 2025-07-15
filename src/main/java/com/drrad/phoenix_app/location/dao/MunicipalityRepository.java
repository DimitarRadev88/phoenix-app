package com.drrad.phoenix_app.location.dao;

import com.drrad.phoenix_app.location.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
}
