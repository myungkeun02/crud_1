package org.myungkeun.crud_1.repository;

import org.myungkeun.crud_1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
