package com.practice.employeeinfo.utils;

import com.practice.employeeinfo.entity.Employee;

import java.util.Comparator;

public class EmployeeEmailAndNumberComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee employee1, Employee employee2) {
        return employee1.getEmailAddress().compareToIgnoreCase(employee2.getEmailAddress());
    }
}
