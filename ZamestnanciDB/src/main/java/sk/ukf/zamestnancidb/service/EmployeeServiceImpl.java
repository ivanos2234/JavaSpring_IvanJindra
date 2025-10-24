package sk.ukf.zamestnancidb.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.zamestnancidb.dao.EmployeeDAO;
import sk.ukf.zamestnancidb.dao.EmployeeRepository;
import sk.ukf.zamestnancidb.entity.Employee;
import sk.ukf.zamestnancidb.exception.EmailAlreadyExistsException;
import sk.ukf.zamestnancidb.exception.ObjectNotFoundException;

import javax.print.DocFlavor;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // private EmployeeDAO employeeDAO;
    EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeDAO(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(() ->new ObjectNotFoundException("Employee", id));
    }

    @Transactional
    public Employee save(Employee employee) {
        if (employee.getId() == 0) {
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        } else {
            Employee existingWithEmail = employeeRepository.findByEmail(employee.getEmail()).orElse(null);

            if (existingWithEmail != null && existingWithEmail.getId() != employee.getId()) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Employee", id);
        }
        employeeRepository.deleteById(id);
    }
}
