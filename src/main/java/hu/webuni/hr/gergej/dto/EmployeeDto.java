package hu.webuni.hr.gergej.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Component
public class EmployeeDto {

    private long employeeId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String rank;
    @Positive
    private int salary;
    @Past
    private LocalDateTime started;

    public EmployeeDto(long employeeId, String name, String rank, int salary, LocalDateTime started) {
        this.employeeId = employeeId;
        this.name = name;
        this.rank = rank;
        this.salary = salary;
        this.started = started;
    }

    public EmployeeDto() {
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }
}
