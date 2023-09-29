package com.itash.cats.repository;

import com.itash.cats.model.CatsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatsRepository extends JpaRepository<CatsModel, Long> {
} 