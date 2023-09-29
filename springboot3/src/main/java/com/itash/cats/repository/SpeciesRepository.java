package com.itash.cats.repository;

import com.itash.cats.model.SpeciesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<SpeciesModel, Long> {
} 