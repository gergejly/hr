package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Company;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.repository.CompanyRepository;
import hu.webuni.hr.gergej.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Company save (Company company){
       return companyRepository.save(company);
    }

    public Company update(Company company){
        if (!companyRepository.existsById(company.getCompanyId()))
            return null;
        return companyRepository.save(company);
    }

    public List<Company> findAllComapnies (){
        return companyRepository.findAll();
    }

    public Optional<Company> findByCompanyId (long id){
        return companyRepository.findById(id);
    }

    public void deleteCompany (long id){
        companyRepository.deleteById(id);
    }

    public Company addEmployee(long id, Employee employee){
       Company company= companyRepository.findById(id).get();
       company.addEmployee(employee);
       employeeRepository.save(employee);
       return company;
    }

    public Company deleteEmployee(long id, long employeeId){
        Company company = companyRepository.findById(id).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setCompany(null);
        company.getEmployeesOfCo().remove(employeeId);
        employeeRepository.save(employee);
        return company;
    }

    public Company replaceEmployees(long id, List<Employee> employees) {
        Company company=companyRepository.findById(id).get();
        for (Employee employee : company.getEmployeesOfCo()) {
            employee.setCompany(null);
        }
        company.getEmployeesOfCo().clear();

        for (Employee employee : employees) {
            company.addEmployee(employee);
           Employee savedEmployee = employeeRepository.save(employee);
           employee.setId(savedEmployee.getId());
        }
        return company;
    }
}
