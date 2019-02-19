package app.service;

import app.domain.model.service.CatServiceModel;

import java.util.List;

public interface CatService {

    boolean saveCat(CatServiceModel catServiceModel);

    List<CatServiceModel> getAllCats();
}
