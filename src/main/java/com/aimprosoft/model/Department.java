package com.aimprosoft.model;

public class Department {

    private int depId;

    private int depNumber;

    private String depName;

    public Department() {
    }

    public Department(int depId, int depNumber, String depName) {
        this.depId = depId;
        this.depNumber = depNumber;
        this.depName = depName;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDepNumber() {
        return depNumber;
    }

    public void setDepNumber(int depNumber) {
        this.depNumber = depNumber;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depNumber=" + depNumber +
                ", depName='" + depName + '\'' +
                '}';
    }
}
