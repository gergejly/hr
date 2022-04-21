package hu.webuni.hr.gergej.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String rank;
    private int salary;
    private LocalDateTime started;

    @ManyToOne
    private Company company;

    public Employee(int salary, int year, int month, int day){
        this.salary=salary;
        this.started=LocalDateTime.of(year, month, day, 10,10,0);
    }

    public Employee(String name, String rank, int salary, LocalDateTime started) {
        this.name = name;
        this.rank = rank;
        this.salary = salary;
        this.started = started;
    }

    public Employee(long id, String name, String rank, int salary, LocalDateTime started) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.salary = salary;
        this.started = started;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
