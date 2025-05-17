package com.example.candidateonboardingsystem.repository;

import com.example.candidateonboardingsystem.model.BankInfo;
import com.example.candidateonboardingsystem.model.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Long> {
    Optional<CandidateEducation> findByCandidateId(Long candidateId);
}
