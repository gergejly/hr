package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.mapper.EmployeeMapper;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.service.EmployeeService;
import hu.webuni.hr.gergej.service.EmployeeSeviceSuperClass;
import hu.webuni.hr.gergej.service.SmartEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class HrController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;

       @GetMapping
       public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary) {
        if (minSalary==null)
        return employeeMapper.employeeToDtos(employeeService.findAllEmployees());
           else return employeeMapper.employeeToDtos(employeeService.findAllEmployees().stream()
                .filter(e -> e.getSalary()>minSalary).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable long id){
        Employee employee= employeeService.findEmployeeById(id);
        if (employee!=null){
            return employeeMapper.employeeToDto(employee);
        }throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/salary")
    public Integer getNewSalary(@RequestBody @Valid Employee employee){
        return employeeService.getPayRaisePercent(employee);
    }

//    1. Megoldás
//    @GetMapping(params="minSalary")
//    public List<EmployeeDto> getAllByMinSalary(@RequestParam int minSalary){
//        return employees.values().stream().filter( e -> e.getSalary()>minSalary).collect(Collectors.toList());
//    }


    @PostMapping
        public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto){
           Employee employee=employeeMapper.dtoToEmployee(employeeDto);
           employeeService.saveEmployee(employee);
            return employeeMapper.employeeToDto(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto){
           Employee employee=employeeMapper.dtoToEmployee(employeeDto);
        if (employeeService.getEmployees().containsKey(id)){
            employeeDto.setEmployeeId(id);
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok(employeeDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
    }

}
