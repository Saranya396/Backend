package com.worktrack.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "finalized_work_records")
public class FinalizedWorkRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "hours_worked", nullable = false)
    private Double hoursWorked;

    @Column(name = "finalized_date", nullable = false)
    private LocalDate finalizedDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public Double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(Double hoursWorked) { this.hoursWorked = hoursWorked; }
    public LocalDate getFinalizedDate() { return finalizedDate; }
    public void setFinalizedDate(LocalDate finalizedDate) { this.finalizedDate = finalizedDate; }
}
