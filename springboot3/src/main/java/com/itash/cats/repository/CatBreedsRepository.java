package com.itash.cats.repository;

import com.itash.cats.model.CatBreedsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatBreedsRepository extends JpaRepository<CatBreedsModel, Long> {
} 