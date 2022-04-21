package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    public int getPayRaisePercent(Employee employee);

    List<Employee> findAllEmployees();

    public Optional<Employee> findEmployeeById(long id);

    public Employee saveEmployee(Employee employee);

    public Employee update(Employee employee);

    public void deleteEmployee(long id);

}
