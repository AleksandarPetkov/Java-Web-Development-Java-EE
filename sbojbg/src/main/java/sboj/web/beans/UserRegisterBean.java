package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.binding.UserRegisterBindingModel;
import sboj.domain.model.UserServiceModel;
import sboj.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private  UserRegisterBindingModel registerBindingModel;

    private UserService userService;

    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.registerBindingModel = new UserRegisterBindingModel();
    }

    public void register() throws IOException {
        //Check the passwords
        if (!this.registerBindingModel.getPassword().equals(this.registerBindingModel.getConfPassword())){
            throw new IllegalAccessError("Password doesn't Match!" );
        }

        //Check Entity is not null
        if (!this.userService.registerUser(this.modelMapper.map(registerBindingModel, UserServiceModel.class))){
            throw new IllegalAccessError("Can't Register User!" );
        }

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/login");
    }

    public UserRegisterBindingModel getRegisterBindingModel() {
        return registerBindingModel;
    }

    public void setRegisterBindingModel(UserRegisterBindingModel registerBindingModel) {
        this.registerBindingModel = registerBindingModel;
    }
}
