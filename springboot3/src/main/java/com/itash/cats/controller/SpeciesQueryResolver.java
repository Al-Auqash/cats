package com.itash.cats.controller;

import com.itash.cats.repository.SpeciesRepository;
import java.util.Optional;

import com.itash.cats.model.SpeciesModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graphql")
@CrossOrigin(origins = "http://localhost:5173")
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
        return repository.findAll();
    }

    @QueryMapping
    public Optional<SpeciesModel> findSpeciesById(@Argument Long id) {
        return repository.findById(id);
    }

    @MutationMapping(name = "createSpecies")
    public SpeciesModel createSpecies(@Argument String species, @Argument String speciesName) {
        SpeciesModel speciesModel = new SpeciesModel();
        speciesModel.setSpecies(species);
        speciesModel.setSpeciesName(speciesName);
        return repository.save(speciesModel);
    }

    @MutationMapping(name = "updateSpecies")
    public SpeciesModel updateSpecies(@Argument Long id, @Argument String species, @Argument String speciesName) {
        SpeciesModel speciesModel = repository.findById(id).orElseThrow(() -> new RuntimeException("Species not found"));
        speciesModel.setSpecies(species);
        speciesModel.setSpeciesName(speciesName);
        return repository.save(speciesModel);
    }

    @MutationMapping(name = "deleteSpecies")
    public boolean deleteSpecies(@Argument Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
