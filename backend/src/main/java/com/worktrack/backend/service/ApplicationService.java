package com.worktrack.backend.service;

import com.worktrack.backend.dto.ApplicationRequest;
import com.worktrack.backend.dto.StatusUpdateRequest;
import com.worktrack.backend.entity.Application;
import com.worktrack.backend.entity.ApplicationStatus;
import com.worktrack.backend.entity.Job;
import com.worktrack.backend.entity.User;
import com.worktrack.backend.repository.ApplicationRepository;
import com.worktrack.backend.repository.JobRepository;
import com.worktrack.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              UserRepository userRepository,
                              JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public Map<String, Object> apply(ApplicationRequest request) {
        if (applicationRepository.existsByStudentIdAndJobId(request.getStudentId(), request.getJobId())) {
            throw new IllegalArgumentException("You have already applied for this job.");
        }

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found."));

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING);
        application.setAppliedAt(LocalDate.now());

        return toMap(applicationRepository.save(application));
    }

    public List<Map<String, Object>> getStudentApplications(Long studentId) {
        return applicationRepository.findByStudentId(studentId).stream()
                .map(this::toMap)
                .toList();
    }

    public List<Map<String, Object>> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::toMap)
                .toList();
    }

    public Map<String, Object> updateStatus(Long applicationId, StatusUpdateRequest request) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found."));

        application.setStatus(request.getStatus());
        return toMap(applicationRepository.save(application));
    }

    private Map<String, Object> toMap(Application application) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("id", application.getId());
        row.put("applicationId", application.getId());
        row.put("studentId", application.getStudent().getId());
        row.put("studentName", application.getStudent().getFullName());
        row.put("jobId", application.getJob().getId());
        row.put("jobTitle", application.getJob().getTitle());
        row.put("department", application.getJob().getDepartment());
        row.put("status", application.getStatus().name());
        row.put("appliedAt", application.getAppliedAt().toString());
        return row;
    }
}
