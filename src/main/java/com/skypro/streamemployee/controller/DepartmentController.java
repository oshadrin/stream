package com.skypro.streamemployee.controller;

import com.skypro.streamemployee.model.Employee;
import com.skypro.streamemployee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/")
    public double getSalarySumByDepartment(@RequestParam("/sum") Integer dep){
        return departmentService.getSalarySumByDepartment(dep);
    }

    @GetMapping(path = "/max-salary")
    public double getMaxSalary(@RequestParam("/departmentID") Integer dep){
        return departmentService.getMaxSalary(dep);
    }

    @GetMapping(path = "/min-salary")
    public double getMinSalary(@RequestParam("/departmentID") Integer dep){
        return departmentService.getMinSalary(dep);
    }

    @GetMapping(value = "all", params = "departmentId")
    public List<Employee> getAll(@RequestParam("departmentId") int dep) {
        return departmentService.getAll((dep));
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
