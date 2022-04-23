package hu.webuni.hr.gergej.repository;

import hu.webuni.hr.gergej.model.AvarageSalaryByPosition;
import hu.webuni.hr.gergej.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM company c JOIN c.employeesOfCo e WHERE e.salary > :minSalary")
    List<Company> findEmployeeWithSalaryHigherThan(int minSalary);

    @Query("SELECT c FROM company c WHERE SIZE(c.employeesOfCo) > :minEmployeeCount")
    List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);

    @Query("SELECT e.rank AS position, avg(e.salary) AS avarageSalary FROM  company c INNER JOIN " +
            "employee e WHERE c.companyId = :companyId " +
            "GROUP BY e.rank ORDER BY avg(e.salary) DESC" )
    public List<AvarageSalaryByPosition> findAvarageSalariesByPosition(long companyId);
}
