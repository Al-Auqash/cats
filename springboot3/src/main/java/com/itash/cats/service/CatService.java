package com.itash.cats.service;

import com.itash.cats.dto.CatDTO;
import com.itash.cats.model.CatBreedsModel;
import com.itash.cats.model.CatOriginsModel;
import com.itash.cats.model.CatsModel;
import com.itash.cats.repository.CatBreedsRepository;
import com.itash.cats.repository.CatOriginsRepository;
import com.itash.cats.repository.CatsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatsRepository catsRepository;
    private final CatOriginsRepository catOriginsRepository;
    private final CatBreedsRepository catBreedsRepository;

    public CatService(CatsRepository catsRepository, CatOriginsRepository catOriginsRepository, CatBreedsRepository catBreedsRepository) {
        this.catsRepository = catsRepository;
        this.catOriginsRepository = catOriginsRepository;
        this.catBreedsRepository = catBreedsRepository;
    }

    public List<CatDTO> getAllCats() {
        List<CatsModel> catModels = new ArrayList<>();
        List<CatOriginsModel> catOriginsModels = new ArrayList<>();
        List<CatBreedsModel> catBreedsModels = new ArrayList<>();

        catsRepository.findAll().forEach(catModels::add);
        catOriginsRepository.findAll().forEach(catOriginsModels::add);
        catBreedsRepository.findAll().forEach(catBreedsModels::add);

        Map<Long, String> originNames = catOriginsModels.stream()
                .collect(Collectors.toMap(CatOriginsModel::getOriginId, CatOriginsModel::getOriginName));

        Map<Long, String> breedNames = catBreedsModels.stream()
                .collect(Collectors.toMap(CatBreedsModel::getCatId, CatBreedsModel::getBreedName));

        List<CatDTO> result = new ArrayList<>();

        for (CatsModel cat : catModels) {
            CatDTO catDTO = new CatDTO();
            catDTO.setCatId(cat.getCatId());
            catDTO.setName(cat.getName());
            catDTO.setAge(cat.getAge());
            catDTO.setColor(cat.getColor());
            catDTO.setOriginId(cat.getOriginId());
            catDTO.setBreedId(cat.getBreedId());
            catDTO.setOriginName(originNames.get(cat.getOriginId()));
            catDTO.setBreedName(breedNames.get(cat.getBreedId()));
            result.add(catDTO);
        }

        return result;
    }

    public CatDTO getCat(long id) {
        Optional<CatsModel> catModelOptional = catsRepository.findById(id);

        // Return an empty DTO or handle the case where the cat is not found as per your requirements
        if (!catModelOptional.isPresent()) {
            return new CatDTO();
        }

        CatsModel catModel = catModelOptional.get();
        CatDTO catDTO = new CatDTO();

        catDTO.setCatId(catsRepository.findById(id).get().getCatId());
        catDTO.setName(catsRepository.findById(id).get().getName());
        catDTO.setAge(catsRepository.findById(id).get().getAge());
        catDTO.setColor(catsRepository.findById(id).get().getColor());

        catOriginsRepository.findById(catModel.getOriginId()).ifPresent(origin -> {
            catDTO.setOriginName(origin.getOriginName());
        });

        catBreedsRepository.findById(catModel.getBreedId()).ifPresent(breed -> {
            catDTO.setBreedName(breed.getBreedName());
        });

        return catDTO;
    }
}
