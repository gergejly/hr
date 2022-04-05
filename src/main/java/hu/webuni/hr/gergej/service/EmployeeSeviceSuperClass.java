package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EmployeeSeviceSuperClass {

    private Map<Long, Employee> employees = new HashMap<>();

    {
        employees.put(1L, new Employee(1, "John Doe", "Ceo", 400000, LocalDateTime.of(2002, 02, 22, 22, 22)));
        employees.put(2L, new Employee(2, "Jane Doe", "Boss", 300000, LocalDateTime.of(2012, 12, 12, 12, 12)));
        employees.put(3L, new Employee(3, "Mekk Elek", "Leader", 200000, LocalDateTime.of(2018, 8, 8, 8, 8)));
        employees.put(4L, new Employee(4, "Hard Worker", "worker", 100000, LocalDateTime.of(2022, 2, 2, 2, 2)));
    }

    public Employee saveEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    public List<Employee> findAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee findEmployeeById(long id) {
        return employees.get(id);
    }

    public void deleteEmployee(long id) {
        employees.remove(id);
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Long, Employee> employees) {
        this.employees = employees;
    }
}
