package hu.webuni.hr.gergej.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Employee {

    private long id;
    private String name;
    private String rank;
    private int salary;
    private LocalDateTime started;

    public Employee(int salary, int experience) {
        this.salary = salary;
        this.setStarted(LocalDateTime.now().minusYears(experience));
    }

    public Employee() {
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
