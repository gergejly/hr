package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HrControllerIT {

    @Autowired
    WebTestClient webTestClient;
    private final static String BASE_URI ="/api/employees";

//    @Test
//    void testThatCreatedCompanyIsListed() throws Exception{
//        List<EmployeeDto> employeesBefore = getAllEmployees();
//
//        EmployeeDto newEmployee = new EmployeeDto(1,"Bruce Willis","hero",2000000,
//                LocalDateTime.of(2017,11,17,11,17));
//        createEmployee(newEmployee);
//
//        List <EmployeeDto> employeesAfter = getAllEmployees();
//
//        assertThat(employeesAfter.subList(0,employeesBefore.size()))
//                .usingRecursiveFieldByFieldElementComparator()
//                .containsExactlyElementsOf(employeesBefore);
//
//        assertThat(employeesAfter.get(employeesAfter.size()-1))
//                .usingRecursiveComparison()
//                .isEqualTo(newEmployee);
//    }

//    private void createEmployee(EmployeeDto newEmployee) {
//        webTestClient.post()
//                .uri(BASE_URI)
//                .bodyValue(newEmployee)
//                .exchange()
//                .expectStatus().isOk();
//    }
//
//    private void updateEmployee(EmployeeDto newEmployee) {
//        webTestClient.put()
//                .uri(BASE_URI)
//                .bodyValue(newEmployee)
//                .exchange()
//                .expectStatus().isOk();
//    }
//
//    private List<EmployeeDto> getAllEmployees() {
//        List<EmployeeDto> responseList = webTestClient.get()
//                .uri(BASE_URI)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(EmployeeDto.class)
//                .returnResult().getResponseBody();
//
//        Collections.sort(responseList, (a1, a2) -> Long.compare(a1.getEmployeeId(), a2.getEmployeeId()));
//        return responseList;
//    }


}
