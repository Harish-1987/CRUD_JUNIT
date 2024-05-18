package com.RoughRough.Service.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.RoughRough.Entity.Employee;
import com.RoughRough.Payload.EmployeeDto;
import com.RoughRough.Repository.EmployeeRepository;
import com.RoughRough.Service.EmployeeService;
import org.modelmapper.ModelMapper;


class EmployeeServiceimplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper mapper;

    //@Mock
    //private EmployeeService employeeService;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
       // employeeService = new EmployeeServiceImpl(employeeRepository);


        // Initialize Employee object with random values
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setCity("New York");
        employee.setDept("Engineering");

        // Initialize EmployeeDto object with corresponding values
        employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("John Doe");
        employeeDto.setCity("New York");
        employeeDto.setDept("Engineering");
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        when(employeeRepository.findAll()).thenReturn(employees);
        when(mapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        List<EmployeeDto> result = employeeServiceImpl.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employeeDto.getId(), result.get(0).getId());
        assertEquals(employeeDto.getName(), result.get(0).getName());
        assertEquals(employeeDto.getCity(), result.get(0).getCity());
        assertEquals(employeeDto.getDept(), result.get(0).getDept());
    }

    @Test
    public void testGetEmployeeById() {
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(mapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        EmployeeDto result = employeeServiceImpl.getEmployeeById(id);

        assertNotNull(result);
        assertEquals(employeeDto.getId(), result.getId());
        assertEquals(employeeDto.getName(), result.getName());
        assertEquals(employeeDto.getCity(), result.getCity());
        assertEquals(employeeDto.getDept(), result.getDept());
    }

    @Test
    public void testCreateEmployee() {
        when(mapper.map(employeeDto, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(mapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        EmployeeDto result = employeeServiceImpl.saveEmployee(employeeDto);

        assertNotNull(result);
        assertEquals(employeeDto.getId(), result.getId());
        assertEquals(employeeDto.getName(), result.getName());
        assertEquals(employeeDto.getCity(), result.getCity());
        assertEquals(employeeDto.getDept(), result.getDept());
    }

    @Test
    public void testUpdateEmployee() {
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        when(mapper.map(employeeDto, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(mapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        EmployeeDto result = employeeServiceImpl.updateEmployee(id, employeeDto);

        assertNotNull(result);
        assertEquals(employeeDto.getId(), result.getId());
        assertEquals(employeeDto.getName(), result.getName());
        assertEquals(employeeDto.getCity(), result.getCity());
        assertEquals(employeeDto.getDept(), result.getDept());
    }

    @Test
    public void testDeleteEmployeeById() {
        Long id = 1L;

        // Ensure deleteById is called correctly on the mock
        doNothing().when(employeeRepository).deleteById(id);

        // Call the method to test
        employeeServiceImpl.deleteEmployeeById(id);

        // Verify the interaction
        verify(employeeRepository, times(1)).deleteById(id);
    }
}