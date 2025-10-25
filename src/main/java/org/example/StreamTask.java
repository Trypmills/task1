package org.example;

import java.util.*;

public class StreamTask {
    public static void main(String[] args) {
        List<Employee> emps = List.of(
                new Employee("Иванов Иван",   28, "DEV", 120_000.0),
                new Employee("Петров Пётр",   35, "DEV", 150_000.0),
                new Employee("Сидорова Анна", 31, "QA",  110_000.0),
                new Employee("Кузнецов Олег", 26, "DEV",  90_000.0),
                new Employee("Маркова Ольга", 29, "QA",   95_000.0)
        );

        String dept = "DEV";

        OptionalDouble avg = emps.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(dept))
                .mapToDouble(Employee::getSalary)
                .average();

        System.out.println("Сотрудники: " + emps);
        System.out.println("Средняя зарплата отдела " + dept + ": " +
                (avg.isPresent() ? avg.getAsDouble() : "нет данных"));
    }
}
