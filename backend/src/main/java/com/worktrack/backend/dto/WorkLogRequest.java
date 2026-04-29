package com.worktrack.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class WorkLogRequest {

    @NotNull(message = "Student is required.")
    private Long studentId;

    @NotNull(message = "Job is required.")
    private Long jobId;

    @NotNull(message = "Hours worked is required.")
    @Min(value = 0, message = "Hours worked cannot be negative.")
    private Double hoursWorked;

    @NotNull(message = "Work date is required.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate workDate;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public Double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(Double hoursWorked) { this.hoursWorked = hoursWorked; }
    public LocalDate getWorkDate() { return workDate; }
    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }
}
