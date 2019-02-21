package casebook.web.beans;

import casebook.domain.models.binding.UserRegisterBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel registerModel;

    private UserService userService;

    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.registerModel = new UserRegisterBindingModel();
    }

    public void register() throws IOException {
        //Check the passwords
        if (!this.registerModel.getPassword().equals(this.registerModel.getConfPassword())){
            throw new IllegalAccessError("Password doesn't Match!" );
        }

        //Check Entity is not null
        if (!this.userService.registerUser(this.modelMapper.map(registerModel, UserServiceModel.class))){
            throw new IllegalAccessError("Can't Register User!" );
        }

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/login");
    }

    public UserRegisterBindingModel getRegisterModel() {
        return registerModel;
    }

    public void setRegisterModel(UserRegisterBindingModel registerModel) {
        this.registerModel = registerModel;
    }
}
