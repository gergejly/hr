package hu.webuni.hr.gergej.mapper;


import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    @Mapping(source = "id", target = "employeeId")
    EmployeeDto employeeToDto (Employee employee);

    @Mapping(source = "employeeId", target = "id")
    Employee dtoToEmployee(EmployeeDto employeeDto);

    //@Mapping(source = "employeeId", target = "id")
    List<EmployeeDto> employeeToDtos (List<Employee> employees);

    //@Mapping(source = "id", target = "employeeId")
    List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtoList);
}
