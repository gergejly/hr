package hu.webuni.hr.gergej.service;

import hu.webuni.hr.gergej.config.HrConfigProperties;
import hu.webuni.hr.gergej.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class SmartEmployeeService implements EmployeeService {
    LocalDate now = LocalDate.now();

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        Duration duration=Duration.between(employee.getStarted(), now.atStartOfDay());
        double experience = duration.toDays()/365.0;



        // TODO  1. MEGOLDÁS:
//        if (experience >= config.getSalary().getSmart().getLimit10()) {
//            return config.getSalary().getSmart().getPercent10();
//        } else if (experience >= config.getSalary().getSmart().getLimit5()) {
//            return config.getSalary().getSmart().getPercent5();
//        }
//       else if (experience >= config.getSalary().getSmart().getLimitTwoHalf()) {
//            return config.getSalary().getSmart().getPercent2();
//        }
//          return 0;

        // TODO 2.MEGOLDÁS:
          TreeMap<Double, Integer> limits = config.getSalary().getSmart().getLimits();
//
//        Integer maxPercent = null;
//        for (Map.Entry<Double, Integer> entry : limits.entrySet()) {
//            if (experience> entry.getKey()){
//                maxPercent=entry.getValue();
//            }else break;
//        }
//        return maxPercent == null ? 0 : maxPercent;

        // TODO 3.MEGOLDÁS
//        Optional<Double> optionalMax = limits.keySet().stream().filter(k -> experience >= k).max(Double::compare);
//
//        return optionalMax.isEmpty() ? 0 : limits.get(optionalMax.get());

        //TODO 4:MEGOLDÁS

        Map.Entry<Double, Integer> floorEntry = limits.floorEntry(experience);
        return floorEntry == null ? 0 : floorEntry.getValue();
    }
}