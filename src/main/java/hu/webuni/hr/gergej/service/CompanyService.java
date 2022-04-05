package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Company;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.web.CompanyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompanyService {


    private Map<Long, Company> companies=new HashMap<>();
    {
         companies.put(1L,new Company(1,1234,"IBM","Silicium Valley", new ArrayList<Employee>(List.of
                  (new Employee( 1,"Nagy Árpi","Worker",220000, LocalDateTime.of(2002, 02, 22, 22, 22))))));
         companies.put(2L,new Company(2,5678,"Apple","Cupertino",new ArrayList<Employee>(List.of
                  (new Employee(2, "Mekk Elek", "Leader", 200000, LocalDateTime.of(2018, 8, 8, 8, 8))))));
                                          //   employeeService.getEmployees().values().stream().collect(Collectors.toList())));
    }

    public Company save (Company company){
        companies.put(company.getCompanyId(), company);
        return company;
    }

    public List<Company> findAllComapnies (){
        return new ArrayList<>(companies.values());
    }

    public Company findByCompanyId (long id){
        return companies.get(id);
    }

    public void deleteCompany (long id){
        companies.remove(id);
    }

    public Map<Long, Company> getCompanies() {
        return companies;
    }
}
