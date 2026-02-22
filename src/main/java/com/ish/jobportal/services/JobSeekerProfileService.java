package com.ish.jobportal.services;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ish.jobportal.entity.JobSeekerProfile;
import com.ish.jobportal.entity.Users;
import com.ish.jobportal.repository.JobSeekerProfileRepository;
import com.ish.jobportal.repository.UsersRepository;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersRepository usersRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UsersRepository usersRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    @Transactional
    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        JobSeekerProfile savedProfile = jobSeekerProfileRepository.save(jobSeekerProfile);

    
        if (savedProfile.getSkills() != null) {
            savedProfile.getSkills().forEach(skill -> skill.setJobSeekerProfile(savedProfile));
        }

        return savedProfile;
    }

    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<JobSeekerProfile> seekerProfile = getOne(users.getUserId());
            return seekerProfile.orElse(null);
        } else return null;

    }
}
