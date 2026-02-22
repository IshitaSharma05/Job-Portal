package com.ish.jobportal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ish.jobportal.entity.JobPostActivity;
import com.ish.jobportal.entity.JobSeekerProfile;
import com.ish.jobportal.entity.JobSeekerSave;
import com.ish.jobportal.repository.JobSeekerSaveRepository;

@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

   public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile seekerProfile) {

        if (seekerProfile == null || seekerProfile.getUserAccountId() == null) {
            return new java.util.ArrayList<>();
     }

        return jobSeekerSaveRepository.findByUserId_UserAccountId(seekerProfile.getUserAccountId());
    }

    public List<JobSeekerSave> getJobCandidates(JobPostActivity job) {
        return jobSeekerSaveRepository.findByJob(job);
    }

    public void addNew(JobSeekerSave jobSeekerSave) {

        Integer userId = jobSeekerSave.getUserId().getUserAccountId();
        Integer jobId = jobSeekerSave.getJob().getJobPostId();

        boolean alreadySaved =jobSeekerSaveRepository.existsByUserId_UserAccountIdAndJob_JobPostId(userId, jobId);

        if (!alreadySaved) {
            jobSeekerSaveRepository.save(jobSeekerSave);
        }
    }
}
