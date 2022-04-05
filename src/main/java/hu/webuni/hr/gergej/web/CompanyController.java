package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.dto.CompanyDto;
import hu.webuni.hr.gergej.dto.EmployeeDto;
import hu.webuni.hr.gergej.mapper.CompanyMapper;
import hu.webuni.hr.gergej.mapper.EmployeeMapper;
import hu.webuni.hr.gergej.model.Company;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.service.CompanyService;
import hu.webuni.hr.gergej.service.EmployeeService;
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
    CompanyService companyService;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam(required = false) Boolean full) {
        List<CompanyDto> allCompanyDtos = companyMapper.companiesToDtos(companyService.findAllComapnies());
        if (full == null ||!full){
           return companyService.getCompanies().values().stream().map(c -> new CompanyDto(c.getCompanyId(),c.getRegNumber(),c.getName(),c.getAddress(),null))
           .collect(Collectors.toList());
        }else
        return allCompanyDtos;
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@RequestParam(required = false) Boolean full, @PathVariable long id){
        Company fullCompany=companyService.findByCompanyId(id);
        Company company=new Company(fullCompany.getCompanyId(), fullCompany.getRegNumber(), fullCompany.getName(), fullCompany.getAddress(), null);
        if (fullCompany != null){
            if (full)
            return companyMapper.companyToDto(fullCompany);
            else return companyMapper.companyToDto(company);
    }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        Company company=companyService.save(companyMapper.dtoToCompany(companyDto));
        return companyMapper.companyToDto(company);
    }

    //TODO  JSON-ben listát kell átadni
    @PostMapping("/{companyId}")
    public CompanyDto changeList (@RequestBody List<EmployeeDto> employeeDtoList, @PathVariable long companyId){
       Company company = companyService.getCompanies().get(companyId);
       if (company==null){
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }
       company.setEmployeesOfCo(employeeMapper.dtosToEmployees(employeeDtoList));
       return companyMapper.companyToDto(company);

    }



    @PutMapping("/{id}")
    public CompanyDto modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
        if (companyService.getCompanies().containsKey(id)){
            companyDto.setCompanyId(id);
            companyService.save(companyMapper.dtoToCompany(companyDto));
            return companyDto;
        }else
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/employee/{companyId}")
//    public ResponseEntity<CompanyDto> addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
//       if (!companies.containsKey(companyId)){
//       return ResponseEntity.notFound().build();
//       }
//       List<EmployeeDto> allEmployees = companies.get(companyId).getEmployeesOfCo();
//       allEmployees.add(employeeDto);
//       return ResponseEntity.ok(companies.get(companyId));
//    }
    // 2. Megoldás
    @PostMapping("/{id}/employees")
    public CompanyDto addEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto){
        Company company=companyService.getCompanies().get(id);
        if (company==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       company.getEmployeesOfCo().add(employeeMapper.dtoToEmployee(employeeDto));
        return companyMapper.companyToDto(company) ;
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id){
        companyService.deleteCompany(id);
    }

//    @DeleteMapping("/employee/{companyId}/{employeeId}")
//    public CompanyDto deleteEmployeeFromCo(@PathVariable long companyId, @PathVariable long employeeId){
//        if (!companies.containsKey(companyId)){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//       CompanyDto company= companies.get(companyId);
//       List<EmployeeDto>employeeDtoList = company.getEmployeesOfCo();
//       employeeDtoList.removeIf(e -> e.getEmployeeId() == employeeId);
//       return company;
//    }
//
    // 2. Megoldás
    @DeleteMapping("/{id}/employees/{employeeId}")
    public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long employeeId){
        Company company=companyService.findByCompanyId(id);
        if (company==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        company.getEmployeesOfCo().removeIf(e -> e.getId()==employeeId);

        return companyMapper.companyToDto(company);
    }


}
