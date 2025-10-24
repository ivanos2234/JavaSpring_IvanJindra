package sk.ukf.zamestnancidb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.zamestnancidb.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
}
