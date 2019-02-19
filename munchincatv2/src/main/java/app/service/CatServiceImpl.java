package app.service;

import app.domain.entities.Cat;
import app.domain.model.service.CatServiceModel;
import app.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean saveCat(CatServiceModel catServiceModel) {
        try{
            Cat cat = this.modelMapper.map(catServiceModel, Cat.class);
            this.catRepository.save(cat);
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public List<CatServiceModel> getAllCats() {
        List<Cat> cats = this.catRepository.findAll();
        List<CatServiceModel> catServiceModels = new ArrayList<>();
        for (Cat cat : cats) {
            catServiceModels.add(this.modelMapper.map(cat, CatServiceModel.class));
        }
        return catServiceModels;
    }
}
