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

    @Autowired
    HrController hrController =new HrController();

    private Map<Long, CompanyDto> companies=new HashMap<>();
    {
        companies.put(1L,new CompanyDto(1,1234,"IBM","Silicium Valley", hrController.getEmployees().values().stream().collect(Collectors.toList())));
        companies.put(2L,new CompanyDto(2,5678,"Apple","Cupertino", hrController.getEmployees().values().stream().collect(Collectors.toList())));
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam(required = false) Boolean full) {
        if (full == null ||!full){
           return companies.values().stream().map(c -> new CompanyDto(c.getCompanyId(),c.getRegNumber(),c.getName(),c.getAddress(),null))
           .collect(Collectors.toList());
        }else
        return new ArrayList<>(companies.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@RequestParam(required = false) Boolean full, @PathVariable long id){
        if (!companies.containsKey(id)){
            return ResponseEntity.notFound().build();
        }
        CompanyDto companyDto=companies.get(id);
        if (full==null ||!full){
            return ResponseEntity.ok(new CompanyDto(companyDto.getCompanyId(),companyDto.getRegNumber(),companyDto.getName(), companyDto.getAddress(), null));
        }
            return ResponseEntity.ok(companyDto);
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
       List<EmployeeDto> allEmployees = companies.get(companyId).getEmployeesOfCo();
       allEmployees.add(employeeDto);
       return ResponseEntity.ok(companies.get(companyId));
    }
    // 2. Megoldás
    @PostMapping("/{id}/employees")
    public CompanyDto addEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto){
        if (!companies.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CompanyDto companyDto = companies.get(id);
        companyDto.getEmployeesOfCo().add(employeeDto);
        return companyDto;
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id){
        companies.remove(id);
    }

    @DeleteMapping("/employee/{companyId}/{employeeId}")
    public CompanyDto deleteEmployeeFromCo(@PathVariable long companyId, @PathVariable long employeeId){
        if (!companies.containsKey(companyId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
       CompanyDto company= companies.get(companyId);
       List<EmployeeDto>employeeDtoList = company.getEmployeesOfCo();
       employeeDtoList.removeIf(e -> e.getEmployeeId() == employeeId);
       return company;
    }

    // 2. Megoldás
    @DeleteMapping("/{id}/employees/{employeeId}")
    public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId){
        if (!companies.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CompanyDto companyDto=companies.get(id);
        companyDto.getEmployeesOfCo().removeIf(e -> e.getEmployeeId() == employeeId);
        return companyDto;
    }
}
