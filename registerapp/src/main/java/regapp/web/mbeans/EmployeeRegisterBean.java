package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.binding.EmployeeRegisterBindingModel;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBean {

    private EmployeeRegisterBindingModel employeeRegisterBindingModel;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public EmployeeRegisterBean() {
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;

    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return employeeRegisterBindingModel;
    }

    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }

    public void register() throws IOException {
        EmployeeServiceModel model = this.modelMapper.map(employeeRegisterBindingModel, EmployeeServiceModel.class);
        this.employeeService.saveEmployee(model);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/");
    }
}
