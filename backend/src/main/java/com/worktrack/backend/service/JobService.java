package com.worktrack.backend.service;

import com.worktrack.backend.dto.JobRequest;
import com.worktrack.backend.entity.Application;
import com.worktrack.backend.entity.Job;
import com.worktrack.backend.repository.ApplicationRepository;
import com.worktrack.backend.repository.FeedbackRepository;
import com.worktrack.backend.repository.JobRepository;
import com.worktrack.backend.repository.WorkLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final WorkLogRepository workLogRepository;
    private final FeedbackRepository feedbackRepository;
    private final WorkLogService workLogService;

    public JobService(JobRepository jobRepository,
                      ApplicationRepository applicationRepository,
                      WorkLogRepository workLogRepository,
                      FeedbackRepository feedbackRepository,
                      WorkLogService workLogService) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.workLogRepository = workLogRepository;
        this.feedbackRepository = feedbackRepository;
        this.workLogService = workLogService;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job createJob(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle().trim());
        job.setDepartment(request.getDepartment().trim());
        job.setPayPerHour(request.getPayPerHour());
        job.setWorkTiming(request.getWorkTiming().trim());
        job.setDescription(request.getDescription().trim());
        job.setPostedDate(LocalDate.now());
        return jobRepository.save(job);
    }

    public Job updateJob(Long id, JobRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found."));

        job.setTitle(request.getTitle().trim());
        job.setDepartment(request.getDepartment().trim());
        job.setPayPerHour(request.getPayPerHour());
        job.setWorkTiming(request.getWorkTiming().trim());
        job.setDescription(request.getDescription().trim());
        return jobRepository.save(job);
    }

    @Transactional
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found."));

        List<Application> applicationsForJob = applicationRepository.findByJobId(id);
        workLogService.finalizeJobHours(job, applicationsForJob);
        feedbackRepository.deleteByJobId(id);
        workLogRepository.deleteByJobId(id);
        applicationRepository.deleteByJobId(id);
        jobRepository.delete(job);
    }
}
