package app.web.beans;

import app.domain.binding.CreateCatBindingModel;
import app.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CatCreateBean {

    private CreateCatBindingModel createCatBindingModel;

    private CatService catService;

    private ModelMapper modelMapper;

    public CatCreateBean() {
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper) {
        this.createCatBindingModel = new CreateCatBindingModel();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public CreateCatBindingModel getCreateCatBindingModel() {
        return createCatBindingModel;
    }

    public void setCreateCatBindingModel(CreateCatBindingModel createCatBindingModel) {
        this.createCatBindingModel = createCatBindingModel;
    }
}
