package org.example;

public class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public Double getSalary()     { return salary; }

    @Override
    public String toString() {
        return fullName + " (" + department + ", " + salary + ")";
    }
}
