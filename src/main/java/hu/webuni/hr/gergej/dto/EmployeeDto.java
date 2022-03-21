package hu.webuni.hr.gergej.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmployeeDto {

    private long id;
    private String name;
    private String rank;
    private int salary;
    private LocalDateTime started;

    public EmployeeDto(long id, String name, String rank, int salary, LocalDateTime started) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.salary = salary;
        this.started = started;
    }

    public EmployeeDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
