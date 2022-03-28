package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.dto.CompanyDto;
import hu.webuni.hr.gergej.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired HrController hrController=new HrController();

    private Map<Long, CompanyDto> companies=new HashMap<>();
    {
        companies.put(1L,new CompanyDto(1,1234,"IBM","Silicium Valley", hrController.getEmployees().values().stream().collect(Collectors.toList())));
        companies.put(2L,new CompanyDto(2,5678,"Apple","Cupertino", hrController.getEmployees().values().stream().collect(Collectors.toList())));
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam(required = false) boolean full){
        if (full){
            return companies.values().stream().collect(Collectors.toList());
        }
        return companies.values().stream()
                .map(e -> new CompanyDto(e.getCompanyId(),e.getRegNumber(),e.getName(), e.getAddress(),null)).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id){
        if (!companies.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companies.get(id));
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        companies.put(companyDto.getCompanyId(), companyDto);
        return companyDto;
    }

    @PostMapping("/{companyId}")
    public CompanyDto changeList (@RequestBody List<EmployeeDto> employeeDtoList, @PathVariable long companyId){
        companies.get(companyId).setEmployeesOfCo(employeeDtoList);
        return companies.get(companyId);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
        if (companies.containsKey(id)){
            companyDto.setCompanyId(id);
            companies.put(id,companyDto);
            return ResponseEntity.ok(companyDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/employee/{companyId}")
    public ResponseEntity<CompanyDto> addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
       if (!companies.containsKey(companyId)){
       return ResponseEntity.notFound().build();
       }
       List<EmployeeDto> allEmployees=companies.get(companyId).getEmployeesOfCo();
       allEmployees.add(employeeDto);
       companies.get(companyId).setEmployeesOfCo(allEmployees);
       return ResponseEntity.ok(companies.get(companyId));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id){
        companies.remove(id);
    }

    @DeleteMapping("/employee/{companyId}/{employeeId}")
    public void deleteEmployeeFromCo(@PathVariable long companyId, @PathVariable long employeeId){
        if (!companies.containsKey(companyId)){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
       CompanyDto company= companies.get(companyId);
       List<EmployeeDto>employeeDtoList = company.getEmployeesOfCo();
       employeeDtoList.remove(employeeDtoList.get((int) employeeId-1));
       company.setEmployeesOfCo(employeeDtoList);
    }
}
