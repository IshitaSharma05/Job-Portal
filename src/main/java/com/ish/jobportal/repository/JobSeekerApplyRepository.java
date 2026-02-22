package com.ish.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ish.jobportal.entity.JobPostActivity;
import com.ish.jobportal.entity.JobSeekerApply;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId_UserAccountId(int userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);

    void deleteByJob(JobPostActivity job);
}
