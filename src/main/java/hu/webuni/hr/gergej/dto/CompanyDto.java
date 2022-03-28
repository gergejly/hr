package hu.webuni.hr.gergej.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDto {

    private long companyId;
    private int regNumber;
    private String name;
    private String address;
    private List<EmployeeDto> employeesOfCo;

    public CompanyDto(long companyId, int regNumber, String name, String address , List<EmployeeDto> employeesOfCo) {
        this.companyId = companyId;
        this.regNumber = regNumber;
        this.name = name;
        this.address = address;
        this.employeesOfCo = employeesOfCo;
    }

    public CompanyDto() {
    }

    public List<EmployeeDto> getEmployeesOfCo() {
        return employeesOfCo;
    }

    public void setEmployeesOfCo(List<EmployeeDto> employeesOfCo) {
        this.employeesOfCo = employeesOfCo;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
