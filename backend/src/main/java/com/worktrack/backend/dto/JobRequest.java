package com.worktrack.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequest {

    @NotBlank(message = "Job title is required.")
    private String title;

    @NotBlank(message = "Department is required.")
    private String department;

    @NotNull(message = "Pay per hour is required.")
    @Min(value = 1, message = "Pay per hour must be at least 1.")
    private Double payPerHour;

    @NotBlank(message = "Work timing is required.")
    private String workTiming;

    @NotBlank(message = "Description is required.")
    private String description;

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
}
