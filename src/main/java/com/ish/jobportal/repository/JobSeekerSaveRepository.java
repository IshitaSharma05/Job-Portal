package com.ish.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ish.jobportal.entity.JobPostActivity;
import com.ish.jobportal.entity.JobSeekerProfile;
import com.ish.jobportal.entity.JobSeekerSave;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);

    void deleteByJob(JobPostActivity job);

    public List<JobSeekerSave> findByUserId_UserAccountId(Integer userAccountId);

    boolean existsByUserId_UserAccountIdAndJob_JobPostId(Integer userId, Integer jobId);

}
