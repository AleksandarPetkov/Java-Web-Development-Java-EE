package app.web.beans;

import app.domain.model.service.CatServiceModel;
import app.domain.model.views.AllCatsViewModel;
import app.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatAllBean {

    private List<AllCatsViewModel> catsViewModels;

    private CatService catService;

    private ModelMapper modelMapper;

    public CatAllBean() {
    }

    @Inject
    public CatAllBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.catsViewModels = this.catService.getAllCats().stream()
                .map(c -> this.modelMapper.map(c, AllCatsViewModel.class))
                .collect(Collectors.toList());
    }


    public List<AllCatsViewModel> getCatsViewModels() {
        return catsViewModels;
    }

    public void setCatsViewModels(List<AllCatsViewModel> catsViewModels) {
        this.catsViewModels = catsViewModels;
    }
}
