package spring.crud.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.crud.entity.Employee;
import spring.crud.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // GET all
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // GET one
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee not found with id: " + id));
    }

    // CREATE
    public Employee saveEmployee(Employee emp) {
        return repository.save(emp);
    }

    // UPDATE
    public Employee updateEmployee(Long id, Employee empDetails) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee not found with id: " + id));

        existing.setName(empDetails.getName());
        existing.setEmail(empDetails.getEmail());
        existing.setDepartment(empDetails.getDepartment());

        return repository.save(existing);
    }

    // DELETE
    public void deleteEmployee(Long id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Employee not found with id: " + id));
        repository.delete(emp);
    }
}
