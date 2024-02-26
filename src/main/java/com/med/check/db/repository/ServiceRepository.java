package com.med.check.db.repository;

import com.med.check.db.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("select distinct s from Service s where s.name=?1")
    Optional<Service> findByName(String name);
}
