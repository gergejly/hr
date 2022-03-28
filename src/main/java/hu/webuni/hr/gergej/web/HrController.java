package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class HrController {

    @Autowired
    EmployeeService employeeService;

    private Map<Long, EmployeeDto> employees = new HashMap<>();
    {
        employees.put(1L,new EmployeeDto(1,"John Doe","Ceo",400000, LocalDateTime.of(2002,02,22,22,22)));
        employees.put(2L,new EmployeeDto(2,"Jane Doe","Boss",300000, LocalDateTime.of(2012,12,12,12,12)));
        employees.put(3L,new EmployeeDto(3,"Mekk Elek","Leader",200000, LocalDateTime.of(2018,8,8,8,8)));
        employees.put(4L,new EmployeeDto(4,"Hard Worker","worker",100000, LocalDateTime.of(2022,2,2,2,2)));
    }
       @GetMapping
       public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary) {
        if (minSalary==null)
        return employees.values().stream().collect(Collectors.toList());
        else return employees.values().stream().filter(e -> e.getSalary()>minSalary).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
        EmployeeDto employeeDto = employees.get(id);
        if (employeeDto!=null){
            return ResponseEntity.ok(employeeDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/salary")
    public Integer getNewSalary(@RequestBody Employee employee){
        return employeeService.getPayRaisePercent(employee);
    }

//    1. Megoldás
//    @GetMapping(params="minSalary")
//    public List<EmployeeDto> getAllByMinSalary(@RequestParam int minSalary){
//        return employees.values().stream().filter( e -> e.getSalary()>minSalary).collect(Collectors.toList());
//    }


    @PostMapping
        public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        if (employees.containsValue(employeeDto)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        employees.put(employeeDto.getId(), employeeDto);
        return employeeDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto){
        if (employees.containsKey(id)){
            employeeDto.setId(id);
            employees.put(id, employeeDto);
            return ResponseEntity.ok(employeeDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id){
        employees.remove(id);
    }

    public Map<Long, EmployeeDto> getEmployees() {
        return employees;
    }
}
