package hu.webuni.hr.gergej;

import hu.webuni.hr.gergej.model.Employee;
import hu.webuni.hr.gergej.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(salaryService.setNewSalary(new Employee(200000, 10)));
		System.out.println(salaryService.setNewSalary(new Employee(200000, 5)));
		System.out.println(salaryService.setNewSalary(new Employee(200000, 3)));
		System.out.println(salaryService.setNewSalary(new Employee(400000, 2)));
		System.out.println(salaryService.setNewSalary(new Employee(400000, -1)));
	}
}
