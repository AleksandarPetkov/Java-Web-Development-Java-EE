package regapp.web.mbeans;

import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeDeleteBean {

    private EmployeeService employeeService;

    public EmployeeDeleteBean() {
    }

    @Inject
    public EmployeeDeleteBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void deleteUser(String id) throws IOException {
        this.employeeService.deleteEmployeeModelById(id);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/");
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
