package com.TheOffice.theOffice.entities;

import java.math.BigDecimal;

public class Employee {
    private Long id;
    private String name;
    private Sex sex;
    private Integer seniority;
    private BigDecimal salary;
    private Integer level;
    private Mood mood;
    private Status status;
    private Job job;
    private Integer health;

    public Employee() {
    }

    public Employee(Long id, String name, Sex sex, Integer seniority, BigDecimal salary, Integer level, Mood mood, Status status, Job job, Integer health) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.seniority = seniority;
        this.salary = salary;
        this.level = level;
        this.mood = mood;
        this.status = status;
        this.job = job;
        this.health = health;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Sex getSex() { return sex; }
    public void setSex(Sex sex) { this.sex = sex; }

    public Integer getSeniority() { return seniority; }
    public void setSeniority(Integer seniority) { this.seniority = seniority; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Mood getMood() { return mood; }
    public void setMood(Mood mood) { this.mood = mood; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public Integer getHealth() { return health; }
    public void setHealth(Integer health) { this.health = health; }
}

// Définition des ENUMS
enum Sex {
    HOMME, FEMME;
}

enum Mood {
    mauvaise, bonne, neutre, heureuse, bof;
}

enum Status {
    ACTIF, INACTIF;
}

enum Job {
    MARKETING, VENTE, PRODUCTION;
}
