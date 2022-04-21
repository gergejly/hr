package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrControllerIT {

    @Autowired
    WebTestClient webTestClient;
    private final static String BASE_URI ="/api/employees";

@Test
void testThatNewValidEmployeeCanBeSaved() throws Exception{
    List<EmployeeDto> employeesBefore = getAllEmployees();

    EmployeeDto newEmployee = new EmployeeDto(0L, "Nagy Árpi","officer", 200000, LocalDateTime.of(2017,11,17,11,17));
    saveEmployee(newEmployee)
            .expectStatus().isOk();

    List<EmployeeDto> employeesAfter = getAllEmployees();

    assertThat(employeesAfter.size()).isEqualTo(employeesBefore.size()+1);
    assertThat(employeesAfter.get(employeesBefore.size()-1))
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(newEmployee);
}

@Test
void testThatNewValidEmployeeCannotBeSaved() throws Exception{
    List<EmployeeDto> employeesBefore=getAllEmployees();
    EmployeeDto newEmployee = newInvalidEmployee();
    saveEmployee(newEmployee)
            .expectStatus()
            .isBadRequest();

    List<EmployeeDto> employeesAfter=getAllEmployees();

    assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
}

private EmployeeDto newInvalidEmployee(){
    return new EmployeeDto(0L,"", "student", 20000,LocalDateTime.of(2019,01,01,8,0));
}

@Test
void testThatEmployeeCanBeUpdatedWithValidFileds() throws Exception{
    EmployeeDto newEmployee = new EmployeeDto(0L, "Nagy Árpi","officer", 200000, LocalDateTime.of(2017,11,17,11,17));

    EmployeeDto savedEmployee=saveEmployee(newEmployee)
            .expectStatus().isOk()
            .expectBody(EmployeeDto.class)
            .returnResult()
            .getResponseBody();

    List<EmployeeDto> employeesBefore=getAllEmployees();
    savedEmployee.setName("modified");
    updateEmployee(savedEmployee).expectStatus().isOk();

    List<EmployeeDto> employeesAfter = getAllEmployees();

    assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
    assertThat(employeesAfter.get(employeesAfter.size()-1))
            .usingRecursiveComparison()
            .isEqualTo(savedEmployee);
}

@Test
void testThatEmployeeCannotBeUpdatedWithInvalidFields() throws Exception{
    EmployeeDto newEmployee = new EmployeeDto(0L, "Nagy Árpi","officer", 200000, LocalDateTime.of(2017,11,17,11,17));
    EmployeeDto savedEmployee=saveEmployee(newEmployee)
            .expectStatus().isOk()
            .expectBody(EmployeeDto.class)
            .returnResult().getResponseBody();

    List<EmployeeDto> employeesBefore=getAllEmployees();
    EmployeeDto invalidEmployee = newInvalidEmployee();
    invalidEmployee.setEmployeeId(savedEmployee.getEmployeeId());
    updateEmployee(invalidEmployee).expectStatus().isBadRequest();

    List<EmployeeDto> employeesAfter = getAllEmployees();

    assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
    assertThat(employeesAfter.get(employeesAfter.size()-1))
            .usingRecursiveComparison()
            .isEqualTo(savedEmployee);


}

    private WebTestClient.ResponseSpec saveEmployee(EmployeeDto newEmployee) {
       return webTestClient.post()
                .uri(BASE_URI)
                .bodyValue(newEmployee)
                .exchange();
    }

    private WebTestClient.ResponseSpec updateEmployee(EmployeeDto newEmployee) {
        String path=BASE_URI+"/"+newEmployee.getEmployeeId();
       return webTestClient.put()
                .uri(path)
                .bodyValue(newEmployee)
                .exchange();
    }

    private List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> responseList = webTestClient.get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeDto.class)
                .returnResult().getResponseBody();

        Collections.sort(responseList, Comparator.comparing(EmployeeDto::getEmployeeId));
        return responseList;
    }


}
