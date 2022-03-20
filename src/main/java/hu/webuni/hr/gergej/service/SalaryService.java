package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    private EmployeeService employeeService;

    public int setNewSalary(Employee employee){
        int salary=employee.getSalary();
        int percent=employeeService.getPayRaisePercent(employee);
        salary = (int) ((salary/100.0) * (100+percent));
        employee.setSalary(salary);
        return salary;
    }
}
