package com.skypro.streamemployee.service;


import com.skypro.streamemployee.Employee;
import com.skypro.streamemployee.exception.EmployeeAlreadyAddedException;
import com.skypro.streamemployee.exception.EmployeeArrayIsFullException;
import com.skypro.streamemployee.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {

    private final Map<String, Employee> employees;
    private static final int MAX_SIZE = 12;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    public String employee() {
        return employees.toString();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeArrayIsFullException("Коллекция сотрудников переполнена");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
            if (!employees.containsKey(employee.getFullName())) {
                throw new EmployeeNotFoundException("Сотрудник не найден");
            }
        return employees.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee tempEmployee = new Employee(firstName, lastName);
        if (!employees.containsKey(tempEmployee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(tempEmployee.getFullName());
    }
}
