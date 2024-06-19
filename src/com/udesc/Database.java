package com.udesc;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.udesc.exception.EmployeeNotFoundException;
import com.udesc.model.Absence;
import com.udesc.model.Certificate;
import com.udesc.model.Employee;

public class Database {
    private static List<Employee> employees = new ArrayList<Employee>();

    public static void seed() {
        System.out.println("✅ Seeding database...");

        String[] names = {"Fulano", "Beltrano", "Sicrano"};

        for (String name : names) {
            final var employee = new Employee(name);

            employee.getCertificates().add(new Certificate(
                "Gripe",
                LocalDateTime.parse("2024-06-21T00:00:00"),
                LocalDateTime.parse("2024-06-22T00:00:00")
            ));

            employee.getCertificates().add(new Certificate(
                "Acidente fora do trabalho",
                LocalDateTime.parse("2024-06-22T00:00:00"),
                LocalDateTime.parse("2024-06-23T00:00:00")
            ));

            employee.getAbsences().add(new Absence(
                "Férias de trabalho",
                LocalDateTime.parse("2024-01-02T00:00:00"),
                LocalDateTime.parse("2024-01-06T00:00:00")
            ));

            employee.getTimeTracking().add(LocalTime.of(8, 0));
            employee.getTimeTracking().add(LocalTime.of(8, 0));

            createEmployee(employee);
        }

        System.out.println("✅ Database seeding completed...\n");
    }

    public static List<Employee> getEmployees() throws EmployeeNotFoundException {
        if (employees.size() == 0) {
            throw new EmployeeNotFoundException();
        }

        return employees;
    }

    public static void createEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        try {
            employees.set(employees.indexOf(employee), employee);

        } catch(NullPointerException e) {
            throw new EmployeeNotFoundException();
        }
    }

    public static void deleteEmployee(Employee employee) throws EmployeeNotFoundException {
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
    }

    public static boolean employeeExists(int index) {
        return index < employees.size() - 1;
    }
}
