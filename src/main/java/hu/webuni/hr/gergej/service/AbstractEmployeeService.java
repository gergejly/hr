package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    private Map<Long, Employee> employees = new HashMap<>();
//
//    {
//        employees.put(1L, new Employee(1, "John Doe", "Ceo", 400000, LocalDateTime.of(2002, 02, 22, 22, 22)));
//        employees.put(2L, new Employee(2, "Jane Doe", "Boss", 300000, LocalDateTime.of(2012, 12, 12, 12, 12)));
//        employees.put(3L, new Employee(3, "Mekk Elek", "Leader", 200000, LocalDateTime.of(2018, 8, 8, 8, 8)));
//        employees.put(4L, new Employee(4, "Hard Worker", "worker", 100000, LocalDateTime.of(2022, 2, 2, 2, 2)));
//    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        if (!employeeRepository.existsById(employee.getId()))
            return null;
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
