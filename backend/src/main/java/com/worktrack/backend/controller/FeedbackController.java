package com.worktrack.backend.controller;

import com.worktrack.backend.dto.FeedbackRequest;
import com.worktrack.backend.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public Map<String, Object> create(@Valid @RequestBody FeedbackRequest request) {
        return feedbackService.create(request);
    }

    @GetMapping("/student/{studentId}")
    public List<Map<String, Object>> getByStudent(@PathVariable Long studentId) {
        return feedbackService.getByStudent(studentId);
    }

    @GetMapping("/admin/candidates")
    public List<Map<String, Object>> getCandidates() {
        return feedbackService.getAdminCandidates();
    }
}
