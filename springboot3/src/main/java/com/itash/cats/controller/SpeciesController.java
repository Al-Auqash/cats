package com.itash.cats.controller;

import com.itash.cats.model.SpeciesModel;
import com.itash.cats.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    @Autowired
    private SpeciesRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<SpeciesModel>> getData() {
        try {
            List<SpeciesModel> models = new ArrayList<>();
            repository.findAll().forEach(models::add);

            if (models.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(models, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeciesModel> getDataById(@PathVariable("id") long id) {
        try {
            // Optional<Object> models = Optional.of(repository.findById(id));
            Optional<SpeciesModel> models = repository.findById(id);

            SpeciesModel model = models.orElse(null);

            if (model == null) {
                // return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(models.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity<>(new SpeciesNotFoundHandler("Species Not Found"),
            // HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createSpecies(@RequestBody SpeciesModel reqModel) {
        try {
            // SpeciesModel model = repository.save(new SpeciesModel(reqModel.getId(),
            // reqModel.getSpecies_name(), reqModel.getSpecies_quantity(),
            // reqModel.getSpecies_species(), reqModel.getSpecies_details(),
            // reqModel.getLast_update_by()));
            SpeciesModel model = repository.save(reqModel);
            return new ResponseEntity<>(model, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSpecies(@PathVariable("id") long id, @RequestBody SpeciesModel reqModel) {
        try {
            Optional<SpeciesModel> models = repository.findById(id);
            if (!models.isEmpty()) {
                SpeciesModel speciesModel = models.get();
                speciesModel.setSpecies(reqModel.getSpecies());

                return new ResponseEntity<>(repository.save(speciesModel), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpecies(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Data Has Been Deleted", HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>(new SpeciesNotFoundHandler("Species Not Found"),
            // HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
