package com.RoughRough.Repository;

import com.RoughRough.Entity.Employee;
import com.RoughRough.Payload.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //void deleteEmployeeByName(String name);

}
