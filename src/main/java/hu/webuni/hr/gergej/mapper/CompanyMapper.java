package hu.webuni.hr.gergej.mapper;

import hu.webuni.hr.gergej.dto.CompanyDto;
import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.model.Company;
import hu.webuni.hr.gergej.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    List<CompanyDto> companiesToDtos(List<Company> company);

    CompanyDto companyToDto (Company company);

    Company dtoToCompany(CompanyDto companyDto);

}
