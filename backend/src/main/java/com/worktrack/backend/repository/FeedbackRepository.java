package com.worktrack.backend.repository;

import com.worktrack.backend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByStudentId(Long studentId);
    List<Feedback> findByJobId(Long jobId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Feedback f where f.job.id = :jobId")
    void deleteByJobId(@Param("jobId") Long jobId);
}
