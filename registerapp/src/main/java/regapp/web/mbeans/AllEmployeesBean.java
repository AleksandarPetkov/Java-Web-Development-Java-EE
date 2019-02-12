package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.views.AllEmployeeViewModel;
import regapp.service.EmployeeService;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class AllEmployeesBean {

    private List<AllEmployeeViewModel> employees;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public AllEmployeesBean() {
    }

    @Inject
    public AllEmployeesBean(EmployeeService employeeService, ModelMapper modelMapper){
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.getAllEmployees().stream()
                .map(x -> this.modelMapper.map(x, AllEmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    public void deleteUser(String id){
        String checkId = id;
        System.out.println();
    }

    public List<AllEmployeeViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<AllEmployeeViewModel> employees) {
        this.employees = employees;
    }
}
