package com.worktrack.backend.controller;

import com.worktrack.backend.dto.JobRequest;
import com.worktrack.backend.entity.Job;
import com.worktrack.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    public Job createJob(@Valid @RequestBody JobRequest request) {
        return jobService.createJob(request);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @Valid @RequestBody JobRequest request) {
        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }
}
