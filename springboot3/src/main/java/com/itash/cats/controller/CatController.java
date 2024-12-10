package com.itash.cats.controller;

import com.itash.cats.dto.CatDTO;
import com.itash.cats.model.CatsModel;
import com.itash.cats.repository.CatsRepository;
import com.itash.cats.service.CatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cats")
public class CatController {

    @Autowired
    private CatsRepository repository;

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @SuppressWarnings("null")
    @GetMapping("/")
    public ResponseEntity<List<CatDTO>> getData() {
        try {
            List<CatDTO> catData = catService.getAllCats();

            if (catData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(catData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatDTO> getDataById(@PathVariable("id") long id) {
        try {
            Optional<CatDTO> catData = Optional.ofNullable(catService.getCat(id));

            if (catData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(catData.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCats(@RequestBody CatsModel reqModel) {
        try {
            CatsModel model = repository.save(reqModel);
            return new ResponseEntity<>(model, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCats(@PathVariable("id") long id, @RequestBody CatsModel reqModel) {
        try {
            Optional<CatsModel> models = repository.findById(id);
            if (!models.isEmpty()) {
                CatsModel catsModel = models.get();
                catsModel.setCatId(reqModel.getCatId());
                catsModel.setName(reqModel.getName());
                catsModel.setAge(reqModel.getAge());
                catsModel.setColor(reqModel.getColor());

                return new ResponseEntity<>(repository.save(catsModel), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCats(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Data Has Been Deleted", HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>(new CatsNotFoundHandler("Cats Not Found"),
            // HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
        }
    }
}

