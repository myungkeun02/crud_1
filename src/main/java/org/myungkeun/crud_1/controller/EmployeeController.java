package org.myungkeun.crud_1.controller;
import org.myungkeun.crud_1.exception.ResourceNotFoundException;
import org.myungkeun.crud_1.model.Employee;
import org.myungkeun.crud_1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    // 전체 직원정보를 가져오는 api

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    // 특정 직원정보를 가져오는 api
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
        return ResponseEntity.ok(employee);
    }
    // 직원정보를 생성하는 api
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return  employeeRepository.save(employee);
    }

    // 특정 직원정보를 수정하는 api
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable long id,
            @RequestBody Employee employee
    ) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No employee"));
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmail(employee.getEmail());
        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }
    // 특정 직원정보를 삭제하는 api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No employee"));
        employeeRepository.delete(employee);
        return ResponseEntity.ok("deleted");
    }
}
