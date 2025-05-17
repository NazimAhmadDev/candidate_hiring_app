package com.example.candidateonboardingsystem.repository;

import com.example.candidateonboardingsystem.model.JobOfferNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateJobOfferNotificationRepository extends JpaRepository<JobOfferNotification, Long> {
}
