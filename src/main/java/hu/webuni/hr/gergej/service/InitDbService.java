package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.model.Company;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.repository.CompanyRepository;
import hu.webuni.hr.gergej.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InitDbService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyService companyService;

    public void clearDB(){
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    public void insertTestData(){
      Company companyOne = companyRepository.save(new Company(1234,"NASA","USA, Washington dc",null));
      Company companyTwo = companyRepository.save(new Company(3456,"Space-X","USA, Los Angeles",null));
      Company companyThree = companyRepository.save(new Company(5678,"Roszkozmosz","CCCP, Bajkonur",null));

      Employee employeeOne = employeeRepository.save(new Employee("Jurij Gagarin","Commander",200000
                , LocalDateTime.of(2015,05,15,05,55,55)));
      Employee employeeTwo = employeeRepository.save(new Employee("Farkas Bertalan","Pilóta",300000
                , LocalDateTime.of(2017,07,17,07,17,17)));
      Employee employeeThree = employeeRepository.save(new Employee("Niel Armstrong","Astronaut",400000
                , LocalDateTime.of(2022,02,22,22,22,22)));
      Employee employeeFour = employeeRepository.save(new Employee("James T. Kirk","Commander",200000
                , LocalDateTime.of(2010,10,10,10,10,10)));
      Employee employeeFive = employeeRepository.save(new Employee("Captain America","Captain",300000
                , LocalDateTime.of(2010,10,10,10,10,10)));

      companyOne.addEmployee(employeeOne);
      companyOne.addEmployee(employeeTwo);
      companyTwo.addEmployee(employeeThree);
      companyTwo.addEmployee(employeeFour);
      companyThree.addEmployee(employeeFive);

    }
}
