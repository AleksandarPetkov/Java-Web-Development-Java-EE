package app.web.beans;

import app.domain.binding.CreateCatBindingModel;
import app.domain.model.service.CatServiceModel;
import app.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

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

    public void registerCat() throws IOException {
        CatServiceModel model = this.modelMapper.map(createCatBindingModel, CatServiceModel.class);
        this.catService.saveCat(model);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/");
    };

    public CreateCatBindingModel getCreateCatBindingModel() {
        return createCatBindingModel;
    }

    public void setCreateCatBindingModel(CreateCatBindingModel createCatBindingModel) {
        this.createCatBindingModel = createCatBindingModel;
    }
}
