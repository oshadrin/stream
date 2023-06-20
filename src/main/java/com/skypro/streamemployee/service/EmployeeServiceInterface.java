package com.skypro.streamemployee.service;

import com.skypro.streamemployee.Employee;

public interface EmployeeServiceInterface {
    String employee();
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

}
