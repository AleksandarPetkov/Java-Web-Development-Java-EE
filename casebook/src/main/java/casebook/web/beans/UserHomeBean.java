package casebook.web.beans;


import casebook.domain.view.UserHomeViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserHomeBean {

    private List<UserHomeViewModel> userHomeViewModelList;

    private UserService userService;

    private ModelMapper modelMapper;

    public UserHomeBean() {
    }

    @Inject
    public UserHomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.init();
    }

    private void init(){

    }

}
