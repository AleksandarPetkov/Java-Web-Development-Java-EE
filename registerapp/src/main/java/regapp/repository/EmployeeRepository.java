package regapp.repository;

import regapp.domain.entities.Employee;

public interface EmployeeRepository extends GenericRepository<Employee, String> {

    void deleteUserById(String id);
}
