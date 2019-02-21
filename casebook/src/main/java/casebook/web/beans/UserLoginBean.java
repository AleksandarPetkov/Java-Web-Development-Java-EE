package casebook.web.beans;

import casebook.domain.models.binding.UserLoginBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel loginBindingModel;

    private UserService userService;

    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.loginBindingModel = new UserLoginBindingModel();
    }

    public void login() throws IOException {
        UserServiceModel model = this.userService
                .loginUser(this.modelMapper.map(this.loginBindingModel, UserServiceModel.class));

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("username", model.getUsername());
        session.setAttribute("userId", model.getId());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/home");
    }

    public UserLoginBindingModel getLoginBindingModel() {
        return loginBindingModel;
    }

    public void setLoginBindingModel(UserLoginBindingModel loginBindingModel) {
        this.loginBindingModel = loginBindingModel;
    }
}
