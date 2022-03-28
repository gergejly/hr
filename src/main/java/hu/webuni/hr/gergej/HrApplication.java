package hu.webuni.hr.gergej;

import hu.webuni.hr.gergej.config.HrConfigProperties;
import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(salaryService.setNewSalary(new Employee(200000, 2010,11,17)));
		System.out.println(salaryService.setNewSalary(new Employee(200000, 2016,3,21)));
		System.out.println(salaryService.setNewSalary(new Employee(200000, 2018,5,5)));
		System.out.println(salaryService.setNewSalary(new Employee(400000,2019,6,19)));
		System.out.println(salaryService.setNewSalary(new Employee(400000, 2020, 3,22)));
	}
}
