package hu.webuni.hr.gergej.repository;

import hu.webuni.hr.gergej.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
