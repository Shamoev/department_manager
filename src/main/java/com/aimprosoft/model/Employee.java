package com.aimprosoft.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private int emplId;

    private String surname;

    private String email;

    private LocalDate dateOfBirth;

    private BigDecimal salary;

    public Employee(int emplId, String surname, String email, LocalDate dateOfBirth, BigDecimal salary) {
        this.emplId = emplId;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public Employee() {
    }

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                '}';
    }
}
