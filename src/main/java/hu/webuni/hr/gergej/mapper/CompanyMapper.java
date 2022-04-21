package hu.webuni.hr.gergej.mapper;

import hu.webuni.hr.gergej.dto.CompanyDto;
import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.model.Company;
import hu.webuni.hr.gergej.model.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    List<CompanyDto> companiesToDtos(List<Company> company);

    @IterableMapping(qualifiedByName = "summary")
    List<CompanyDto> companiesToDtosNoEmployees(List<Company> company);

    CompanyDto companyToDto (Company company);

    @Mapping(target = "employeesOfCo", ignore = true)
    @Named("summary")
    CompanyDto companyToDtoNoEmployees (Company company);

    Company dtoToCompany(CompanyDto companyDto);

    @Mapping(source = "id", target = "employeeId")
    EmployeeDto employeeToDto (Employee employee);

    @Mapping(source = "employeeId", target = "id")
    Employee dtoToEmployee(EmployeeDto employeeDto);

}
