package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.view.UserProfilViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserProfileBean {

    private UserProfilViewModel viewModel;

    private UserService userService;

    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    public UserProfilViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(UserProfilViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void initModel(){
        String userId = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        UserServiceModel userServiceModel = this.userService.findUserById(userId);
        if (userServiceModel == null){
            throw new IllegalArgumentException("Cant find User By Id");
        }
        this.viewModel = this.modelMapper.map(userServiceModel, UserProfilViewModel.class);
        System.out.println();
    }

}
