package com.skypro.streamemployee.service;

import com.skypro.streamemployee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    Collection<Employee> EMPLOYEES = Arrays.asList(
            new Employee("Ivan", "Ivanov", 1, 10000),
            new Employee("IvanA", "Ivanov", 1, 20000),
            new Employee("IvanB", "Ivanov", 1, 30000),
            new Employee("IvanC", "Ivanov", 1, 50000),
            new Employee("IvanD", "Ivanov", 1, 10000),
            new Employee("IvanE", "Ivanov", 1, 5000),
            new Employee("IvanF", "Ivanov", 2, 20000),
            new Employee("IvanG", "Ivanov", 2, 30000),
            new Employee("IvanH", "Ivanov", 2, 10000),
            new Employee("IvanI", "Ivanov", 2, 7000),
            new Employee("IvanJ", "Ivanov", 2, 60000),
            new Employee("IvanK", "Ivanov", 3, 70000)
            );

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init(){
        when(employeeService.getAll()).thenReturn(EMPLOYEES);
    }
    @Test
    void sum(){
        double actual = departmentService.getSalarySumByDepartment(1);
        assertEquals(125000, actual, 0.000001);
    }

    @Test
    void max() {
        double actual = departmentService.getMaxSalary(2);
        assertEquals(60000, actual, 0.000001);
    }
    @Test
    void min() {
        double actual = departmentService.getMinSalary(2);
        assertEquals(7000, actual, 0.000001);
    }

    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getAll(3);
        Collection<Employee> expected = Collections.singletonList(new Employee("IvanK", "Ivanov", 3, 70000));
        assertIterableEquals(expected, actual);
        }

    @Test
    void getAll() {
        Map<Integer, List <Employee>> actual = departmentService.getAll();
        Employee ivanK = new Employee("IvanK", "Ivanov", 3, 70000);
        assertTrue(actual.get(3).contains(ivanK));
        assertFalse(actual.get(2).contains(ivanK));
        assertEquals(3, actual.keySet().size());

    }

    @Test
    void getAllByWrongDepartment() {
        List<Employee> all = departmentService.getAll(4);
        assertTrue(all.isEmpty());
    }




}
