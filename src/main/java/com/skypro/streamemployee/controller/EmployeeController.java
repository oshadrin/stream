package com.skypro.streamemployee.controller;


import com.skypro.streamemployee.model.Employee;
import com.skypro.streamemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> employee() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/add")

    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                Integer department, double salary) {
        return employeeService.addEmployee(new Employee(firstName, lastName, department, salary));
    }

    @GetMapping(path = "/del")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
}
