package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public int getPayRaisePercent(Employee employee);

    List<Employee> findAllEmployees();

    public Employee findEmployeeById(long id);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployee(long id);

    public Map<Long, Employee> getEmployees();

}
