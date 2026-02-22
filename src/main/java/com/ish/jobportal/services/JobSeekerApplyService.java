package com.ish.jobportal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ish.jobportal.entity.JobPostActivity;
import com.ish.jobportal.entity.JobSeekerApply;
import com.ish.jobportal.entity.JobSeekerProfile;
import com.ish.jobportal.repository.JobSeekerApplyRepository;

@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile seekerProfile) {

    if (seekerProfile == null || seekerProfile.getUserAccountId() == null) {
        return new ArrayList<>();
    }

    return jobSeekerApplyRepository
            .findByUserId_UserAccountId(seekerProfile.getUserAccountId());
}

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job) {
        return jobSeekerApplyRepository.findByJob(job);
    }

    public void addNew(JobSeekerApply jobSeekerApply) {
        jobSeekerApplyRepository.save(jobSeekerApply);
    }
}
