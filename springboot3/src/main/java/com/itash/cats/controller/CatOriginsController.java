package com.itash.cats.controller;

import com.itash.cats.model.CatOriginsModel;
import com.itash.cats.repository.CatOriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/origins")
public class CatOriginsController {

    @Autowired
    private CatOriginsRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<CatOriginsModel>> getData() {
        try {
            List<CatOriginsModel> models = new ArrayList<>();
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
    public ResponseEntity<CatOriginsModel> getDataById(@PathVariable("id") long id) {
        try {
            // Optional<Object> models = Optional.of(repository.findById(id));
            Optional<CatOriginsModel> models = repository.findById(id);

            CatOriginsModel model = models.orElse(null);

            if (model == null) {
                // return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(models.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity<>(new CatOriginsNotFoundHandler("CatOrigins Not Found"),
            // HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCatOrigins(@RequestBody CatOriginsModel reqModel) {
        try {
            // CatOriginsModel model = repository.save(new CatOriginsModel(reqModel.getId(),
            // reqModel.getCatOrigins_name(), reqModel.getCatOrigins_quantity(),
            // reqModel.getCatOrigins_catOrigins(), reqModel.getCatOrigins_details(),
            // reqModel.getLast_update_by()));
            CatOriginsModel model = repository.save(reqModel);
            return new ResponseEntity<>(model, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCatOrigins(@PathVariable("id") long id, @RequestBody CatOriginsModel reqModel) {
        try {
            Optional<CatOriginsModel> models = repository.findById(id);
            if (!models.isEmpty()) {
                CatOriginsModel catOriginsModel = models.get();
                catOriginsModel.setOriginsName(reqModel.getOriginsName());
                catOriginsModel.setCharacteristics(reqModel.getCharacteristics());

                return new ResponseEntity<>(repository.save(catOriginsModel), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCatOrigins(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Data Has Been Deleted", HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>(new CatOriginsNotFoundHandler("CatOrigins Not Found"),
            // HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
