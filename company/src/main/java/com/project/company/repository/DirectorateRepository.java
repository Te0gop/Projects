package com.project.company.repository;

import com.project.company.entity.Directorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorateRepository extends JpaRepository<Directorate, Long> {
}
