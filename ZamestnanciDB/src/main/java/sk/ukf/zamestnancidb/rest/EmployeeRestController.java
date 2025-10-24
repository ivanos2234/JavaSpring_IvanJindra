package sk.ukf.zamestnancidb.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.zamestnancidb.dto.ApiResponse;
import sk.ukf.zamestnancidb.entity.Employee;
import sk.ukf.zamestnancidb.service.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<ApiResponse<List<Employee>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(employeeService.findAll(), LocalDateTime.now().toString()));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(employee, LocalDateTime.now().toString()));
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse<Employee>> add(@Valid @RequestBody Employee employee) {
        employee.setId(0);
        Employee addedEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(addedEmployee, "Employee added successfully", LocalDateTime.now().toString()));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> update(@PathVariable int id, @Valid @RequestBody Employee employee) {
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(updatedEmployee, "Employee updated successfully", LocalDateTime.now().toString()));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> delete(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        employeeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(employee, "Employee deleted successfully", LocalDateTime.now().toString()));
    }
}
