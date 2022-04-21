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

import java.util.*;
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
        List<Company> companies = companyService.findAllComapnies();
        if (isFull(full)) {
            return companyMapper.companiesToDtos(companies);
        } else {
            return companyMapper.companiesToDtosNoEmployees(companies);
        }
    }
    private boolean isFull(Boolean full) {
        return full !=null && full;
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@RequestParam(required = false) Boolean full, @PathVariable long id){
        Company company = companyService.findByCompanyId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (isFull(full)){
            return companyMapper.companyToDto(company);
        }else{
            return companyMapper.companyToDtoNoEmployees(company);
        }
//        Company fullCompany=companyService.findByCompanyId(id);
//        Company company=new Company(fullCompany.getCompanyId(), fullCompany.getRegNumber(), fullCompany.getName(), fullCompany.getAddress(), null);
//        if (fullCompany != null){
//            if (full)
//            return companyMapper.companyToDto(fullCompany);
//            else return companyMapper.companyToDto(company);
//    }else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        Company company=companyService.save(companyMapper.dtoToCompany(companyDto));
        return companyMapper.companyToDto(company);
    }

    //TODO  JSON-ben listát kell átadni
    @PostMapping("/{companyId}")
    public CompanyDto changeList (@RequestBody List<EmployeeDto> employeeDtoList, @PathVariable long companyId){
       Company company = companyService.findByCompanyId(companyId).get();
       if (company==null){
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }
       company.setEmployeesOfCo(employeeMapper.dtosToEmployees(employeeDtoList));
       return companyMapper.companyToDto(company);
    }

    @PutMapping("/{companyId}/employees")
    public CompanyDto replaceEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> employeeDtoList){
        try {
            return companyMapper
                    .companyToDto(companyService.replaceEmployees(companyId, employeeMapper.dtosToEmployees(employeeDtoList)));
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
            companyDto.setCompanyId(id);
            Company updatedCompany=companyService.update(companyMapper.dtoToCompany(companyDto));
            if (updatedCompany==null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
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
    @PostMapping("/{companyId}/employees")
    public CompanyDto addEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
       try{
           return companyMapper.companyToDto(companyService.addEmployee(companyId, employeeMapper.dtoToEmployee(employeeDto)));
       }catch (NoSuchElementException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
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
        try {
            return companyMapper.companyToDto(companyService.deleteEmployee(id, employeeId));
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
