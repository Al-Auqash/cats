package com.itash.cats.controller;

import com.itash.cats.repository.SpeciesRepository;
import java.util.Optional;

import com.itash.cats.model.SpeciesModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graphql")
public class SpeciesQueryResolver {

    @Autowired
    private SpeciesRepository repository;

    private static final List<SpeciesModel> DUMMY_SPECIES = new ArrayList<>();

    static {
        DUMMY_SPECIES.add(new SpeciesModel((long) 1, "Lion"));
        DUMMY_SPECIES.add(new SpeciesModel((long) 2, "Tiger"));
        DUMMY_SPECIES.add(new SpeciesModel((long) 3, "Cheetah"));
    }

    @QueryMapping(name = "findAllSpecies")
    public List<SpeciesModel> findAllSpecies() {
        System.out.println("findAllSpecies method called");

        return DUMMY_SPECIES;
    }

    @QueryMapping
    public Optional<SpeciesModel> findSpeciesById(@Argument Long id) {
        return repository.findById(id);
    }
}
