package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.config.HrConfigProperties;
import hu.webuni.hr.gergej.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Primary
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        return config.getSalary().getDef().getPercent();
    }
}
