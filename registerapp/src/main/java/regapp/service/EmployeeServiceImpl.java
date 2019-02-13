package regapp.service;

import org.modelmapper.ModelMapper;
import regapp.domain.entities.Employee;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try {
            Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);
            this.employeeRepository.save(employee);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        List<Employee> employeesEntities = this.employeeRepository.findAll();
        List<EmployeeServiceModel> models = new ArrayList<>();

        for (Employee entity : employeesEntities) {
            models.add(this.modelMapper.map(entity, EmployeeServiceModel.class));
        }
        return models;
    }

    @Override
    public void deleteEmployeeModelById(String id) {
        this.employeeRepository.deleteUserById(id);
    }
}
