package hu.webuni.hr.gergej.config;

import hu.webuni.hr.gergej.service.DefaultEmployeeService;
import hu.webuni.hr.gergej.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {

    @Bean
    public EmployeeService employeeService(){
        return new DefaultEmployeeService();
    }
}
