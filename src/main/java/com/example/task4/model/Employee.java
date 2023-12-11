package com.example.task4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не должно быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не должна быть пустой")
    private String lastName;

    @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
    @Max(value = 65, message = "Возраст должен быть не более 65 лет")
    private int age;

    @NotBlank(message = "Профессия не должна быть пустой")
    private String profession;

    @NotBlank(message = "Компания не должна быть пустой")
    private String company;

    @NotNull(message = "Дата вступления в должность не должна быть пустой")
    private LocalDate effectiveDate;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int age, String profession, String company, LocalDate effectiveDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
        this.company = company;
        this.effectiveDate = effectiveDate;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
