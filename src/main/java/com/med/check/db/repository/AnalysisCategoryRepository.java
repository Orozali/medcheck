package com.med.check.db.repository;

import com.med.check.db.model.AnalysisCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalysisCategoryRepository extends JpaRepository<AnalysisCategory, Long> {
    Optional<AnalysisCategory> findByName(String name);
}
