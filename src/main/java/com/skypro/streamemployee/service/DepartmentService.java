package com.skypro.streamemployee.service;

import com.skypro.streamemployee.exception.EmployeeNotFoundException;
import com.skypro.streamemployee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Employee getMaxSalary(Integer dep) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == dep)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }
    public Employee getMinSalary(Integer dep) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == dep)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }
    public List<Employee> getAll(int dep){
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == dep)
                .collect(Collectors.toList());
    }
    public Map<Integer,List <Employee>> getAll(){
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }


}
