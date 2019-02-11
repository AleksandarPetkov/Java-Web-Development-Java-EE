package regapp.service;

import org.modelmapper.ModelMapper;
import regapp.domain.entities.Employee;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.repository.EmployeeRepository;

import javax.inject.Inject;

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
}
