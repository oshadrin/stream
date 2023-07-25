package com.skypro.streamemployee.controller;

import com.skypro.streamemployee.model.Employee;
import com.skypro.streamemployee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/{id}/salary/sum")
    public double getSalarySumByDepartment(@PathVariable int id){
        return departmentService.getSalarySumByDepartment(id);
    }

    @GetMapping(value = "/{id}/salary/max")
    public double getMaxSalary(@PathVariable int id){
        return departmentService.getMaxSalary(id);
    }

    @GetMapping(value =  "/{id}/salary/min")
    public double getMinSalary(@PathVariable int id){
        return departmentService.getMinSalary(id);
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> getAll(@PathVariable int id) {
        return departmentService.getAll((id));
    }

    @GetMapping(value = "/employees")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
