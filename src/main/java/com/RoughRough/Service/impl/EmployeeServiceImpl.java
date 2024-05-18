package com.RoughRough.Service.impl;

import com.RoughRough.Entity.Employee;
import com.RoughRough.Exception.ResourceNotFoundException;
import com.RoughRough.Payload.EmployeeDto;
import com.RoughRough.Repository.EmployeeRepository;
import com.RoughRough.Service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository  employeeRepository;

    @Autowired
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return mapToEmployeeDto(employeeRepository.save(mapToEmployee(employeeDto)));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Record with given Id is not present" + employeeId));

        return mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees =  employeeRepository.findAll();
        return employees.stream().map(this::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Record with given id is not present" + employeeId));

        employee.setCity(employeeDto.getCity());
        employee.setDept(employeeDto.getDept());
        employee.setName(employeeDto.getName());
        //employee.setId(employeeDto.getId());
        return mapToEmployeeDto(employeeRepository.save(employee));

    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Record with given id is not present" + employeeId));

        employeeRepository.deleteById(employee.getId());

    }

    //service to delete employee by providing employee name
//    @Override
//    public void deleteEmployeeByName(String employeeName){
//
//        employeeRepository.deleteEmployeeByName(employeeName);
//    }







    private Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;
    }

    private EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

}
