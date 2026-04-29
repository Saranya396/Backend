package com.worktrack.backend.service;

import com.worktrack.backend.dto.WorkLogRequest;
import com.worktrack.backend.entity.Application;
import com.worktrack.backend.entity.ApplicationStatus;
import com.worktrack.backend.entity.FinalizedWorkRecord;
import com.worktrack.backend.entity.Job;
import com.worktrack.backend.entity.User;
import com.worktrack.backend.entity.WorkLog;
import com.worktrack.backend.repository.ApplicationRepository;
import com.worktrack.backend.repository.FinalizedWorkRecordRepository;
import com.worktrack.backend.repository.JobRepository;
import com.worktrack.backend.repository.UserRepository;
import com.worktrack.backend.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkLogService {

    private final WorkLogRepository workLogRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final FinalizedWorkRecordRepository finalizedWorkRecordRepository;

    public WorkLogService(WorkLogRepository workLogRepository,
                          UserRepository userRepository,
                          JobRepository jobRepository,
                          ApplicationRepository applicationRepository,
                          FinalizedWorkRecordRepository finalizedWorkRecordRepository) {
        this.workLogRepository = workLogRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.finalizedWorkRecordRepository = finalizedWorkRecordRepository;
    }

    public Map<String, Object> save(WorkLogRequest request) {
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found."));
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found."));

        WorkLog workLog = workLogRepository.findByStudentIdAndJobId(request.getStudentId(), request.getJobId())
                .orElseGet(WorkLog::new);
        workLog.setStudent(student);
        workLog.setJob(job);
        workLog.setHoursWorked(request.getHoursWorked());
        workLog.setWorkDate(request.getWorkDate());

        WorkLog saved = workLogRepository.save(workLog);
        return toStudentMap(saved);
    }

    public List<Map<String, Object>> getByStudent(Long studentId) {
        List<Map<String, Object>> rows = new ArrayList<>();
        workLogRepository.findByStudentId(studentId).forEach(log -> rows.add(toStudentMap(log)));
        finalizedWorkRecordRepository.findByStudentId(studentId).forEach(record -> {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("id", "final-" + record.getId());
            row.put("jobTitle", record.getJobTitle());
            row.put("workDate", record.getFinalizedDate().toString());
            row.put("hoursWorked", record.getHoursWorked());
            rows.add(row);
        });
        return rows;
    }

    public List<Map<String, Object>> getAdminRows() {
        List<Map<String, Object>> rows = new ArrayList<>();

        applicationRepository.findByStatus(ApplicationStatus.APPROVED).forEach(application -> {
            Double hours = workLogRepository.sumHoursByStudentIdAndJobId(
                    application.getStudent().getId(),
                    application.getJob().getId()
            );

            Map<String, Object> row = new LinkedHashMap<>();
            row.put("applicationId", application.getId());
            row.put("studentId", application.getStudent().getId());
            row.put("studentName", application.getStudent().getFullName());
            row.put("jobId", application.getJob().getId());
            row.put("jobTitle", application.getJob().getTitle());
            row.put("status", application.getStatus().name());
            row.put("hoursWorked", hours);
            row.put("workDate", LocalDate.now().toString());
            row.put("finalized", false);
            rows.add(row);
        });

        finalizedWorkRecordRepository.findAll().forEach(record -> {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("applicationId", "final-" + record.getId());
            row.put("studentId", record.getStudentId());
            row.put("studentName", record.getStudentName());
            row.put("jobId", null);
            row.put("jobTitle", record.getJobTitle());
            row.put("status", "FINALIZED");
            row.put("hoursWorked", record.getHoursWorked());
            row.put("workDate", record.getFinalizedDate().toString());
            row.put("finalized", true);
            rows.add(row);
        });

        return rows;
    }

    public void finalizeJobHours(Job job, List<Application> applicationsForJob) {
        for (Application application : applicationsForJob) {
            if (application.getStatus() != ApplicationStatus.APPROVED) {
                continue;
            }

            Double hours = workLogRepository.sumHoursByStudentIdAndJobId(
                    application.getStudent().getId(),
                    job.getId()
            );

            FinalizedWorkRecord record = new FinalizedWorkRecord();
            record.setStudentId(application.getStudent().getId());
            record.setStudentName(application.getStudent().getFullName());
            record.setJobTitle(job.getTitle());
            record.setHoursWorked(hours);
            record.setFinalizedDate(LocalDate.now());
            finalizedWorkRecordRepository.save(record);
        }
    }

    private Map<String, Object> toStudentMap(WorkLog workLog) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("id", workLog.getId());
        row.put("studentId", workLog.getStudent().getId());
        row.put("jobId", workLog.getJob().getId());
        row.put("jobTitle", workLog.getJob().getTitle());
        row.put("workDate", workLog.getWorkDate().toString());
        row.put("hoursWorked", workLog.getHoursWorked());
        return row;
    }
}
