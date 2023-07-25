package com.skypro.streamemployee.service;

import com.skypro.streamemployee.exception.EmployeeAlreadyAddedException;
import com.skypro.streamemployee.exception.EmployeeArrayIsFullException;
import com.skypro.streamemployee.exception.EmployeeInvalidDataException;
import com.skypro.streamemployee.exception.EmployeeNotFoundException;
import com.skypro.streamemployee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void whenFullThenThrowException() {
        for (int i = 0; i < 12; i++) {
            char additionalChar = (char) ('a' + i);
            Employee employee = new Employee("Firstname" + additionalChar, "Lastname", 1, 10000);
            employeeService.addEmployee(employee);
        }
        assertThrows(EmployeeArrayIsFullException.class, () -> employeeService.addEmployee(new Employee("name", "surname", 1, 14000)));
    }

    @Test
    void whenIsNotUniqThrowException() {
        Employee employee = new Employee("name", "surname", 1, 2);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeAlreadyAddedException.class, ()-> employeeService.addEmployee(employee));
    }

    @Test
    void addPositive(){
        Employee employee = new Employee("name", "surname", 1, 2);
        employeeService.addEmployee(employee);
        assertTrue(employeeService.getAll().contains(employee));
    }

    @Test
    void whenIncorrectThrowException(){
        Employee employee = new Employee("name", "surname", 1, 2);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeInvalidDataException.class, () -> employeeService.addEmployee(new Employee("name" + 11, "surname" + 22, 1, 2)));
    }

    @Test
    void findPositive(){
        Employee employee = new Employee("N", "S", 1, 2);
        employeeService.addEmployee(employee);
        Employee actual = employeeService.findEmployee("N", "S");
        assertNotNull(actual);
        assertEquals(employee, actual);
    }

    @Test
    void findNegativeThrowException() {
        Employee employee = new Employee("name", "surname", 1, 2);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.findEmployee("not_name","not_surname"));
    }

    @Test
    void removePositive(){
        Employee employee = new Employee("name", "surname", 1, 2);
        employeeService.addEmployee(employee);
        employeeService.removeEmployee("name", "surname");
        assertFalse(employeeService.getAll().contains(employee));
    }

    @Test
    void removeNegativeTrowException(){
        Employee employee = new Employee("name", "surname", 1, 2);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.removeEmployee("not_name","not_surname"));
    }

}
