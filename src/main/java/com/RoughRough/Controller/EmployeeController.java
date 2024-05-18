package com.RoughRough.Controller;

import com.RoughRough.Payload.EmployeeDto;
import com.RoughRough.Service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/Employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //   http://localhost:8080/api/Employee
    @PostMapping()
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmp = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeById = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeById);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.ok(employeeDto1);
    }

    @DeleteMapping("{id}")
    public String deleteEmployeeById(@PathVariable("id") Long employeeId){
            employeeService.deleteEmployeeById(employeeId);
            return "Record deleted successfully";
    }

    //controller for deleting employee by name
//    @DeleteMapping("/delete/{name}")
//    public String deleteEmployeeByName(@PathVariable("name") String name){
//        employeeService.deleteEmployeeByName(name);
//        return "Record deleted successfully";
//    }


}

