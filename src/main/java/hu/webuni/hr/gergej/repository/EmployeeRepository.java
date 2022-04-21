package hu.webuni.hr.gergej.repository;

import hu.webuni.hr.gergej.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> findByRank(String rank);
    List<Employee> findByNameStartingWithIgnoreCase(String name);
    List<Employee> findByStartedBetween(LocalDateTime start, LocalDateTime end);
}
