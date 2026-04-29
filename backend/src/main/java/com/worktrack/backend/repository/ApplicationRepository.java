package com.worktrack.backend.repository;

import com.worktrack.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findByJobId(Long jobId);
    List<Application> findByStatus(com.worktrack.backend.entity.ApplicationStatus status);
    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Application a where a.job.id = :jobId")
    void deleteByJobId(@Param("jobId") Long jobId);
}
