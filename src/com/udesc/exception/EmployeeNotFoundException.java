package com.udesc.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException() {
        super("Nenhum funcionário encontrado");
    }
}
