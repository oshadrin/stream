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

    public double getSalarySumByDepartment(int dep){
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double getMaxSalary(int dep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    public double getMinSalary(int dep) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow();
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
