package com.aimprosoft.validation;

import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

public class DepartmentOval {

    @NotNull
    @MatchPattern(pattern = "^\\d+$", message = "Department number is not valid")
    private String depNumber;

    @NotNull
    @NotBlank(message = "Department name cannot be blank")
    private String depName;

    public DepartmentOval(String depNumber, String depName) {
        this.depNumber = depNumber;
        this.depName = depName;
    }

    public String getDepNumber() {
        return depNumber;
    }

    public String getDepName() {
        return depName;
    }

}
