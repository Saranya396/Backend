package com.worktrack.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String department;

    @Column(name = "pay_per_hour", nullable = false)
    private Double payPerHour;

    @Column(name = "work_timing", nullable = false)
    private String workTiming;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "posted_date", nullable = false)
    private LocalDate postedDate;

    public Job() {
    }

    public Job(String title, String department, Double payPerHour, String workTiming, String description, LocalDate postedDate) {
        this.title = title;
        this.department = department;
        this.payPerHour = payPerHour;
        this.workTiming = workTiming;
        this.description = description;
        this.postedDate = postedDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public Double getPayPerHour() { return payPerHour; }
    public void setPayPerHour(Double payPerHour) { this.payPerHour = payPerHour; }
    public String getWorkTiming() { return workTiming; }
    public void setWorkTiming(String workTiming) { this.workTiming = workTiming; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDate postedDate) { this.postedDate = postedDate; }
}
