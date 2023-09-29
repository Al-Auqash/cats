package com.itash.cats.repository;

import com.itash.cats.model.CatOriginsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatOriginsRepository extends JpaRepository<CatOriginsModel, Long> {
} 