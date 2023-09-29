package com.itash.cats.controller;

import com.itash.cats.model.CatBreedsModel;
import com.itash.cats.model.CatOriginsModel;
import com.itash.cats.model.CatsModel;
import com.itash.cats.repository.CatOriginsRepository;
import com.itash.cats.repository.CatBreedsRepository;
import com.itash.cats.repository.CatsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cats")
public class CatsController {

    @Autowired
    private CatsRepository repository;

    @Autowired
    private CatOriginsRepository catOriginsRepository;

    @Autowired
    private CatBreedsRepository catBreedsRepository;

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getData() {
        try {
            List<CatsModel> catModels = new ArrayList<>();
            List<CatOriginsModel> catOriginsModels = new ArrayList<>();
            List<CatBreedsModel> catBreedsModels = new ArrayList<>();

            repository.findAll().forEach(catModels::add);
            catOriginsRepository.findAll().forEach(catOriginsModels::add);
            catBreedsRepository.findAll().forEach(catBreedsModels::add);

            // int size = Math.min(catModels.size(), catOriginsModels.size());
            // for (int i = 0; i < size; i++) {
            // combineCatDatas.add(new CatData(catModels.get(i), catOriginsModels.get(i)));
            // }

            // Create a map to quickly lookup the origin name by origin_id
            Map<Long, String> originNames = catOriginsModels.stream()
                    .collect(Collectors.toMap(CatOriginsModel::getCatId, CatOriginsModel::getOriginsName));

            // Create a map to quickly lookup the breed name by breed_id
            Map<Long, String> breedNames = catBreedsModels.stream()
                    .collect(Collectors.toMap(CatBreedsModel::getCatId, CatBreedsModel::getBreedName));

            // Prepare the result list
            List<Map<String, Object>> result = new ArrayList<>();

            for (CatsModel cat : catModels) {
                Map<String, Object> catData = new HashMap<>();
                catData.put("cat_id", cat.getCatId());
                catData.put("name", cat.getName());
                catData.put("age", cat.getAge());
                catData.put("color", cat.getColor());
                catData.put("origin_id", cat.getOriginId());
                catData.put("breed_id", cat.getBreedId());
                catData.put("origin_name", originNames.get(cat.getOriginId()));
                catData.put("breed_name", breedNames.get(cat.getBreedId()));
                result.add(catData);
            }

            if (catModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatsModel> getDataById(@PathVariable("id") long id) {
        try {
            // Optional<Object> models = Optional.of(repository.findById(id));
            Optional<CatsModel> models = repository.findById(id);

            CatsModel model = models.orElse(null);

            if (model == null) {
                // return new ResponseEntity<>("Data Not Found", HttpStatus.NOT_FOUND);
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(models.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity<>(new CatsNotFoundHandler("Cats Not Found"),
            // HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCats(@RequestBody CatsModel reqModel) {
        try {
            // CatsModel model = repository.save(new CatsModel(reqModel.getId(),
            // reqModel.getCats_name(), reqModel.getCats_quantity(),
            // reqModel.getCats_cats(), reqModel.getCats_details(),
            // reqModel.getLast_update_by()));
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

class CatData {
    private CatsModel catsModel;
    private CatOriginsModel catOriginsModel;

    public CatData() {

    }

    public CatData(CatsModel catsModel, CatOriginsModel catOriginsModel) {
        this.catsModel = catsModel;
        this.catOriginsModel = catOriginsModel;
    }

    public CatsModel getCatsModel() {
        return catsModel;
    }

    public void setCatsModel(CatsModel catsModel) {
        this.catsModel = catsModel;
    }

    public CatOriginsModel getCatOriginsModel() {
        return catOriginsModel;
    }

    public void setCatOriginsModel(CatOriginsModel catOriginsModel) {
        this.catOriginsModel = catOriginsModel;
    }

}

// interface CatData {
// CatsModel getCatsModel();
// CatOriginsModel getCatOriginsModel();
// }
