package hu.webuni.hr.gergej.web;

import hu.webuni.hr.gergej.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HrTLController {

    private List<Employee> allEmployees = new ArrayList<>();
    {
        allEmployees.add(new Employee(1,"John Doe","Ceo",2000000, LocalDateTime.of
                (1999,9,8,12,12)));
        allEmployees.add(new Employee(2,"Jane Doe","Leader",1000000, LocalDateTime.of
                (2015,8,8,8,8)));
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/employees")
    public String listEmployees (Map<String, Object> model) {
        model.put("employees",allEmployees);
        model.put("newEmployee", new Employee());
        return "employees";
    }

    @PostMapping("/employees")
    public String addEmployee(Employee employee){
        allEmployees.add(employee);
        return "redirect:employees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable long id){
        allEmployees.removeIf(e -> e.getId()==id);
        return "redirect:/employees";
    }
    @GetMapping("/employees/{id}")
    public String editEmployee(@PathVariable long id ,Map<String,Object> model){
        Employee selectedEmployee = allEmployees.stream().filter(e -> e.getId()==id).findFirst().get();
        model.put("employee", selectedEmployee);
        return "editEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(Employee employee){
        for(int i=0; i< allEmployees.size(); i++){
            if (allEmployees.get(i).getId()==employee.getId()){
                allEmployees.set(i, employee);
                break;
            }
        }
        return "redirect:employees";
    }
}
