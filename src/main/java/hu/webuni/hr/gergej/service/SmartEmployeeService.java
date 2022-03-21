package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.config.HrConfigProperties;
import hu.webuni.hr.gergej.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

@Service
public class SmartEmployeeService implements EmployeeService {
    LocalDate now = LocalDate.now();

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        Duration duration=Duration.between(employee.getStarted(), now.atStartOfDay());
        double experience = duration.toDays()/365;

        if (experience >= config.getSalary().getSmart().getLimit10()) {
            return config.getSalary().getSmart().getPercent10();
        } else if (experience >= config.getSalary().getSmart().getLimit5()) {
            return config.getSalary().getSmart().getPercent5();
        }
       else if (experience >= config.getSalary().getSmart().getLimitTwoHalf()) {
            return config.getSalary().getSmart().getPercent2();
        }
        return 0;
    }
}