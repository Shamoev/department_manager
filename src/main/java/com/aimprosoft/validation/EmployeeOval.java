package com.aimprosoft.validation;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class EmployeeOval {


    @NotNull
    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotNull
    @Email(message = "Email is not valid")
    private String email;

    @MatchPattern(pattern = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "Date of Birth is not valid (YYYY-mm-DD)")
    private String dateOfBirth;

    @NotNull
    @MatchPattern(pattern = "^(\\d*\\.)?\\d+$", message = "Salary is not valid.")
    private String salary;

    public EmployeeOval(String surname, String email, String dateOfBirth, String salary) {
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSalary() {
        return salary;
    }

}
