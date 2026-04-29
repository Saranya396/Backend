package com.worktrack.backend.service;

import com.worktrack.backend.dto.FeedbackRequest;
import com.worktrack.backend.entity.Application;
import com.worktrack.backend.entity.ApplicationStatus;
import com.worktrack.backend.entity.Feedback;
import com.worktrack.backend.entity.Job;
import com.worktrack.backend.entity.User;
import com.worktrack.backend.repository.ApplicationRepository;
import com.worktrack.backend.repository.FeedbackRepository;
import com.worktrack.backend.repository.JobRepository;
import com.worktrack.backend.repository.UserRepository;
import com.worktrack.backend.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final WorkLogRepository workLogRepository;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           ApplicationRepository applicationRepository,
                           UserRepository userRepository,
                           JobRepository jobRepository,
                           WorkLogRepository workLogRepository) {
        this.feedbackRepository = feedbackRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.workLogRepository = workLogRepository;
    }

    public Map<String, Object> create(FeedbackRequest request) {
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        User admin = userRepository.findById(request.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found."));
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found."));

        Feedback feedback = new Feedback();
        feedback.setStudent(student);
        feedback.setAdmin(admin);
        feedback.setJob(job);
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment().trim());
        feedback.setCreatedAt(LocalDate.now());

        return toMap(feedbackRepository.save(feedback));
    }

    public List<Map<String, Object>> getByStudent(Long studentId) {
        return feedbackRepository.findByStudentId(studentId).stream()
                .map(this::toMap)
                .toList();
    }

    public List<Map<String, Object>> getAdminCandidates() {
        return applicationRepository.findByStatus(ApplicationStatus.APPROVED).stream()
                .map(this::toCandidateMap)
                .toList();
    }

    private Map<String, Object> toMap(Feedback feedback) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("id", feedback.getId());
        row.put("studentId", feedback.getStudent().getId());
        row.put("jobId", feedback.getJob().getId());
        row.put("jobTitle", feedback.getJob().getTitle());
        row.put("adminId", feedback.getAdmin().getId());
        row.put("adminName", feedback.getAdmin().getFullName());
        row.put("rating", feedback.getRating());
        row.put("comment", feedback.getComment());
        row.put("createdAt", feedback.getCreatedAt().toString());
        return row;
    }

    private Map<String, Object> toCandidateMap(Application application) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("applicationId", application.getId());
        row.put("studentId", application.getStudent().getId());
        row.put("studentName", application.getStudent().getFullName());
        row.put("jobId", application.getJob().getId());
        row.put("jobTitle", application.getJob().getTitle());
        row.put("hoursWorked", workLogRepository.sumHoursByStudentIdAndJobId(
                application.getStudent().getId(),
                application.getJob().getId()
        ));
        return row;
    }
}
