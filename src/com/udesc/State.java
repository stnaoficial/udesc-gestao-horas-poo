package com.udesc;

import java.util.Optional;

import com.udesc.model.Employee;

public class State {
    private static Employee employee;

    public static void setEmployee(Employee employee) {
        State.employee = employee;
    }

    public static Employee getEmployee() {
        return employee;
    }

    public static boolean hasEmployee() {
        return employee != null;
    }

    public static Optional<Employee> getEmployeeOptional() {
        return Optional.ofNullable(employee);
    }
}
