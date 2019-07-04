package hva.ewa.service;

import hva.ewa.model.Employee;
import hva.ewa.model.User;

import java.util.List;

public interface EmployeeRepositoryService {
    boolean saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Employee employee);

    Employee getEmployee(int id);

    boolean editEmployee(Employee employee);
}
