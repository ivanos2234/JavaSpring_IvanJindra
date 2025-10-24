package sk.ukf.zamestnancidb.dao;

import sk.ukf.zamestnancidb.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
