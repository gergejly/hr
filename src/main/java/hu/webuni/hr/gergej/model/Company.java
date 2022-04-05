package hu.webuni.hr.gergej.model;

import hu.webuni.hr.gergej.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Company {

    private long companyId;
    private int regNumber;
    private String name;
    private String address;
    private List<Employee> employeesOfCo;

    public Company(long companyId, int regNumber, String name, String address, List<Employee> employeesOfCo) {
        this.companyId = companyId;
        this.regNumber = regNumber;
        this.name = name;
        this.address = address;
        this.employeesOfCo = employeesOfCo;
    }

    public Company() {
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployeesOfCo() {
        return employeesOfCo;
    }

    public void setEmployeesOfCo(List<Employee> employeesOfCo) {
        this.employeesOfCo = employeesOfCo;
    }
}

