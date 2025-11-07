package sk.ukf.zamestnancidb.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.ukf.zamestnancidb.dto.ApiResponse;
import sk.ukf.zamestnancidb.entity.Employee;
import sk.ukf.zamestnancidb.exception.EmailAlreadyExistsException;
import sk.ukf.zamestnancidb.service.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Value("${jobTitle}")
    private List<String> jobTitle;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        ApiResponse<List<Employee>> response = ApiResponse.success(employeeService.findAll(), LocalDateTime.now().toString());

        model.addAttribute("employees", response.getData());
        return "employees/list";
    }

    @GetMapping("/{id}")
    public String viewEmployee(@PathVariable int id, Model model) {
        ApiResponse<Employee> response = ApiResponse.success(employeeService.findById(id), LocalDateTime.now().toString());

        model.addAttribute("employee", response.getData());

        return "employees/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("employee", new Employee());

        model.addAttribute("jobTitle", jobTitle);

        return "employees/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {

        Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);

        model.addAttribute("jobTitle", jobTitle);

        return "employees/form";
    }

    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("jobTitle", jobTitle);
            return "employees/form";
        }

        try {
            employeeService.save(employee);
            return "redirect:/employees";
        } catch (EmailAlreadyExistsException ex) {
            bindingResult.rejectValue("email", "email.exists", ex.getMessage());
            return "employees/form";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {

        ApiResponse<Employee> response = ApiResponse.success(employeeService.findById(id), LocalDateTime.now().toString());

        employeeService.deleteById(id);

        return "redirect:/employees";
    }
}
