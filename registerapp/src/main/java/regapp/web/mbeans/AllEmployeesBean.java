package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.views.AllEmployeeViewModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllEmployeesBean {

    private List<AllEmployeeViewModel> employees;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    public AllEmployeesBean() {
    }

    @Inject
    public AllEmployeesBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.getAllEmployees().stream()
                .map(x -> this.modelMapper.map(x, AllEmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    public BigDecimal getAverageSalary() {
        BigDecimal aveSum = new BigDecimal(0);
        for (AllEmployeeViewModel employee : employees) {
            aveSum = aveSum.add(employee.getSalary());
        }

        BigDecimal size = new BigDecimal(this.employees.size());
        return aveSum = aveSum.divide(size, 2, RoundingMode.CEILING);
    }

    public BigDecimal getTotalSalaryNeeded() {
        BigDecimal sum = new BigDecimal(0);
        for (AllEmployeeViewModel employee : employees) {
            sum = sum.add(employee.getSalary());
        }
        return sum;
    }

    public List<AllEmployeeViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<AllEmployeeViewModel> employees) {
        this.employees = employees;
    }
}
