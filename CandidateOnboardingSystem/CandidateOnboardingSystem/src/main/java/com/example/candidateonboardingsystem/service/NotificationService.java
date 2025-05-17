package com.example.candidateonboardingsystem.service;

import com.example.candidateonboardingsystem.model.CandidateModel;
import com.example.candidateonboardingsystem.model.JobOfferNotification;
import com.example.candidateonboardingsystem.repository.CandidateJobOfferNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final CandidateJobOfferNotificationRepository jobOfferNotificationRepository;

    @Autowired
    public NotificationService(CandidateJobOfferNotificationRepository jobOfferNotificationRepository) {
        this.jobOfferNotificationRepository = jobOfferNotificationRepository;
    }

    public void handleTries(JobOfferNotification jobOfferNotification) {
        int MAX_RETRIES = 3;

        CandidateModel candidate = jobOfferNotification.getCandidate();

        if (candidate.getStatus() == CandidateModel.Status.APPLIED) {
            int currentRetries = jobOfferNotification.getRetry_count();

            if (currentRetries >= MAX_RETRIES) {
                jobOfferNotificationRepository.delete(jobOfferNotification);
            } else {
                jobOfferNotification.setRetry_count(currentRetries + 1);
                jobOfferNotificationRepository.save(jobOfferNotification);
            }
        }
    }
}
