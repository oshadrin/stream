package com.skypro.streamemployee.service;


import com.skypro.streamemployee.exception.EmployeeInvalidDataException;
import com.skypro.streamemployee.model.Employee;
import com.skypro.streamemployee.exception.EmployeeAlreadyAddedException;
import com.skypro.streamemployee.exception.EmployeeArrayIsFullException;
import com.skypro.streamemployee.exception.EmployeeNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private static final int MAX_SIZE = 12;
    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee addEmployee(Employee employee) {
        if (!StringUtils.isAlpha(employee.getFirstName()) || !StringUtils.isAlpha(employee.getLastName())){
        throw new EmployeeInvalidDataException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeArrayIsFullException("Коллекция сотрудников переполнена");
        }
        correctCase(employee);
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.remove(employee.getFullName());
    }


    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(employee.getFullName());
    }

    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    private static String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }
    private static void correctCase(Employee employee){
        employee.setFirstName(StringUtils.capitalize(employee.getFirstName().toLowerCase()));
        employee.setLastName(StringUtils.capitalize(employee.getLastName().toLowerCase()));
    }
}

